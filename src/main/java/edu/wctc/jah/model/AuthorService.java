/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author etherdesign
 */
public class AuthorService {
    
    private AuthorDaoInterface dao;
    
    public AuthorService(AuthorDaoInterface dao) {
        this.dao = dao;
    }
    
    public final List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        return dao.getAuthorList();
    }
 
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDaoInterface dao = new AuthorDao(
        new MySqlDbStrategy(),
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );
        
        AuthorService srv = new AuthorService(dao);
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
        
    }
    
}
