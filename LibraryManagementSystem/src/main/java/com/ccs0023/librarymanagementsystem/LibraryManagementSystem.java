package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;


public class LibraryManagementSystem {
        public static void main(String[] args) {
        //CODES STARTING HERE ARE USED TO TEST BORROWERS
//        BorrowerInformation borrowerSystem = new BorrowerInformation(); //load existing fiel
//
//        borrowerSystem.loadBorrowers(); //load existing file method
//        borrowerSystem.borrower();
        
        //CODE ENDS HERE
        
        
//        LocalDateTime TIME = LocalDateTime.now();
//        System.out.println(TIME);
//        
//        AssetManagement assetSystem =  new AssetManagement();
//        assetSystem.loadAsset();
//        assetSystem.assetManagement();
        
        BorrowAMaterial borrowSystem = new BorrowAMaterial();
        BookAndBorrowerHistory bookHistory = new BookAndBorrowerHistory();
        bookHistory.bookHistory();
        
        BookAndBorrowerHistory history = new BookAndBorrowerHistory();
        history.borrowerHistory();
    }
}
