package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;
public class Validator {
    
    public static boolean PositiveNumber(int number){
        if(number > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean Name(String name){

        if (name.trim().isEmpty()){
            return false;
        }

        if (name.matches("[A-Z .'-]+")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean Gender(String gender){

        if (gender.equalsIgnoreCase("MALE")){
            return true;
        }
        else if (gender.equalsIgnoreCase("FEMALE")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean Birthday(String birthday){

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

    public static boolean ContactNumber(String contactNumber){

        if (contactNumber.matches("09[0-9]{9}")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean Email(String email){

        if (email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean Address(String address){

        if (address.trim().isEmpty()){
            return false;
        }
        if (address.matches("[A-Z0-9 #.'/-]+")){
            return true;
        }
        else{
            return false;
        }
    }
     
    public static boolean Year(int yearPublished){
     
        if (yearPublished > 868 && yearPublished <= Function.SetCurrentYear()){
            return true;
        }
        else{
            return false;
        }
    } // initial year is based on the first book published Diamond Sutra source: University of Oxford. (n.d.). The world's earliest dated printed book: The Diamond Sutra (868 CE). Cabinet. https://www.cabinet.ox.ac.uk/worlds-earliest-dated-printed-book-diamond-sutra-868-ce
 
    public static boolean Text(String text){
        
        if (text.trim().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static boolean NoComma(String title) {
    if (title.trim().isEmpty()) {
        return false;
    }
    if (title.contains(",")){
        System.out.println("Use of ',' is not allowed.");
        return false;
    }
    return true;
    }
    
    
}
