package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class ReturnManagement {

    
    public static void processReturnTransaction(Scanner scanner, ArrayList<String> registeredBorrowers, 
                                                ArrayList<String> assetList, ArrayList<Integer> assetCopies, 
                                                ArrayList<Integer> borrowerStrikes, ArrayList<Integer> borrowerActiveBorrows) {
        try {
            System.out.println("\n--- LIBRARY ASSET RETURN SYSTEM ---");

          
            System.out.print("Enter Borrower ID returning the material: ");
            String borrowerId = scanner.nextLine().trim();

            if (!registeredBorrowers.contains(borrowerId)) {
                System.out.println("Error: Borrower is not registered in the system.");
                return;
            }

            int borrowerIndex = registeredBorrowers.indexOf(borrowerId);

            
            if (borrowerActiveBorrows.get(borrowerIndex) == 0) {
                System.out.println("Error: This borrower has no active borrowed materials to return.");
                return;
            }

           
            if (assetList.isEmpty()) {
                System.out.println("Error: No assets found in the library database.");
                return;
            }

            System.out.println("\nSelect the asset being returned:");
            for (int i = 0; i < assetList.size(); i++) {
                System.out.println((i + 1) + ". " + assetList.get(i));
            }

            System.out.print("Enter asset number: ");
            String assetInput = scanner.nextLine().trim();
            int assetChoice = Integer.parseInt(assetInput);

            if (assetChoice < 1 || assetChoice > assetList.size()) {
                System.out.println("Error: Invalid asset selection number.");
                return;
            }

            int assetIndex = assetChoice - 1;

          
            System.out.println("\nWas the material returned on time?");
            System.out.println("[1] Yes");
            System.out.println("[2] No (Late)");
            System.out.print("Enter your choice (1 or 2): ");
            String timeInput = scanner.nextLine().trim();
            int timeChoice = Integer.parseInt(timeInput);

            if (timeChoice == 1) {
                System.out.println("Status: Material returned on time. No strikes added.");
            } else if (timeChoice == 2) {
                
                int currentStrikes = borrowerStrikes.get(borrowerIndex);
                borrowerStrikes.set(borrowerIndex, currentStrikes + 1);
                System.out.println("Notice: Material is late! Borrower has received 1 strike. Total strikes: " + (currentStrikes + 1));
            } else {
                System.out.println("Error: Invalid choice. Transaction cancelled.");
                return;
            }

            
            assetCopies.set(assetIndex, assetCopies.get(assetIndex) + 1);
            borrowerActiveBorrows.set(borrowerIndex, 0); // Active borrow cleared

            System.out.println("\nSuccess! Material successfully returned and inventory updated.");

        } catch (NumberFormatException e) {
            System.out.println("Input Error: Please enter a valid numeric value where required.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during the return process: " + e.getMessage());
        }
    }
}