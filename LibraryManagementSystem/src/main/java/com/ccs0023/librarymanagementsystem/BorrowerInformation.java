package com.ccs0023.librarymanagementsystem;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowerInformation {
    
    ArrayList<BorrowersManagement> borrower = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    int choice;
    
    public void borrower(){
        
        do{
            System.out.println("1. Add Borrower");
            System.out.println("2. Edit Borrower");
            System.out.println("3. Delete Borrower");
            System.out.println("4. View Borrower");
            choice = scan.nextInt();
            
            switch(choice){
                case 1:
                    addBorrower();
                    break;
                case 2:
                    editBorrower();
                    break;
                case 3:
                    deleteBorrower();
                    break;
                case 4:
                    viewBorrower();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }
            
        } while(true);
    }
    
    public void addBorrower(){
        
        BorrowersManagement registeredBorrower = new BorrowersManagement();
        
        System.out.println("Enter Borrower ID Number: ");
        int BorrowerIdNumber = scan.nextInt();
        registeredBorrower.setBorrowerIdNumber(BorrowerIdNumber);
        scan.nextLine();
        
        System.out.println("Enter Firstname: ");
        String firstName = scan.nextLine();
        
        registeredBorrower.setFirstName(firstName);
        
        System.out.println("Enter Middlename: ");
        String middleName = scan.nextLine();
        
        registeredBorrower.setMiddleName(middleName);
        
        System.out.println("Enter Lastname: ");
        String lastName = scan.nextLine();
        
        registeredBorrower.setLastName(lastName);
        
        System.out.println("Enter Gender: ");
        String gender = scan.nextLine();
        
        registeredBorrower.setGender(gender);
        
        System.out.println("Enter birthday: ");
        String birthday = scan.nextLine();
        
        registeredBorrower.setBirthday(birthday);
        
        System.out.println("Enter Contact Number: ");
        String contactNumber = scan.nextLine();
        
        registeredBorrower.setContactNumber(contactNumber);
        
        System.out.println("Enter Email: ");
        String email = scan.nextLine();
        
        registeredBorrower.setEmail(email);
        
        System.out.println("Enter Address: ");
        String address = scan.nextLine();
        
        registeredBorrower.setAddress(address);
        
        borrower.add(registeredBorrower);
        
        System.out.println("New borrower has been successfully added to the system.");
    }
    
    public void editBorrower(){
        
    }
    
    public void deleteBorrower(){
        
    }
    
    public void viewBorrower(){
        
        System.out.println("All Records of Registered Borrowers");
        
        for (BorrowersManagement registeredBorrower : borrower){
            
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
        
    }

    
    
    
    
}
