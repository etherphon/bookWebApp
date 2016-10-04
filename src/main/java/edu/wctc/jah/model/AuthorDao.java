/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author etherdesign
 */
public class AuthorDao implements AuthorDaoInterface {
    
    public static final String AUTHOR = "author";
    public static final String AUTHOR_ID = "author_id";
    public static final String DATE_ADDED = "date_added";
    public static final String AUTHOR_N = "author_name";
    
    private dbStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String passWord;

    public AuthorDao(dbStrategy db, String driverClass, String url, String userName, String passWord) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }
    
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, passWord);
        List<Map<String, Object>> records = db.findAllRecords(AUTHOR, 500);
        List<Author> authors = new ArrayList<>();
        
        for (Map<String, Object> rec : records) {
            Author author = new Author();
            author.setAuthorId(Integer.parseInt(rec.get(AUTHOR_ID).toString()));
            if (rec.get(AUTHOR_N).toString() != null) {
                author.setAuthorName(rec.get(AUTHOR_N).toString());
            } else {
                author.setAuthorName("Unknown");
            }
            author.setDateAdded((Date)rec.get(DATE_ADDED));
            authors.add(author);
        }
        db.closeConnection();
        
        return authors;
    }
    
    public Author findAuthorById(String id) throws ClassNotFoundException, SQLException {
        Author author = new Author();
        db.openConnection(driverClass, url, userName, passWord);
        Map<String,Object> rec = new LinkedHashMap<>();
        
        rec = db.findRecordByKey(AUTHOR, AUTHOR_ID, id);
        
        author.setAuthorId(Integer.parseInt(rec.get(AUTHOR_ID).toString()));
            if (rec.get(AUTHOR_N).toString() != null) {
                author.setAuthorName(rec.get(AUTHOR_N).toString());
            } else {
                author.setAuthorName("Unknown");
            }
            author.setDateAdded((Date)rec.get(DATE_ADDED));
            
        return author;
    }
    
    public final void deleteAuthorById(String id) throws SQLException, NumberFormatException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, passWord);
        Integer pkv = Integer.parseInt(id);
        db.deleteRecordByKey(AUTHOR, AUTHOR_ID, pkv);
        db.closeConnection();
    }
    
    public final void addAuthor(String authorName) throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, passWord);
        List<String> columnNames = new ArrayList<>();
        columnNames.add(AUTHOR_N);
        columnNames.add(DATE_ADDED);
        List<Object> colValues = new ArrayList<>();
        colValues.add("'" + authorName + "'");
        colValues.add(new Date());
        db.insertRecord(AUTHOR, columnNames, colValues);
        db.closeConnection();
    }
    
    public dbStrategy getDb() {
        return db;
    }

    public void setDb(dbStrategy db) {
        this.db = db;
    }
   
    public final void editAuthor(String table, String primaryKey, Object pkValue, List<String> colnames, List<Object> colVals) throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, passWord);
        //List<String> columnNames = new ArrayList<>();
        //columnNames.add(AUTHOR_N);
        //columnNames.add(DATE_ADDED);
        //List<Object> colValues = new ArrayList<>();
        db.updateRecord(table, primaryKey, pkValue, colnames, colVals);
        db.closeConnection();
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDaoInterface dao = new AuthorDao(
                new MySqlDbStrategy(),
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );
        
        dao.addAuthor("James Joyce");
        //List<Author> authors = dao.getAuthorList();
        Author author = dao.findAuthorById("1");
        System.out.println(author);
        
    }
    
}
