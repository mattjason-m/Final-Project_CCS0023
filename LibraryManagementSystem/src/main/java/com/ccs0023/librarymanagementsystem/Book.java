package com.ccs0023.librarymanagementsystem;

public class Book extends Material {

    private String author;

    public Book(){
        super();
        author = "Author";
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String enteredAuthor){
        author = enteredAuthor;
    }

    @Override
    public int getBorrowingDays(){
        return 7;
    }
}