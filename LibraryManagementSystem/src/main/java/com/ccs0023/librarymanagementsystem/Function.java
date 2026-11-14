package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;

public class Function {
    
    public static void BorrowersTableHeader() {
        System.out.printf("%-12s %-25s %-15s %-15s %-10s %-12s %-15s %-35s %-40s %-10s%n",
                "Borrower ID", "First Name", "Middle Name", "Last Name",
                "Gender", "Birthday", "Contact", "Email", "Address", "Violations");
    }

    public static void BorrowersPrintValues(BorrowersManagement registeredBorrower) {
        System.out.printf("%-12d %-25s %-15s %-15s %-10s %-12s %-15s %-35s %-40s %-10d%n",
                registeredBorrower.getBorrowerIdNumber(),
                registeredBorrower.getFirstName(),
                registeredBorrower.getMiddleName(),
                registeredBorrower.getLastName(),
                registeredBorrower.getGender(),
                registeredBorrower.getBirthday(),
                registeredBorrower.getContactNumber(),
                registeredBorrower.getEmail(),
                registeredBorrower.getAddress(),
                registeredBorrower.getViolations());
    }
    
    public static String capitalizer (String input){
        String Capitalized = input.toUpperCase();
        return Capitalized;
    }
    
    private static final LocalDateTime TIMESTAMP = LocalDateTime.now();
    
    public static int SetCurrentYear(){
      return TIMESTAMP.getYear();
    }
    
    
    public static String SetCurrentDate(){
      return String.valueOf(TIMESTAMP);
    }
    
    public static String SetDeadline(String MaterialType){
         String TESTFINALVALUE;
        switch(MaterialType){
            case "Book":
                TESTFINALVALUE = String.valueOf(TIMESTAMP.plusDays(7));
                return TESTFINALVALUE;
            case "Journal":
                TESTFINALVALUE = String.valueOf(TIMESTAMP.plusDays(3));
                return TESTFINALVALUE;
            case "Magazine":
                TESTFINALVALUE = String.valueOf(TIMESTAMP.with(java.time.LocalTime.MAX));
                return TESTFINALVALUE;
            case "Thesis Book":
                TESTFINALVALUE = String.valueOf(TIMESTAMP.plusDays(2));
                return TESTFINALVALUE;
            default:
                System.out.println("Invalid Material");
                break;
            
        }
        
      return String.valueOf(TIMESTAMP);
    }
    
    
    
    
    
    
}
