
package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;


public class TimeStructure {
    private BorrowersManagement borrower;
    private int assetID;
    private String assetName;
    private LocalDateTime timestamp;
       
    public TimeStructure(){}
    public int getID(){
        return borrower.getBorrowerIdNumber();
    }
    
    
    
}
