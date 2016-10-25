/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author etherdesign
 */
@Dependent
public class AuthorDao implements AuthorDaoInterface, Serializable {
    
    public static final String AUTHOR = "author";
    public static final String AUTHOR_ID = "author_id";
    public static final String DATE_ADDED = "date_added";
    public static final String AUTHOR_N = "author_name";
    
    @Inject
    private dbStrategy db;
    
    private DataSource ds;
    private String driverClass;
    private String url;
    private String userName;
    private String passWord;
    
    public AuthorDao() {   
   }
    
    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        if (ds == null) {
            db.openConnection(driverClass, url, userName, passWord);
        } else {
            db.openConnection(ds);
        }
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
    
    @Override
    public Author findAuthorById(String id) throws ClassNotFoundException, SQLException {
        Author author = new Author();
        if (ds == null) {
            db.openConnection(driverClass, url, userName, passWord);
        } else {
            db.openConnection(ds);
        }
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
    
    @Override
    public void deleteAuthorById(String id) throws SQLException, NumberFormatException, ClassNotFoundException {
        if (ds == null) {
            db.openConnection(driverClass, url, userName, passWord);
        } else {
            db.openConnection(ds);
        }
        Integer pkv = Integer.parseInt(id);
        db.deleteRecordByKey(AUTHOR, AUTHOR_ID, pkv);
        db.closeConnection();
    }
    
    @Override
    public void addAuthor(String authorName) throws ClassNotFoundException, SQLException {
        if (ds == null) {
            db.openConnection(driverClass, url, userName, passWord);
        } else {
            db.openConnection(ds);
        }
        List<String> columnNames = new ArrayList<>();
        columnNames.add(AUTHOR_N);
        columnNames.add(DATE_ADDED);
        List<Object> colValues = new ArrayList<>();
        //colValues.add("'" + authorName + "'");
        colValues.add(authorName);
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

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
   
    @Override
    public void editAuthor(String table, String primaryKey, Object pkValue, List<String> colnames, List<Object> colVals) throws ClassNotFoundException, SQLException {
        if (ds == null) {
            db.openConnection(driverClass, url, userName, passWord);
        } else {
            db.openConnection(ds);
        }
        //List<String> columnNames = new ArrayList<>();
        //columnNames.add(AUTHOR_N);
        //columnNames.add(DATE_ADDED);
        //List<Object> colValues = new ArrayList<>();
        db.updateRecord(table, primaryKey, pkValue, colnames, colVals);
        db.closeConnection();
    }
    
    @Override
    public void initDao(String driver, String url, String user, String pass) {
        setDriverClass(driver);
        setUrl(url);
        setUserName(user);
        setPassWord(pass);
    }

    
    @Override
    public void initDao(DataSource ds) throws SQLException {
        setDs(ds);
    }
    
    
    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        AuthorDaoInterface dao = new AuthorDao(
//                new MySqlDbStrategy(),
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root",
//                "admin"
//        );
//        
//        dao.addAuthor("James Joyce");
//        //List<Author> authors = dao.getAuthorList();
//        Author author = dao.findAuthorById("1");
//        System.out.println(author);
//        
//    }
    
}
