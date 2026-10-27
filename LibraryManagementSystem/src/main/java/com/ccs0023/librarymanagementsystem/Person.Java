package com.ccs0023.librarymanagementsystem;
//borrower ID number, first name, middle name, last name, gender, birthday, contact number, email, address, and number of violations.

public class Person {
        int idNumber;
        String firstName;
        String middleName;
        String lastName;
        String gender;
        String birthday;
        String contactNumber;
        String email;
        String address;
        int violations;
        
        public Person(){
            idNumber = 0;
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
        
        public int getIdNumber() {
            return idNumber;
        }

        public String getfirstName() {
            return firstName;
        }

        public String getmiddleName() {
            return middleName;
        }

        public String getlastName() {
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


        public void setIdNumber(int enteredID) {
            idNumber = enteredID;
        }

        public void setfirstName(String enteredFName) {
            firstName = enteredFName;
        }

        public void setmiddleName(String enteredMName) {
            middleName = enteredMName;
        }

        public void setlastName(String enteredLName) {
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
        
        public void addViolation(){
            violations++;
        }
}
