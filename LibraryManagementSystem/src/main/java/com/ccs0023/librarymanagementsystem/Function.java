package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;

public class Function {
    public static void BorrowersPrintValues(BorrowersManagement registeredBorrower){
            System.out.println(registeredBorrower.getBorrowerIdNumber());
            System.out.println(registeredBorrower.getFirstName());
            System.out.println(registeredBorrower.getMiddleName());
            System.out.println(registeredBorrower.getLastName());
            System.out.println(registeredBorrower.getGender());
            System.out.println(registeredBorrower.getBirthday());
            System.out.println(registeredBorrower.getContactNumber());
            System.out.println(registeredBorrower.getEmail());
            System.out.println(registeredBorrower.getAddress());
            System.out.println(registeredBorrower.getViolations());
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
