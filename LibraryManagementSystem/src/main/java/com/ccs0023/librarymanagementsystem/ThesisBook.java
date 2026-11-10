
package com.ccs0023.librarymanagementsystem;

public class ThesisBook extends Material {
    
    public ThesisBook(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 2;
    }
    
    @Override
    public String getMaterialType(){
        return "Thesis Book";
    }
}
