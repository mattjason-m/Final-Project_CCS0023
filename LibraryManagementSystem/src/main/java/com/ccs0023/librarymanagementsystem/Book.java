
package com.ccs0023.librarymanagementsystem;

public class Book extends Material {
    
    public Book(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 7;
    }
    
    @Override
    public String getMaterialType(){
        return "Book";
    }
}
