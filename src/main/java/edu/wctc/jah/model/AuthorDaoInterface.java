/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author etherdesign
 */
public interface AuthorDaoInterface {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    
    Author findAuthorById(String id) throws ClassNotFoundException, SQLException;
    
    void deleteAuthorById(String id) throws SQLException, NumberFormatException, ClassNotFoundException;
    
    void addAuthor(String authorName) throws ClassNotFoundException, SQLException;
    
    void editAuthor(String table, String primaryKey, Object pkValue, List<String> colnames, List<Object> colVals) throws ClassNotFoundException, SQLException; 
    
    void initDao(String driver, String url, String user, String pass);
}
