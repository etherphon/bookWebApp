/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.controller;

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
import edu.wctc.jah.model.AuthorDao;
import edu.wctc.jah.model.AuthorDaoInterface;
import edu.wctc.jah.model.AuthorService;
import edu.wctc.jah.model.MySqlDbStrategy;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author etherdesign
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
        //public static final String DRIVER = "com.mysql.jdbc.Driver";      
        private String driverClass;
        private String URL;
        private String userName;
        private String passWord;
          
        @Inject
        private AuthorService as;

    //public void refreshAuthorList() {
        
    //}
        
    @Override
    public void init() throws ServletException {
        driverClass = "com.mysql.jdbc.Driver";
        URL = "jdbc:mysql://localhost:3306/book";
        userName = "root";
        passWord = "admin";
    }
    
    private void configDbConnection() {
        as.getDao().initDao(driverClass, URL, userName, passWord);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            configDbConnection();
            List<Author> authorList = as.getAuthorList();
            request.setAttribute("authorList", authorList);
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }
        // getParameterValues returns an array of strings from chceckboxes
        
        
        RequestDispatcher view = request.getRequestDispatcher("viewAuthors.jsp");
        view.forward(request, response);
        
        String formAction = request.getParameter("fAction");
        String authId = request.getParameter("authorPk");
        List<Author> authorList;
        
        
        switch (formAction) {
            case "Add":
                String newAuthor = request.getParameter("newAuthor");
                as.addAuthor(newAuthor);
                request.setAttribute("addedAuthor", newAuthor);
                authorList = as.getAuthorList();
                request.setAttribute("authorList", authorList);
                view = request.getRequestDispatcher("/viewAuthors.jsp");
                view.forward(request, response);
            break;
            
            case "Update":
                String updAuthor = request.getParameter("updAuthor");
                as.updateAuthor(updAuthor, authId);
                authorList = as.getAuthorList();
                request.setAttribute("authorList", authorList);
                view = request.getRequestDispatcher("/viewAuthors.jsp");
                view.forward(request, response);
            break;
            
            case "Delete":
                authId = request.getParameter("authorPk");
                as.deleteAuthorById(authId);
                authorList = as.getAuthorList();
                request.setAttribute("authorList", authorList);
                view = request.getRequestDispatcher("/viewAuthors.jsp");
                view.forward(request, response);
            break;
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
    
    //public 

}
