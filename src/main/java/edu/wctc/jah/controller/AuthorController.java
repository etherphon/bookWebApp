/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.controller;

import edu.wctc.jah.service.AuthorService;
import edu.wctc.jah.service.BookService;
import edu.wctc.jah.model.Author;
import edu.wctc.jah.model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.jah.model.Author;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author etherdesign
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
        public static final String LIST_PAGE = "viewAuthors.jsp";
        public static final String HELP_PAGE = "helpPage.jsp";
        public static final String ADD = "Add";
        public static final String DEL = "Delete";
        public static final String UPD = "Update";
        public static final String HELP = "Help";
        public static final String WEBMASTER = "jahedding@gmail.com";
    
        int numAdded = 0;
        int numUpdated = 0;
        int numDeleted = 0;
        
        private String dbJndiName;
        
        private AuthorService as;
        
        private RequestDispatcher view;
        
    @Override
    public void init() throws ServletException {
    ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        as = (AuthorService) ctx.getBean("authorService");
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
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
            String authId = request.getParameter("authorPk");
            
            if (formAction == null) {
                formAction = "";
            }
            
        
        switch (formAction) {
            case ADD:
                String newAuthor = request.getParameter("newAuthor");
                Author author = new Author();
                author.setAuthorName(newAuthor);
                author.setDateAdded(new Date());
                as.edit(author);
                numAdded++;
                session.setAttribute("numAdded", numAdded);
                request.setAttribute("addedAuthor", newAuthor);
                refreshList(request, as);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
            break;
            
            case UPD:
                String updAuthor = request.getParameter("updAuthor");
                Author authorEdit = as.findById(authId);
                authorEdit.setAuthorName(updAuthor);
                as.edit(authorEdit);
                numUpdated++;
                session.setAttribute("numUpdated", numUpdated);
                refreshList(request, as);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
            break;
            
            case DEL:
                authId = request.getParameter("authorPk");
                as.remove(as.findById(authId));
                numDeleted++;
                session.setAttribute("numDeleted", numDeleted);
                refreshList(request, as);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
            break;
            
            case HELP:
                response.sendRedirect(response.encodeRedirectURL(HELP_PAGE));
            
            default:
                refreshList(request, as);
                view = request.getRequestDispatcher(response.encodeURL(LIST_PAGE));
                view.forward(request, response);
                
        }
            
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }
        // getParameterValues returns an array of strings from chceckboxes
        
        
//        RequestDispatcher view = request.getRequestDispatcher("viewAuthors.jsp");
//        view.forward(request, response);
//        
//        String formAction = request.getParameter("fAction");
//        String authId = request.getParameter("authorPk");
//        List<Author> authorList;
//        
//        
//        switch (formAction) {
//            case "Add":
//                String newAuthor = request.getParameter("newAuthor");
//                as.addAuthor(newAuthor);
//                request.setAttribute("addedAuthor", newAuthor);
//                authorList = as.getAuthorList();
//                request.setAttribute("authorList", authorList);
//                view = request.getRequestDispatcher("/viewAuthors.jsp");
//                view.forward(request, response);
//            break;
//            
//            case "Update":
//                String updAuthor = request.getParameter("updAuthor");
//                as.updateAuthor(updAuthor, authId);
//                authorList = as.getAuthorList();
//                request.setAttribute("authorList", authorList);
//                view = request.getRequestDispatcher("/viewAuthors.jsp");
//                view.forward(request, response);
//            break;
//            
//            case "Delete":
//                authId = request.getParameter("authorPk");
//                as.deleteAuthorById(authId);
//                authorList = as.getAuthorList();
//                request.setAttribute("authorList", authorList);
//                view = request.getRequestDispatcher("/viewAuthors.jsp");
//                view.forward(request, response);
//            break;
//        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public AuthorService getAs() {
        return as;
    }

    public void setAs(AuthorService as) {
        this.as = as;
    }
    
    public void refreshList(HttpServletRequest request, AuthorService as) throws ClassNotFoundException, SQLException {
        List<Author> authorList = as.findAll();
        request.setAttribute("authorList", authorList);
    }

    public String getDbJndiName() {
        return dbJndiName;
    }

    public void setDbJndiName(String dbJndiName) {
        this.dbJndiName = dbJndiName;
    }

    
}
