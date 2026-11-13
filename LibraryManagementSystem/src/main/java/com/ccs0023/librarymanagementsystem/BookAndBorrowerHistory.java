package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BookAndBorrowerHistory {

    ArrayList<BorrowBlueprint> borrowRecords = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public void loadHistory(){

        AssetManagement assetSystem = new AssetManagement();
        assetSystem.loadAsset();

        BorrowerInformation borrowerSystem = new BorrowerInformation();
        borrowerSystem.loadBorrowers();

        BorrowAMaterial borrowSystem = new BorrowAMaterial();
        borrowSystem.loadTransactions(assetSystem, borrowerSystem);

        borrowRecords = borrowSystem.borrowRecords;
    }

    public void borrowerHistory(){

        loadHistory();

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

        boolean found = false;

        for (BorrowBlueprint registeredRecord : borrowRecords){

            if (borrowerIdNumber == registeredRecord.getBorrower().getBorrowerIdNumber()){

                System.out.println("Borrower ID: " + registeredRecord.getBorrower().getBorrowerIdNumber());
                System.out.println("Borrower Name: " + registeredRecord.getBorrower().getFirstName() + " "
                        + registeredRecord.getBorrower().getMiddleName() + " "
                        + registeredRecord.getBorrower().getLastName());
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

    public void bookHistory(){

        loadHistory();

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

                System.out.println("Material ID: " + registeredRecord.getMaterial().getMaterialId());
                System.out.println("Material Name: " + registeredRecord.getMaterial().getTitle());
                System.out.println("Borrower ID: " + registeredRecord.getBorrower().getBorrowerIdNumber());
                System.out.println("Borrower Name: " + registeredRecord.getBorrower().getFirstName() + " "
                        + registeredRecord.getBorrower().getMiddleName() + " "
                        + registeredRecord.getBorrower().getLastName());
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
