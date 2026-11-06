package com.ccs0023.librarymanagementsystem;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowManagement {

    public static void processBorrowTransaction(Scanner scanner, ArrayList<String> registeredBorrowers, 
                                                ArrayList<String> assetList, ArrayList<Integer> assetCopies, 
                                                ArrayList<Integer> borrowerStrikes, ArrayList<Integer> borrowerActiveBorrows) {
        try {
            System.out.println("\n--- LIBRARY ASSET BORROWING SYSTEM ---");

            
            System.out.print("Enter Borrower ID to proceed: ");
            String borrowerId = scanner.nextLine().trim();

            if (!registeredBorrowers.contains(borrowerId)) {
                System.out.println("Error: Borrower is not registered in the system. Borrowing denied.");
                return;
            }

          
            int borrowerIndex = registeredBorrowers.indexOf(borrowerId);

            
            if (borrowerStrikes.get(borrowerIndex) >= 3) {
                System.out.println("Error: Borrower has 3 or more strikes on record. Borrowing privileges revoked.");
                return;
            }

            
            if (borrowerActiveBorrows.get(borrowerIndex) == 1) {
                System.out.println("Error: Borrower already has an active borrowed material. Please return it first.");
                return;
            }

            if (assetList.isEmpty()) {
                System.out.println("Error: No assets currently available in the library.");
                return;
            }

            System.out.println("\nAvailable Library Assets:");
            for (int i = 0; i < assetList.size(); i++) {
                System.out.println((i + 1) + ". " + assetList.get(i) + " | Available Copies: " + assetCopies.get(i));
            }

           
            System.out.print("Enter the number of the asset you want to borrow: ");
            String inputStr = scanner.nextLine().trim();
            
            int choice = Integer.parseInt(inputStr);

            if (choice < 1 || choice > assetList.size()) {
                System.out.println("Error: Invalid asset selection number.");
                return;
            }

            int assetIndex = choice - 1;

          
            if (assetCopies.get(assetIndex) <= 0) {
                System.out.println("Error: No available copies left for this material.");
                return;
            }

            
            String selectedAsset = assetList.get(assetIndex);
            int returnDays = getReturnDays(selectedAsset);

            
            assetCopies.set(assetIndex, assetCopies.get(assetIndex) - 1);
            borrowerActiveBorrows.set(borrowerIndex, 1); 

            System.out.println("\nSuccess! Material successfully borrowed.");
            System.out.println("Borrowed Item: " + selectedAsset);
            System.out.println("Please note: This item must be returned within " + returnDays + " day(s).");

        } catch (NumberFormatException e) {
            System.out.println("Input Error: Please enter a valid numeric value where required.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during the borrow process: " + e.getMessage());
        }
    }

    
    private static int getReturnDays(String assetName) {
        String lowerName = assetName.toLowerCase();
        if (lowerName.contains("journal")) {
            return 3;
        } else if (lowerName.contains("magazine")) {
            return 0; 
        } else if (lowerName.contains("thesis")) {
            return 2;
        } else {
            
            return 7;
        }
    }
}