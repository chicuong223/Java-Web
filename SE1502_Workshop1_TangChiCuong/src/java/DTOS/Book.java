/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOS;

import java.util.Comparator;

/**
 *
 * @author chiuy
 */
public class Book {
    private String bookID;
    private String name;
    private String author;
    private int year;
    private String description;
    private String status;
    private String categoryID;

    public Book(String bookID, String name, String author, int year, String description, String status, String categoryID) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.year = year;
        this.description = description;
        this.status = status;
        this.categoryID = categoryID;
    }
    
    public Book(){
        bookID = "";
        name = "";
        author = "";
        year = 0;
        description = "";
        status = "";
        categoryID = "";
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
    
    public static Comparator<Book> byCategory = new Comparator<Book>() {
        @Override
        public int compare(Book t, Book t1) {
            if(t.getCategoryID().compareTo(t1.getCategoryID())>0) return 1;
            else if(t.getCategoryID().compareTo(t1.getCategoryID())<0) return -1;
            return 0;
        }
    };
}
