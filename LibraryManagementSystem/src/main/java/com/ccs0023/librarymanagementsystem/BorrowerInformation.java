package com.ccs0023.librarymanagementsystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

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
        System.out.println("5. Return to Main Menu");

        try{
            choice = scan.nextInt();
            scan.nextLine();

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
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
        }

    }while(true);
}
    
   
public void addBorrower(){

    BorrowersManagement registeredBorrower = new BorrowersManagement();

    int BorrowerIdNumber;

    try{
        System.out.println("Enter Borrower ID Number: ");
        BorrowerIdNumber = scan.nextInt();
        scan.nextLine();
    }
    catch(InputMismatchException e){
        System.out.println("Invalid input. Please enter a valid Borrower ID Number.");
        scan.nextLine();
        return;
}

    if (validatePositiveNumber(BorrowerIdNumber) == false){
        System.out.println("Invalid Borrower ID Number.");
        return;
    }

    if (searchBorrower(BorrowerIdNumber) != null){
        System.out.println("Borrower ID Number is already registered.");
        return;
    }

    registeredBorrower.setBorrowerIdNumber(BorrowerIdNumber);

    System.out.println("Enter Firstname: ");
    String firstName = scan.nextLine();

    if (validateName(firstName) == false){
        System.out.println("Invalid First Name.");
        return;
    }

    registeredBorrower.setFirstName(firstName);

    System.out.println("Enter Middlename: ");
    String middleName = scan.nextLine();

    if (!middleName.trim().isEmpty()){
        if (validateName(middleName) == false){
            System.out.println("Invalid Middle Name.");
            return;
        }
    }

    registeredBorrower.setMiddleName(middleName);

    System.out.println("Enter Lastname: ");
    String lastName = scan.nextLine();

    if (validateName(lastName) == false){
        System.out.println("Invalid Last Name.");
        return;
    }

    registeredBorrower.setLastName(lastName);

    System.out.println("Enter Gender: ");
    String gender = scan.nextLine();

    if (validateGender(gender) == false){
        System.out.println("Invalid Gender.");
        return;
    }

    registeredBorrower.setGender(gender);

    System.out.println("Enter birthday (YYYY-MM-DD): ");
    String birthday = scan.nextLine();

    if (validateBirthday(birthday) == false){
        System.out.println("Invalid Birthday.");
        return;
    }

    registeredBorrower.setBirthday(birthday);

    if (checkDuplicateBorrower(firstName, middleName, lastName, birthday)){
        System.out.println("Borrower is already registered.");
        return;
    }

    System.out.println("Enter Contact Number: ");
    String contactNumber = scan.nextLine();

    if (validateContactNumber(contactNumber) == false){
        System.out.println("Invalid Contact Number.");
        return;
    }

    registeredBorrower.setContactNumber(contactNumber);

    System.out.println("Enter Email: ");
    String email = scan.nextLine();

    if (validateEmail(email) == false){
        System.out.println("Invalid Email.");
        return;
    }

    registeredBorrower.setEmail(email);

    System.out.println("Enter Address: ");
    String address = scan.nextLine();

    if (validateAddress(address) == false){
        System.out.println("Invalid Address.");
        return;
    }

    registeredBorrower.setAddress(address);

    borrower.add(registeredBorrower);

    System.out.println("New borrower has been successfully added to the system.");
}

    
    
public void editBorrower(){

    int borrowerIdNumber;

    try{
        System.out.println("Enter Borrower ID Number to be Edited");
        borrowerIdNumber = scan.nextInt();
        scan.nextLine();
    }
    catch(InputMismatchException e){
        System.out.println("Invalid input. Please enter a valid Borrower ID Number.");
        scan.nextLine();
        return;
}

    BorrowersManagement registeredBorrower = searchBorrower(borrowerIdNumber);

    if (registeredBorrower != null){
        do{
            System.out.println("Edit Borrower Information");
            System.out.println("Borrower ID: " + borrowerIdNumber);
            System.out.println("\n1. Edit First Name");
            System.out.println("2. Edit Middle Name");
            System.out.println("3. Edit Last Name");
            System.out.println("4. Edit Gender");
            System.out.println("5. Edit Birthday");
            System.out.println("6. Edit Contact Number");
            System.out.println("7. Edit Email");
            System.out.println("8. Edit Address");
            System.out.println("9. Return to Borrower Management");

            int choice;

            try{
                System.out.println("Enter Choice: ");
                choice = scan.nextInt();
                scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number from 1 to 9.");
                scan.nextLine();
                continue;
            }

            switch(choice){
                case 1:
                    System.out.println("Edit First Name: ");
                    String firstName = scan.nextLine();

                    if (validateName(firstName) == false){
                        System.out.println("Invalid First Name.");
                        break;
                    }

                    if (checkDuplicateBorrower(firstName, registeredBorrower.getMiddleName(),
                            registeredBorrower.getLastName(), registeredBorrower.getBirthday(), borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setFirstName(firstName);
                    System.out.println("First Name has been updated successfully.");
                    break;

                case 2:
                    System.out.println("Edit Middle Name: ");
                    String middleName = scan.nextLine();

                    if (!middleName.trim().isEmpty()){
                        if (validateName(middleName) == false){
                            System.out.println("Invalid Middle Name.");
                            break;
                        }
                    }

                    if (checkDuplicateBorrower(registeredBorrower.getFirstName(), middleName,
                            registeredBorrower.getLastName(), registeredBorrower.getBirthday(), borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setMiddleName(middleName);
                    System.out.println("Middle Name has been updated successfully.");
                    break;

                case 3:
                    System.out.println("Edit Last Name: ");
                    String lastName = scan.nextLine();

                    if (validateName(lastName) == false){
                        System.out.println("Invalid Last Name.");
                        break;
                    }

                    if (checkDuplicateBorrower(registeredBorrower.getFirstName(),
                            registeredBorrower.getMiddleName(), lastName,
                            registeredBorrower.getBirthday(), borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setLastName(lastName);
                    System.out.println("Last Name has been updated successfully.");
                    break;

                case 4:
                    System.out.println("Edit Gender: ");
                    String gender = scan.nextLine();

                    if (validateGender(gender) == false){
                        System.out.println("Invalid Gender.");
                        break;
                    }

                    registeredBorrower.setGender(gender);
                    System.out.println("Gender has been updated successfully.");
                    break;

                case 5:
                    System.out.println("Edit Birthday (YYYY-MM-DD): ");
                    String birthday = scan.nextLine();

                    if (validateBirthday(birthday) == false){
                        System.out.println("Invalid Birthday.");
                        break;
                    }

                    if (checkDuplicateBorrower(registeredBorrower.getFirstName(),
                            registeredBorrower.getMiddleName(), registeredBorrower.getLastName(),
                            birthday, borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setBirthday(birthday);
                    System.out.println("Birthday has been updated successfully.");
                    break;

                case 6:
                    System.out.println("Edit Contact Number: ");
                    String contactNumber = scan.nextLine();

                    if (validateContactNumber(contactNumber) == false){
                        System.out.println("Invalid Contact Number.");
                        break;
                    }

                    registeredBorrower.setContactNumber(contactNumber);
                    System.out.println("Contact Number has been updated successfully.");
                    break;

                case 7:
                    System.out.println("Edit Email: ");
                    String email = scan.nextLine();

                    if (validateEmail(email) == false){
                        System.out.println("Invalid Email.");
                        break;
                    }

                    registeredBorrower.setEmail(email);
                    System.out.println("Email has been updated successfully.");
                    break;

                case 8:
                    System.out.println("Edit Address: ");
                    String address = scan.nextLine();

                    if (validateAddress(address) == false){
                        System.out.println("Invalid Address.");
                        break;
                    }

                    registeredBorrower.setAddress(address);
                    System.out.println("Address has been updated successfully.");
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid choice from 1 to 9");
            }
        }while(true);
    }
    else {
        System.out.println("Borrower does not exist.");
    }
}

    
    public void deleteBorrower(){
        
        int borrowerIdNumber;

        try{
            System.out.println("Enter Borrower ID Number to be Deleted");
            borrowerIdNumber = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Borrower ID Number.");
            scan.nextLine();
            return;
        }
            
        BorrowersManagement registeredBorrower = searchBorrower(borrowerIdNumber);
        
        if (registeredBorrower != null){
            borrower.remove(registeredBorrower);
            System.out.println("Borrower has been successfully removed and no longer exixts.");
            }
        
        else{
            System.out.println("Borrower does not exists.");
        }
        
    }
    
    public void viewBorrower(){
        
        // We decided for the viewBorrower have another option, which is to view all the borrowers and to view a specific borrower by searching for their id
        
        int choice;
        
        do{
            System.out.println("View Borrower\n");
            System.out.println("1. View All Borrower");
            System.out.println("2. View Specific Borrower");
            System.out.println("3. Return to Borrowers Management");
            
            try{
            choice = scan.nextInt();
            scan.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a number from 1 to 3.");
                scan.nextLine();
                continue;
            }
            
            switch(choice){
                case 1:
                    viewAllBorrower();
                    break;
                case 2:
                    viewSpecificBorrower();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice from 1 to 3.");
            }
        }while(true);
        
    }
    
    public void viewAllBorrower(){
        
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
            
    public BorrowersManagement searchBorrower(int borrowerIdNumber){
        
        for (BorrowersManagement registeredBorrower : borrower){
            
            if (borrowerIdNumber == registeredBorrower.getBorrowerIdNumber()){
            return registeredBorrower;
            }
        }
        
         return null;  
    }
    
    public void viewSpecificBorrower(){
        
        int borrowerIdNumber;

        try{
            System.out.println("Enter Borrower's ID to view: ");
            borrowerIdNumber = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Borrower ID Number.");
            scan.nextLine();
            return;
        }
        
        BorrowersManagement registeredBorrower = searchBorrower(borrowerIdNumber);
        
        if (registeredBorrower != null){
            
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
        
        else {
            System.out.println("Borrower does not exists.");
        }
        

    }
    
    public boolean validatePositiveNumber(int number){

        if (number > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateName(String name){

        if (name.trim().isEmpty()){
            return false;
        }

        if (name.matches("[a-zA-Z .'-]+")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateGender(String gender){

        if (gender.equalsIgnoreCase("Male")){
            return true;
        }
        else if (gender.equalsIgnoreCase("Female")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateBirthday(String birthday){

        if (!birthday.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
            return false;
        }

        String[] date = birthday.split("-");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        if (year < 1900 || year > 2026){
            return false;
        }

        if (month < 1 || month > 12){
            return false;
        }

        int daysInMonth = 31;

        if (month == 4 || month == 6 || month == 9 || month == 11){
            daysInMonth = 30;
        }
        else if (month == 2){
            daysInMonth = 28;

            if (year % 400 == 0){
                daysInMonth = 29;
            }
            else if (year % 4 == 0 && year % 100 != 0){
                daysInMonth = 29;
            }
        }

        if (day < 1 || day > daysInMonth){
            return false;
        }

        return true;
    }

    public boolean validateContactNumber(String contactNumber){

        if (contactNumber.matches("09[0-9]{9}")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateEmail(String email){

        if (email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean validateAddress(String address){

        if (address.trim().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkDuplicateBorrower(String firstName, String middleName,
            String lastName, String birthday){

        return checkDuplicateBorrower(firstName, middleName, lastName, birthday, -1);
    }

    public boolean checkDuplicateBorrower(String firstName, String middleName,
            String lastName, String birthday, int borrowerIdNumber){

        for (BorrowersManagement registeredBorrower : borrower){

            if (registeredBorrower.getBorrowerIdNumber() != borrowerIdNumber){

                if (firstName.trim().equalsIgnoreCase(registeredBorrower.getFirstName().trim())
                        && middleName.trim().equalsIgnoreCase(registeredBorrower.getMiddleName().trim())
                        && lastName.trim().equalsIgnoreCase(registeredBorrower.getLastName().trim())
                        && birthday.equals(registeredBorrower.getBirthday())){

                    return true;
                }
            }
        }

        return false;
    }
// hi world
}
