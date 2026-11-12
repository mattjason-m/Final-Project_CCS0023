
package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BookHistory {

    ArrayList<BorrowBlueprint> borrowRecords;
    Scanner scan = new Scanner(System.in);

    public BookHistory(ArrayList<BorrowBlueprint> borrowRecords){
        this.borrowRecords = borrowRecords;
    }

    public void bookHistory(){

        int materialID;

        System.out.println("Enter Material ID Number: ");

        try{
            materialID = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
            return;
        }

        boolean found = false;

        for (BorrowBlueprint registeredRecord : borrowRecords){

            if (materialID == registeredRecord.getMaterial().getMaterialId()){

                BorrowersManagement registeredBorrower = registeredRecord.getBorrower();

                System.out.println("Material ID: " + registeredRecord.getMaterial().getMaterialId());
                System.out.println("Material Name: " + registeredRecord.getMaterial().getTitle());
                System.out.println("Borrower ID: " + registeredBorrower.getBorrowerIdNumber());
                System.out.println("Borrower Name: " + registeredBorrower.getFirstName() + " "
                        + registeredBorrower.getMiddleName() + " "
                        + registeredBorrower.getLastName());
                System.out.println("Date Borrowed: " + registeredRecord.getBorrowDate());
                System.out.println();

                found = true;
            }
        }

        if (found == false){
            System.out.println("No borrowing history found.");
        }
    }
}
