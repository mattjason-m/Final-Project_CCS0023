package com.ccs0023.librarymanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    
    public void readFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(IOException e){
            System.out.println("Error reading file");
        }
    }

    public void appendFile(String filename, String data) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.append(data);
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    
    public void updateFile(String filename, String data) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.append(data);
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    
    public void readBorrower(){
        readFile("borrowers.txt");
    }
    
    public void readTransaction(){
        readFile("transactions.txt");
    }
    public void readMatereials(){
        readFile("materials.txt");
    }
    
    public void addBorrower(String input){
        appendFile("borrowers.txt", input);
    }
    
    public void addTransaction(String input){
        appendFile("transactions.txt", input);
    }
    public void addMaterials(String input){
        appendFile("materials.txt", input);
    }   
    
    
    
}
    
