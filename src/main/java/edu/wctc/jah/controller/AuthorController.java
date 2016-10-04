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

/**
 *
 * @author etherdesign
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
        private AuthorDaoInterface dao = new AuthorDao(new MySqlDbStrategy(),
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin");
        
        private AuthorService as = new AuthorService(dao);

    public AuthorService getAs() {
        return as;
    }

    public void setAs(AuthorService as) {
        this.as = as;
    }
    
    //AuthorEditController aec = new AuthorEditContoller();
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
        
        
        // getParameterValues returns an array of strings from chceckboxes
        
        List<Author> authorList = as.getAuthorList();
        request.setAttribute("authorList", authorList);
        RequestDispatcher view = request.getRequestDispatcher("viewAuthors.jsp");
        view.forward(request, response);
        
//        String formAction = request.getParameter("fAction");
//        String authId = request.getParameter("authorPk");
//        
//        
//        switch (formAction) {
//            case "Add":
//                String newAuthor = request.getParameter("newAuthor");
//                as.addAuthor(newAuthor);
//                request.setAttribute("addedAuthor", newAuthor);
//                RequestDispatcher view2 = request.getRequestDispatcher("/RectangleResponse.jsp");
//                view.forward(request, response);
//            break;
//            
//            case "Update":
//                
//                as.updateAuthor(newName, authId);
//            break;
//            
//            case "Delete":
//                as.deleteAuthorById(authId);
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

}
