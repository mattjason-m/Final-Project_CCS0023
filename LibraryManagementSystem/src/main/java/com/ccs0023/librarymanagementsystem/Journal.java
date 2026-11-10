
package com.ccs0023.librarymanagementsystem;

public class Journal extends Material {
    
    public Journal(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 3;
    }
    
    @Override
    public String getMaterialType(){
        return "Journal";
    }
}
