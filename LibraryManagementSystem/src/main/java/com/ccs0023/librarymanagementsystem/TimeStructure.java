package com.ccs0023.librarymanagementsystem;

import java.time.LocalDate;

public class TimeStructure {

    private BorrowersManagement borrower;
    private int assetID;
    private String assetName;
    private LocalDate timestamp;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;

    public TimeStructure(){
        borrower = null;
        assetID = 0;
        assetName = "";
        timestamp = null;
        dueDate = null;
        returnDate = null;
        returned = false;
    }

    public int getID(){
        return borrower.getBorrowerIdNumber();
    }

    public BorrowersManagement getBorrower(){
        return borrower;
    }

    public int getAssetID(){
        return assetID;
    }

    public String getAssetName(){
        return assetName;
    }

    public LocalDate getTimestamp(){
        return timestamp;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public boolean getReturned(){
        return returned;
    }

    public void setBorrower(BorrowersManagement enteredBorrower){
        borrower = enteredBorrower;
    }

    public void setAssetID(int enteredID){
        assetID = enteredID;
    }

    public void setAssetName(String enteredName){
        assetName = enteredName;
    }

    public void borrowMaterial(int days){

        timestamp = LocalDate.now();
        dueDate = timestamp.plusDays(days);
        returnDate = null;
        returned = false;
    }

    public void returnMaterial(){

        returnDate = LocalDate.now();
        returned = true;
    }

    public boolean isLate(){

        if (dueDate == null){
            return false;
        }

        if (returned == true){
            if (returnDate.isAfter(dueDate)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (LocalDate.now().isAfter(dueDate)){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
