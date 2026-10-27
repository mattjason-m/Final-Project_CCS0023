package com.ccs0023.librarymanagementsystem;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Test, Matt JaSON!");

        Person student = new Person();

        ArrayList<Person> students = new ArrayList<>();
    
        System.out.print("EnterID: ");
        int testinglanginput = input.nextInt();
        student.setIdNumber(testinglanginput);
        
        System.out.println("Entered ID: " + student.getIdNumber());
       
                
        File file = new File("students.txt");
        
            
        
        try {
            if (file.createNewFile()) {
                System.out.println("File created!");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error creating file!");
        }   
        
        
        try {
        FileWriter writer = new FileWriter("students.txt", true);
        int add = student.getIdNumber();
        writer.write(String.valueOf(add) +  "\n");
        writer.close();
        } 
        catch (IOException e) {
        System.out.println("File error: " + e.getMessage());
        }   
        
        
        input.close();
       
        
    }
}
