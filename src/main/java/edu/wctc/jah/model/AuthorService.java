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
import org.apache.commons.lang3.StringUtils;

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
    
    public final Author getAuthor(String id) throws ClassNotFoundException, SQLException {
        if (id == null || id.isEmpty() || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException();
        } else {
            return dao.findAuthorById(id.trim());
        }
    }
    
    public final void deleteAuthorById(String id) throws SQLException, NumberFormatException, ClassNotFoundException {
        if (id == null || id.isEmpty() || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException();
        } 
            dao.deleteAuthorById(id.trim());
        }
    
    public final void addAuthor(String authorName) throws ClassNotFoundException, SQLException {
        if (authorName == null || authorName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            dao.addAuthor(authorName.trim());
        }
    }
 
    public final void updateAuthor(String newName, String pkValue) throws ClassNotFoundException, SQLException {
        if (newName == null || newName.isEmpty() || pkValue == null || pkValue.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            String table = "author";
            String primaryKey = "author_id";
            List<String> colnames = new ArrayList<>();
            List<Object> colVals = new ArrayList<>();
            colnames.add("author_name");
            colnames.add("date_added");
            colVals.add(newName);
            colVals.add(dao.findAuthorById(pkValue).getDateAdded());
            dao.editAuthor(table, primaryKey, pkValue, colnames, colVals);
        }
        
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
