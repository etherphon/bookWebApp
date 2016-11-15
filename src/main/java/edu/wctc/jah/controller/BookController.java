/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.controller;


import edu.wctc.jah.ejb.AuthorFacade;
import edu.wctc.jah.ejb.BookFacade;
import edu.wctc.jah.model.Author;
import edu.wctc.jah.model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author etherdesign
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    public static final String ADD = "Add";
    public static final String DEL = "Delete";
    public static final String UPD = "Update";
    public static final String HELP = "Help";
    public static final String WEBMASTER = "jahedding@gmail.com";
    public static final String LIST_PAGE = "viewBooks.jsp";
    public static final String HELP_PAGE = "helpPage.jsp";
    
    int numAdded = 0;
    int numUpdated = 0;
    int numDeleted = 0;
        
    private String dbJndiName;
        
    @Inject
    private BookFacade bs;
    
    @Inject
    private AuthorFacade as;
        
    private RequestDispatcher view;
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
        HttpSession session = request.getSession();
        //session.setAttribute("attribute", ADD);
        ServletContext ctx = request.getServletContext();
        
        synchronized(ctx) {
            // add applications operations here
            ctx.setAttribute("dateAndTime", new Date());
            ctx.setAttribute("webmaster", WEBMASTER);
        }
        
        try {
//            refreshList(request, as);
//            view = request.getRequestDispatcher(LIST_PAGE);
//            view.forward(request, response);
        
            String formAction = request.getParameter("fAction");
            String bookId = request.getParameter("bookPk");
            
            if (formAction == null) {
                formAction = "";
            }
            
        
        switch (formAction) {
            case ADD:
                String bookName = request.getParameter("newBookName");
                String isbn = request.getParameter("newBookIsbn");
                String author = request.getParameter("newBookAuthor");
                Book book = new Book();
                book.setTitle(bookName);
                book.setIsbn(isbn);
                book.setAuthorId(as.find(new Integer(author)));
                bs.create(book);
                numAdded++;
                //session.setAttribute("numAdded", numAdded);
                //request.setAttribute("addedAuthor", newAuthor);
                refreshList(request, bs);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
            break;
            
            case UPD:
                String updBook = request.getParameter("updBook");
                String updIsbn = request.getParameter("updIsbn");
                String updAuthor = request.getParameter("updAuthor");
                Book bookEdit = bs.find(new Integer(bookId));
                bookEdit.setTitle(updBook);
                bookEdit.setIsbn(updIsbn);
                bookEdit.setAuthorId(as.find(new Integer(updAuthor)));
                bs.edit(bookEdit);
                numUpdated++;
                session.setAttribute("numUpdated", numUpdated);
                refreshList(request, bs);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
            break;
            
            case DEL:
                bookId = request.getParameter("bookPk");
                //as.find(new Integer(authId));
                bs.remove(bs.find(new Integer(bookId)));
                numDeleted++;
                session.setAttribute("numDeleted", numDeleted);
                refreshList(request, bs);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
            break;
            
            case HELP:
                response.sendRedirect(response.encodeRedirectURL(HELP_PAGE));
            
            default:
                refreshList(request, bs);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
                
        }
            
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public BookFacade getBs() {
        return bs;
    }

    public void setBs(BookFacade bs) {
        this.bs = bs;
    }

    public AuthorFacade getAs() {
        return as;
    }

    public void setAs(AuthorFacade as) {
        this.as = as;
    }
    
    public void refreshList(HttpServletRequest request, BookFacade bs) throws ClassNotFoundException, SQLException {
        List<Book> bookList = bs.findAll();
        List<Author> authorList = getAs().findAll();
        request.setAttribute("authorList", authorList);
        request.setAttribute("bookList", bookList);
    }

    public String getDbJndiName() {
        return dbJndiName;
    }

    public void setDbJndiName(String dbJndiName) {
        this.dbJndiName = dbJndiName;
    }
    
    
    
    
}
