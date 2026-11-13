package com.ccs0023.librarymanagementsystem;
//borrower ID number, first name, middle name, last name, gender, birthday, contact number, email, address, and number of violations.

public class BorrowersManagement {
       private int BorrowerIdNumber;
       private String firstName;
       private String middleName;
       private String lastName;
       private String gender;
       private String birthday;
       private String contactNumber;
       private String email;
       private String address;
       private int violations;
        
        public BorrowersManagement(){
            BorrowerIdNumber = 0;
            firstName = "First Name";
            middleName = "Middle Name";
            lastName = "Last Name";
            gender = "Gender";
            birthday = "Birthday";
            contactNumber = "0";
            email = "@gmail.com"; 
            address = "Street, Brgy., Municipality/City, Province, Region";
            violations = 0;
        }
        
        public int getBorrowerIdNumber() {
            return BorrowerIdNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getGender() {
            return gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public int getViolations(){ 
            return violations;
        } 

        public void setBorrowerIdNumber(int enteredID) {
            BorrowerIdNumber = enteredID;
        }

        public void setFirstName(String enteredFName) {
            firstName = enteredFName;
        }

        public void setMiddleName(String enteredMName) {
            middleName = enteredMName;
        }

        public void setLastName(String enteredLName) {
            lastName = enteredLName;
        }

        public void setGender(String enteredGender) {
            gender = enteredGender;
        }

        public void setBirthday(String enteredBirthday) {
            birthday = enteredBirthday;
        }

        public void setContactNumber(String enteredContactNumber) {
            contactNumber = enteredContactNumber;
        }

        public void setEmail(String enteredEmail) {
            email = enteredEmail;
        }

        public void setAddress(String enteredAddress) {
            address = enteredAddress;
        }
        
        public void setViolations(int enteredViolations) {
            violations = enteredViolations;
        }
        
        public void addViolation(){
            violations++;
        }
        
        public void AddRecord(){

            FileHandler FILE = new FileHandler();
            FILE.addBorrower(String.join(",",String.valueOf(BorrowerIdNumber), firstName, middleName,lastName,gender,
            birthday,contactNumber, email, address, String.valueOf(violations)));
            
        }
        
        public void AddRecord(int enteredID, String enteredFName, String enteredMName, String enteredLName, 
                String enteredGender, String enteredBirthday, String enteredContactNumber,String enteredEmail, String enteredAddress)
        {
            BorrowerIdNumber = enteredID;
            firstName = enteredFName;
            middleName = enteredMName;
            lastName = enteredLName;
            gender = enteredGender;
            birthday = enteredBirthday;
            contactNumber = enteredContactNumber;
            email = enteredEmail;
            address = enteredAddress;
            violations = 0;
            
            FileHandler FILE = new FileHandler();
            FILE.addBorrower(String.join(",",String.valueOf(BorrowerIdNumber), firstName, middleName,lastName,gender,
            birthday,contactNumber, email, address, String.valueOf(violations)));
        }

        public boolean UpdateRecord(int enteredID){
            FileHandler FILE = new FileHandler();
            
            String changedData = String.join(",",String.valueOf(BorrowerIdNumber), firstName, middleName,lastName,gender,
                                birthday,contactNumber, email, address, String.valueOf(violations));
            return FILE.BorrowerUpdate(enteredID, changedData);
        }
        
        public void DeleteRecord(int enteredID){
            FileHandler FILE = new FileHandler();
            FILE.BorrowerDelete(enteredID);
        }
        
}
