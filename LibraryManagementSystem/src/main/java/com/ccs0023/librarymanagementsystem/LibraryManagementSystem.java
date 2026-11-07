package com.ccs0023.librarymanagementsystem;


public class LibraryManagementSystem {
        public static void main(String[] args) {
        //CODES STARTING HERE ARE USED TO TEST BORROWERS
        BorrowerInformation borrowerSystem = new BorrowerInformation();

        borrowerSystem.loadBorrowers();
        borrowerSystem.borrower();
        
        //CODE ENDS HERE

    }
}
