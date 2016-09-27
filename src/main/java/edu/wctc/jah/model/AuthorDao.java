/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.sql.SQLException;
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
        List<Map<String, Object>> records = db.findAllRecords("author", 500);
        List<Author> authors = new ArrayList<>();
        
        for (Map<String, Object> rec : records) {
            Author author = new Author();
            author.setAuthorId(Integer.parseInt(rec.get("author_id").toString()));
            if (rec.get("author_name").toString() != null) {
                author.setAuthorName(rec.get("author_name").toString());
            } else {
                author.setAuthorName("Unknown");
            }
            author.setDateAdded((Date)rec.get("date_added"));
            authors.add(author);
        }
        db.closeConnection();
        
        return authors;
    }
    
    public Author findAuthorById(String id) throws ClassNotFoundException, SQLException {
        Author author = new Author();
        db.openConnection(driverClass, url, userName, passWord);
        Map<String,Object> rec = new LinkedHashMap<>();
        
        rec = db.findRecordByKey("author", "author_id", id);
        
        author.setAuthorId(Integer.parseInt(rec.get("author_id").toString()));
            if (rec.get("author_name").toString() != null) {
                author.setAuthorName(rec.get("author_name").toString());
            } else {
                author.setAuthorName("Unknown");
            }
            author.setDateAdded((Date)rec.get("date_added"));
            
        return author;
    }
    
    public dbStrategy getDb() {
        return db;
    }

    public void setDb(dbStrategy db) {
        this.db = db;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDaoInterface dao = new AuthorDao(
                new MySqlDbStrategy(),
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root",
                "admin"
        );
        
        //List<Author> authors = dao.getAuthorList();
        Author author = dao.findAuthorById("1");
        System.out.println(author);
        
    }
    
}
