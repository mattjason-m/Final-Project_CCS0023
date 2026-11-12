
package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BorrowerHistory {

    ArrayList<BorrowBlueprint> borrowRecords;
    BorrowerInformation borrowerInformation;
    Scanner scan = new Scanner(System.in);

    public BorrowerHistory(ArrayList<BorrowBlueprint> borrowRecords, BorrowerInformation borrowerInformation){
        this.borrowRecords = borrowRecords;
        this.borrowerInformation = borrowerInformation;
    }

    public void borrowerHistory(){

        int borrowerIdNumber;

        System.out.println("Enter Borrower ID Number: ");

        try{
            borrowerIdNumber = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
            return;
        }

        BorrowersManagement registeredBorrower = borrowerInformation.searchBorrower(borrowerIdNumber);

        if (registeredBorrower != null){

            System.out.println("Borrower History");
            System.out.println("Borrower ID: " + registeredBorrower.getBorrowerIdNumber());
            System.out.println("Borrower Name: " + registeredBorrower.getFirstName() + " " + registeredBorrower.getMiddleName() + " " + registeredBorrower.getLastName());

            boolean found = false;

            for (BorrowBlueprint registeredRecord : borrowRecords){

                if (borrowerIdNumber == registeredRecord.getBorrower().getBorrowerIdNumber()){

                    System.out.println("Material ID: " + registeredRecord.getMaterial().getMaterialId());
                    System.out.println("Material Name: " + registeredRecord.getMaterial().getTitle());
                    System.out.println("Date Borrowed: " + registeredRecord.getBorrowDate());
                    System.out.println();

                    found = true;
                }
            }

            if (found == false){
                System.out.println("No borrowing history found.");
            }
        }
        else{
            System.out.println("Borrower does not exist.");
        }
    }
}
