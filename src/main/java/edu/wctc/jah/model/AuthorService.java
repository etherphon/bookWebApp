/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author etherdesign
 */
public class AuthorService {
    
    private List<Author> authorList;
    
    public AuthorService() {
        Author A1 = new Author(1111);
        A1.setAuthorName("Aldous Huxley");
        A1.setDateAdded(Date.valueOf(LocalDate.now()));
        Author A2 = new Author(2222);
        A2.setAuthorName("Douglas Adams");
        A2.setDateAdded(Date.valueOf(LocalDate.now()));
        Author A3 = new Author(3333);
        A3.setAuthorName("Alan Watts");
        A3.setDateAdded(Date.valueOf(LocalDate.now()));
        authorList.add(A1);
        authorList.add(A2);
        authorList.add(A3);
    }
    
    public final List<Author> getAuthorList() {
        return authorList;
    }
    
}
