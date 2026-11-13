package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BorrowAMaterial {
    ArrayList<Material> materials = new ArrayList<>();
    ArrayList<BorrowersManagement> borrower = new ArrayList<>();
    ArrayList<BorrowBlueprint> borrowRecords = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    
    public void Terminal(){
        // load to read files
        AssetManagement assetSystem = new AssetManagement();
        assetSystem.loadAsset();
        BorrowerInformation borrowerSystem = new BorrowerInformation();
        borrowerSystem.loadBorrowers();
        loadTransactions(assetSystem, borrowerSystem);
        
        int hihiramIDnumber = 0, materialID = 0;
 
        System.out.print("Enter Borrower ID Number:");
        try{
            hihiramIDnumber = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
                return;
        }
        if (Validator.PositiveNumber(hihiramIDnumber) == false){
        System.out.println("Invalid Borrower ID Number.");
        return;
        }
       
            BorrowersManagement borrowPerson = borrowerSystem.searchBorrower(hihiramIDnumber);
            if (borrowPerson == null) {
                System.out.println("Borrower not found.");
                return;
            }
            
            if (borrowPerson.getViolations() >=3){
                System.out.println("Borrower Exceeded Violation Limit.");
                return;
            }

            for (BorrowBlueprint registeredRecord : borrowRecords){

                if (hihiramIDnumber == registeredRecord.getBorrower().getBorrowerIdNumber()){

                    if (registeredRecord.getReturned() == false){
                        System.out.println("Borrower already has a borrowed material.");
                        return;
                    }
                }
            }
            
            String fullname = String.join(" ", borrowPerson.getFirstName(), borrowPerson.getMiddleName(), 
                    borrowPerson.getLastName());
            
            System.out.print("Enter Material ID Number:");
            try{
                materialID = scan.nextInt();
                scan.nextLine();
            }//GET ASSET ID NUMBER NADUDULING AKO
            catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number.");
                    scan.nextLine();
                    return;
            }
            
            Material MaterialSelected = assetSystem.searchMaterial(materialID);
            if (MaterialSelected == null) {
                System.out.println("Material not Found");
                return;
            }

            String data = String.join(",", String.valueOf(hihiramIDnumber), String.valueOf(materialID),
                    fullname, MaterialSelected.getTitle(), Function.SetCurrentDate(),
                    Function.SetDeadline(MaterialSelected.getMaterialType()), "", "false");

            if (SetBorrow(MaterialSelected)) {
                addRecord(data);
                System.out.println("Material Successfully Borrowed.");
            } else {
                System.out.println("Borrowing transaction failed.");
            }
    }

    //METHOD HERE
        
    public void loadTransactions(AssetManagement assetSystem, BorrowerInformation borrowerSystem) {

    borrowRecords.clear();

    FileHandler FILE = new FileHandler();
    ArrayList<String> records = FILE.readTransactions();

    for (String record : records) {
        try {
            String[] values = record.split(",", -1);

            if (values.length != 6 && values.length != 8) {
                System.out.println("Incomplete transaction record.");
                continue;
            }
            int borrowerID = Integer.parseInt(values[0]);
            int materialID = Integer.parseInt(values[1]);

            BorrowersManagement borrower = borrowerSystem.searchBorrower(borrowerID);
            Material material = assetSystem.searchMaterial(materialID);

            if (borrower == null || material == null) {
                System.out.println("Borrower or material not found.");
                continue;
            }
            LocalDateTime borrowDate = LocalDateTime.parse(values[4]);
            LocalDateTime dueDate = LocalDateTime.parse(values[5]);

            BorrowBlueprint recordedTransaction = new BorrowBlueprint(
                material, borrower, borrowDate, dueDate
            );

            if (values.length == 8){

                if (values[7].equals("true")){

                    recordedTransaction.setReturnDate(LocalDateTime.parse(values[6]));
                    recordedTransaction.setReturned(true);
                }
            }

            borrowRecords.add(recordedTransaction);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in transaction record.");
        }catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date in transaction record.");
        }
    }
}
    public void addRecord(String data){
        FileHandler FILE = new FileHandler();
        FILE.addTransaction(data);
    }
        
    public boolean SetBorrow(Material registeredMaterial){
        if (registeredMaterial.getAvailableCopies() <= 0) {
        System.out.println("No available copies.");
        return false;
        }
        
        int updatedCopies = registeredMaterial.getAvailableCopies();
        updatedCopies--;
        registeredMaterial.setAvailableCopies(updatedCopies);

        int materialID = registeredMaterial.getMaterialId();
        String type = registeredMaterial.getMaterialType();

        if (type.equals("Book") || type.equals("Thesis Book")) {
            registeredMaterial.UpdateAuthoredMaterial(materialID, type);
            return true;
            
        }
        else if (type.equals("Magazine") || type.equals("Journal")) {
            registeredMaterial.UpdateUnauthoredMaterial(materialID, type);
            return true;
        }
        return true;
    
    }

    
}
