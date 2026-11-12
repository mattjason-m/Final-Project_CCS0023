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

    if (Validator.PositiveNumber(BorrowerIdNumber) == false){
        System.out.println("Invalid Borrower ID Number.");
        return;
    }

    if (searchBorrower(BorrowerIdNumber) != null){
        System.out.println("Borrower ID Number is already registered.");
        return;
    }

    registeredBorrower.setBorrowerIdNumber(BorrowerIdNumber);

    
    System.out.println("Enter Firstname: ");
    String firstName = Function.capitalizer(scan.nextLine());

    if (Validator.Name(firstName) == false){
        System.out.println("Invalid First Name.");
        return;
    }

    registeredBorrower.setFirstName(firstName);

    
    System.out.print("Enter Middle Name (Enter NONE if not applicable): ");
    String middleName = Function.capitalizer(scan.nextLine().trim());

    if (middleName.isEmpty() || middleName.equalsIgnoreCase("NONE")) {
        middleName = "N/A";
    } else if (Validator.Name(middleName) == false) {
        System.out.println("Invalid Middle Name.");
        return;
    }

   registeredBorrower.setMiddleName(middleName);

    
    System.out.println("Enter Lastname: ");
    String lastName = Function.capitalizer(scan.nextLine());

    if (Validator.Name(lastName) == false){
        System.out.println("Invalid Last Name.");
        return;
    }

    registeredBorrower.setLastName(lastName);

    System.out.println("Enter Gender: ");
    String gender = Function.capitalizer(scan.nextLine());

    if (Validator.Gender(gender) == false){
        System.out.println("Invalid Gender.");
        return;
    }

    registeredBorrower.setGender(gender);

    System.out.println("Enter birthday (YYYY-MM-DD): ");
    String birthday = scan.nextLine();

    if (Validator.Birthday(birthday) == false){
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

    if (Validator.ContactNumber(contactNumber) == false){
        System.out.println("Invalid Contact Number.");
        return;
    }

    registeredBorrower.setContactNumber(contactNumber);

    System.out.println("Enter Email: ");
    String email = scan.nextLine();

    if (Validator.Email(email) == false){
        System.out.println("Invalid Email.");
        return;
    }

    registeredBorrower.setEmail(email);

    System.out.println("Enter Address: Separate by spaces not commas)");
    String address = Function.capitalizer(scan.nextLine());

    if (Validator.Address(address) == false){
        System.out.println("Invalid Address.");
        return;
    }

    registeredBorrower.setAddress(address);
    
    borrower.add(registeredBorrower);
    registeredBorrower.AddRecord();
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
                    String changedfirstName = Function.capitalizer(scan.nextLine());

                    if (Validator.Name(changedfirstName) == false){
                        System.out.println("Invalid First Name.");
                        break;
                    }

                    if (checkDuplicateBorrower(changedfirstName, registeredBorrower.getMiddleName(),
                            registeredBorrower.getLastName(), registeredBorrower.getBirthday(), borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setFirstName(changedfirstName); // update to RAM
                    registeredBorrower.UpdateRecord(borrowerIdNumber); // update to text file
                    System.out.println("First Name has been updated successfully.");
                    break;

                case 2:
                    System.out.println("Edit Middle Name: ");
                    String changedmiddleName = Function.capitalizer(scan.nextLine());

                    if (!changedmiddleName.trim().isEmpty()){
                        if (Validator.Name(changedmiddleName) == false){
                            System.out.println("Invalid Middle Name.");
                            break;
                        }
                    }

                    if (checkDuplicateBorrower(registeredBorrower.getFirstName(), changedmiddleName,
                            registeredBorrower.getLastName(), registeredBorrower.getBirthday(), borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setMiddleName(changedmiddleName);
                    registeredBorrower.UpdateRecord(borrowerIdNumber); 
                    System.out.println("Middle Name has been updated successfully.");
                    break;

                case 3:
                    System.out.println("Edit Last Name: ");
                    String changedlastName = Function.capitalizer(scan.nextLine());

                    if (Validator.Name(changedlastName) == false){
                        System.out.println("Invalid Last Name.");
                        break;
                    }

                    if (checkDuplicateBorrower(registeredBorrower.getFirstName(),
                            registeredBorrower.getMiddleName(), changedlastName,
                            registeredBorrower.getBirthday(), borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        
                        break;
                    }

                    registeredBorrower.setLastName(changedlastName);
                    registeredBorrower.UpdateRecord(borrowerIdNumber); 
                    System.out.println("Last Name has been updated successfully."); 
                    break;

                case 4:
                    System.out.println("Edit Gender: ");
                    String changedgender = Function.capitalizer(scan.nextLine());

                    if (Validator.Gender(changedgender) == false){
                        System.out.println("Invalid Gender.");
                        break;
                    }

                    registeredBorrower.setGender(changedgender);
                    registeredBorrower.UpdateRecord(borrowerIdNumber);
                    System.out.println("Gender has been updated successfully.");                    
                    break;

                case 5:
                    System.out.println("Edit Birthday (YYYY-MM-DD): ");
                    String changedbirthday = scan.nextLine();

                    if (Validator.Birthday(changedbirthday) == false){
                        System.out.println("Invalid Birthday.");
                        break;
                    }

                    if (checkDuplicateBorrower(registeredBorrower.getFirstName(),
                            registeredBorrower.getMiddleName(), registeredBorrower.getLastName(),
                            changedbirthday, borrowerIdNumber)){
                        System.out.println("Borrower is already registered.");
                        break;
                    }

                    registeredBorrower.setBirthday(changedbirthday);
                    registeredBorrower.UpdateRecord(borrowerIdNumber);
                    System.out.println("Birthday has been updated successfully.");
                    break;

                case 6:
                    System.out.println("Edit Contact Number: ");
                    String changedcontactNumber = scan.nextLine();

                    if (Validator.ContactNumber(changedcontactNumber) == false){
                        System.out.println("Invalid Contact Number.");
                        break;
                    }

                    registeredBorrower.setContactNumber(changedcontactNumber);
                    registeredBorrower.UpdateRecord(borrowerIdNumber);
                    System.out.println("Contact Number has been updated successfully.");
                    break;

                case 7:
                    System.out.println("Edit Email: ");
                    String changedemail = scan.nextLine();

                    if (Validator.Email(changedemail) == false){
                        System.out.println("Invalid Email.");
                        break;
                    }

                    registeredBorrower.setEmail(changedemail);
                    registeredBorrower.UpdateRecord(borrowerIdNumber);
                    System.out.println("Email has been updated successfully.");
                    break;

                case 8:
                    System.out.println("Edit Address (Separate by spaces not commas): ");
                    String changedaddress = Function.capitalizer(scan.nextLine());

                    if (Validator.Address(changedaddress) == false){
                        System.out.println("Invalid Address.");
                        break;
                    }

                    registeredBorrower.setAddress(changedaddress);
                    registeredBorrower.UpdateRecord(borrowerIdNumber);
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
            borrower.remove(registeredBorrower); // delete in RAM
            registeredBorrower.DeleteRecord(borrowerIdNumber); //delete in txt
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
                Function.BorrowersPrintValues(registeredBorrower);
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
                Function.BorrowersPrintValues(registeredBorrower);
            }

            else {
                System.out.println("Borrower does not exists.");
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
                    && birthday.equals(registeredBorrower.getBirthday()))
                    {
                    return true;
                    }
                }
            }
            return false;
        }

    public void loadBorrowers() {
        FileHandler file = new FileHandler();
        ArrayList<String> records = file.readBorrower();
        for (String record : records) {
               try {
                    String[] values = record.split(",");

                    if (values.length != 10) {
                        System.out.println("Invalid borrower record.");
                        continue; 
                    }
                    BorrowersManagement registeredBorrower = new BorrowersManagement();

                    registeredBorrower.setBorrowerIdNumber(Integer.parseInt(values[0]));
                    registeredBorrower.setFirstName(values[1]);
                    registeredBorrower.setMiddleName(values[2]);
                    registeredBorrower.setLastName(values[3]);
                    registeredBorrower.setGender(values[4]);
                    registeredBorrower.setBirthday(values[5]);
                    registeredBorrower.setContactNumber(values[6]);
                    registeredBorrower.setEmail(values[7]);
                    registeredBorrower.setAddress(values[8]);
                    registeredBorrower.setViolations(Integer.parseInt(values[9]));

                    borrower.add(registeredBorrower);
               }
                catch(NumberFormatException e) {
                    System.out.println("Invalid number in borrower record.");
                }
        }
    }
    
    
    
}
