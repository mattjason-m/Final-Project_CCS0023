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

    //GENERAL METHOD/BLUEPRINT
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

    public void writeFile(String filename, String data, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append));
            writer.write(data);
            if (!data.isEmpty()) {
                writer.newLine();
            }
            writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(IOException e){
            System.out.println("Error");
        }
    }// to make write and rewriting more cleanre

    public void appendFile(String filename, String data) {
        writeFile(filename,data,true);
    }

    public boolean rewriteFile(String filename, ArrayList<String> UpdatedRecords) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (String record : UpdatedRecords) {
                writer.write(record);
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error rewriting file.");
            return false;
        }
    }

    public boolean updateFile(String filename, int uniqueID, String newData) {
        ArrayList<String> records = readFile(filename);
        int size = records.size();
        boolean matched = false;
        for (int i = 0; i < size; i++) {
            String record = records.get(i);
            String[] values = record.split(",");

            if (values[0].equals(String.valueOf(uniqueID))) {
                records.set(i, newData);
                matched = true;
                break;
            }
        }

        if (matched == true){
            return rewriteFile(filename, records);
        }
        else{
            return false;
        }
    }

    public void deleteFile(String filename, int uniqueID) {
        ArrayList<String> records = readFile(filename);
        int size = records.size();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            String record = records.get(i);
            String[] values = record.split(",");

            if (values[0].equals(String.valueOf(uniqueID))) {
                records.remove(i);
                found = true;
                break;
            }//id found and delete
        }// fimd first the uniqe id then delete
        if (found == true){
            rewriteFile(filename, records);
        }//id found rewriting data into the txt file
    }

    //BORROWER METHODS
    public void BorrowerDelete(int borrowerID){
        deleteFile("borrowers.txt", borrowerID);
    }

    public boolean BorrowerUpdate(int borrowerID, String valueEdited){
        return updateFile("borrowers.txt", borrowerID, valueEdited);
    }

    public ArrayList<String> readBorrower(){
        return readFile("borrowers.txt");
    }

    public void addBorrower(String input){
        appendFile("borrowers.txt", input);
    }

    //ASSET METHODS

    public ArrayList<String> readMaterials(){
        return readFile("materials.txt");
    }

    public void addMaterials(String input){
        appendFile("materials.txt", input);
    }

    public boolean MaterialsUpdate(int assetID, String valueEdited){
        return updateFile("materials.txt", assetID, valueEdited);
    }

    public void MaterialsRemove(int assetID){
        deleteFile("materials.txt", assetID);
    }

    //TRANSACTION METHOD
    public void addTransaction(String input){
        appendFile("transactions.txt", input);
    }

    public ArrayList<String> readTransactions(){
        return readFile("transactions.txt");
    }

    public boolean updateTransaction(int borrowerID, int materialID, String borrowDate, String returnDate){

        ArrayList<String> records = readTransactions();
        boolean found = false;

        for (int i = 0; i < records.size(); i++){

            String record = records.get(i);
            String[] values = record.split(",", -1);

            if (values.length == 6 || values.length == 8){

                if (values[0].equals(String.valueOf(borrowerID))
                        && values[1].equals(String.valueOf(materialID))
                        && values[4].equals(borrowDate)){

                    if (values.length == 6 || values[7].equals("false")){

                        String newData = values[0] + "," + values[1] + ","
                                + values[2] + "," + values[3] + ","
                                + values[4] + "," + values[5] + ","
                                + returnDate + ",true";

                        records.set(i, newData);
                        found = true;
                        break;
                    }
                }
            }
        }

        if (found == true){
            return rewriteFile("transactions.txt", records);
        }
        else{
            return false;
        }
    }
}
