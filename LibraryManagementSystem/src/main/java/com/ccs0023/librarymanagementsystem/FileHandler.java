package com.ccs0023.librarymanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    public void FileHandler(){}
    
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> records = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                records.add(line);
                line = reader.readLine();
            }

            reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }catch (IOException e) {
            System.out.println("Error reading file");
        }

        return records;
    }

    public void appendFile(String filename, String data) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.append(data);
            writer.newLine();
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(IOException e){
            System.out.println("Error");
        }
    }
    
    public void updateFile(String filename, String uniqueID, String newData) {
        ArrayList<String> records = readFile(filename);
        int size = records.size();
        for (int i = 0; i < size; i++) {
            String record = records.get(i);
            String[] values = record.split(",");
            if (values[0].equals(uniqueID)) {
                records.set(i, newData);
                break;
            }
        }
    }
    
    public void BorrowerUpdate(String borrowerID, String valueEdited){
        
        updateFile("borrowers.txt", borrowerID, valueEdited);
    }
    
    
    public ArrayList<String> readBorrower(){
        return readFile("borrowers.txt");
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
    
