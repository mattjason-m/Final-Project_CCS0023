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
    
    
    
    
    
    
}
