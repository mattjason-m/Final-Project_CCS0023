package com.ccs0023.librarymanagementsystem;

public class Magazine extends Material {

    public Magazine(){
        super();
    }

    @Override
    public int getBorrowingDays(){
        return 0;
    }
}