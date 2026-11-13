package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Return {

    ArrayList<BorrowBlueprint> borrowRecords = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public void returnBook(){

        AssetManagement assetSystem = new AssetManagement();
        assetSystem.loadAsset();

        BorrowerInformation borrowerSystem = new BorrowerInformation();
        borrowerSystem.loadBorrowers();

        BorrowAMaterial borrowSystem = new BorrowAMaterial();
        borrowSystem.loadTransactions(assetSystem, borrowerSystem);

        borrowRecords = borrowSystem.borrowRecords;

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

        BorrowersManagement registeredBorrower = borrowerSystem.searchBorrower(borrowerIdNumber);

        if (registeredBorrower == null){
            System.out.println("Borrower does not exist.");
            return;
        }

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

        int activeRecords = 0;
        BorrowBlueprint activeRecord = null;

        for (BorrowBlueprint registeredRecord : borrowRecords){

            if (borrowerIdNumber == registeredRecord.getBorrower().getBorrowerIdNumber()){

                if (registeredRecord.getReturned() == false){

                    activeRecords++;

                    if (materialID == registeredRecord.getMaterial().getMaterialId()){
                        activeRecord = registeredRecord;
                    }
                }
            }
        }

        if (activeRecords > 1){
            System.out.println("Multiple active transactions found. Please check the borrowing records.");
            return;
        }

        if (activeRecord == null){
            System.out.println("No active borrowing transaction found.");
            return;
        }

        Material registeredMaterial = activeRecord.getMaterial();

        if (registeredMaterial.getAvailableCopies() >= registeredMaterial.getTotalCopies()){
            System.out.println("All copies are already available.");
            return;
        }

        LocalDateTime returnDate = LocalDateTime.now();

        FileHandler FILE = new FileHandler();

        boolean updated = FILE.updateTransaction(
                borrowerIdNumber,
                materialID,
                activeRecord.getBorrowDate().toString(),
                returnDate.toString());

        if (updated == false){
            System.out.println("Unable to update borrowing transaction.");
            return;
        }

        activeRecord.setReturnDate(returnDate);
        activeRecord.setReturned(true);

        if (returnDate.isAfter(activeRecord.getDueDate())){

            registeredBorrower.addViolation();

            if (registeredBorrower.UpdateRecord(borrowerIdNumber) == false){
                System.out.println("Error saving borrower violations.");
                return;
            }

            System.out.println("Material returned late.");
            System.out.println("Borrower received one violation.");
        }
        else{
            System.out.println("Material returned on time.");
        }

        registeredMaterial.setAvailableCopies(registeredMaterial.getAvailableCopies() + 1);

        String type = registeredMaterial.getMaterialType();
        boolean saved = false;

        if (type.equals("Book") || type.equals("Thesis Book")){
            saved = registeredMaterial.UpdateAuthoredMaterial(materialID, type);
        }
        else if (type.equals("Journal") || type.equals("Magazine")){
            saved = registeredMaterial.UpdateUnauthoredMaterial(materialID, type);
        }

        if (saved == false){
            System.out.println("Error saving available copies.");
            return;
        }

        System.out.println("Material has been successfully returned.");
        System.out.println("Material Name: " + registeredMaterial.getTitle());
        System.out.println("Date Returned: " + returnDate);
    }
}
