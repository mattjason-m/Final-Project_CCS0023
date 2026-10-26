package com.ccs0023.librarymanagementsystem;
//borrower ID number, first name, middle name, last name, gender, birthday, contact number, email, address, and number of violations.

public class Person {
        private int idNumber;
        private String firstName;
        private String middleName;
        private String lastName;
        private String gender;
        private String birthday;
        private int contactNumber;
        private String email;
        private String address;
        private int violations;
        
        public Person(){
            idNumber = 0;
            firstName = "First Name";
            middleName = "Middle Name";
            lastName = "Last Name";
            gender = "Gender";
            birthday = "Birthday";
            contactNumber = 0;
            email = "@gmail.com";
            address = "Street, Brgy., Municipality/City, Province, Region";
            violations = 0;
        }
        
        public int getIdNumber() {
            return idNumber;
        }
}
