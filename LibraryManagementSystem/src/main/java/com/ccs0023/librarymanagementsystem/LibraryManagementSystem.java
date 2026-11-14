package com.ccs0023.librarymanagementsystem;

import java.util.InputMismatchException;
import java.util.Scanner;


public class LibraryManagementSystem {
        public void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            //initialize all class
            BorrowerInformation borrowerSystem = new BorrowerInformation(); 
            AssetManagement assetSystem =  new AssetManagement();      
            BorrowAMaterial borrowingSystem = new BorrowAMaterial();
            Return returnSystem = new Return();
            BookAndBorrowerHistory historySystem = new BookAndBorrowerHistory();
            
        int choice = 0;
        do{
            printMenu();
            try{
                choice = scan.nextInt();
                switch(choice){
                    case 1:
                        borrowerSystem.loadBorrowers(); //load existing file method
                        borrowerSystem.borrowerTerminal(); //start function
                        break;
                    case 2:
                        assetSystem.loadAsset();
                        assetSystem.assetTerminal();
                        break;
                    case 3:
                        borrowingSystem.Terminal();
                        break;
                    case 4:
                        returnSystem.returnBook();
                        break;      
                    case 5:
                        historySystem.borrowerHistory();
                        break;
                    case 6:
                        historySystem.bookHistory();
                        break;
                    case 7:
                        System.out.println("Exiting Program...");
                        break;
                    default:
                        System.out.println("Enter Valid Choice.");
                        break;
               
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a Number.");
                System.out.println();
                scan.nextLine();
            }
        }while(choice != 7);
    }
        
    public void printMenu(){
            System.out.println("============================");
            System.out.println(" LIBRARY MANAGEMENT SYSTEM  ");
            System.out.println("============================");
            System.out.println();
            System.out.println("[1] BORROWERS MANAGEMENT");
            System.out.println("[2] ASSET MANAGEMENT");
            System.out.println("[3] BORROW");
            System.out.println("[4] RETURN");
            System.out.println("[5] BORROWER HISTORY");
            System.out.println("[6] BOOK HISTORY");
            System.out.println("[7] EXIT");
            System.out.println("----------------------------");
            System.out.print("Enter Choice: ");
    }
}
