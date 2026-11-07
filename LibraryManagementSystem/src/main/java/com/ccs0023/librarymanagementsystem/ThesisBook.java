package com.ccs0023.librarymanagementsystem;

public class ThesisBook extends Material {

    private String author;

    public ThesisBook(){
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
        return 2;
    }
}