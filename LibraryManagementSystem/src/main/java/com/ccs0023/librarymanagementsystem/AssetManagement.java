package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AssetManagement {
    
    ArrayList<Material> materials = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    int choice;
    
    public void assetTerminal(){
        
        do{
            System.out.println("");
            System.out.println("----------------------------");
            System.out.println("[1] Add Material");
            System.out.println("[2] Edit Material");
            System.out.println("[3] Delete Material");
            System.out.println("[4] View Material");
            System.out.println("[5] Return to Main Menu");
            System.out.println("----------------------------");
            System.out.print("Enter Option: ");
            try{
                choice = scan.nextInt();
                scan.nextLine();
                
                switch(choice){
                    case 1:
                        addMaterial();
                        break;
                    case 2:
                        editMaterial();
                        break;
                    case 3:
                        deleteMaterial();
                        break;
                    case 4:
                        viewMaterial();
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
    
    
    public void MaterialsTableHeader() {
        System.out.printf("%-15s %-12s %-40s %-30s %-18s %-30s %-15s %-18s %-15s%n",
                "Material Type", "Material ID", "Title or Name",
                "Author", "Year Published", "Publisher",
                "Total Copies", "Available Copies", "Borrowing Days");
    }

    public void MaterialsPrintValues(Material registeredMaterial) {

        String author = "N/A";

        if (registeredMaterial.getMaterialType().equals("Book")
                || registeredMaterial.getMaterialType().equals("Thesis Book")) {
            author = registeredMaterial.getAuthor();
        }

        System.out.printf("%-15s %-12d %-40s %-30s %-18d %-30s %-15d %-18d %-15d%n",
                registeredMaterial.getMaterialType(),
                registeredMaterial.getMaterialId(),
                registeredMaterial.getTitle(),
                author,
                registeredMaterial.getYearPublished(),
                registeredMaterial.getPublisher(),
                registeredMaterial.getTotalCopies(),
                registeredMaterial.getAvailableCopies(),
                registeredMaterial.getBorrowingDays());
    }

    public void addMaterial(){
        
        int materialType;
        
        try{
            System.out.println("Select Material Type");
            System.out.println("[1] Book");
            System.out.println("[2] Journal");
            System.out.println("[3] Magazine");
            System.out.println("[4] Thesis Book");
            System.out.println("[5] Return to Asset Management");
            System.out.print("Enter Choice: ");
            materialType = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
            return;
        }
        
        Material registeredMaterial;
        
        switch(materialType){
            case 1:
                registeredMaterial = new Book();
                break;
            case 2:
                registeredMaterial = new Journal();
                break;
            case 3:
                registeredMaterial = new Magazine();
                break;
            case 4:
                registeredMaterial = new ThesisBook();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid material type.");
                return;
        }
        
        int materialId;
        
        try{
            System.out.print("Enter Material ID: ");
            materialId = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Material ID.");
            scan.nextLine();
            return;
        }
        
        if (Validator.PositiveNumber(materialId) == false){
            System.out.println("Invalid Material ID.");
            return;
        }
        
        if (searchMaterial(materialId) != null){
            System.out.println("Material ID is already registered.");
            return;
        }
        
        registeredMaterial.setMaterialId(materialId);
        
        System.out.print("Enter Material Title or Name (Exclude ','): ");
        String title = scan.nextLine();
        
        if (Validator.Text(title) == false || Validator.NoComma(title) == false){
            System.out.println("Invalid Material Title.");
            return;
        }
        
        registeredMaterial.setTitle(title);
        
        if (registeredMaterial.getMaterialType().equals("Book")
                || registeredMaterial.getMaterialType().equals("Thesis Book")){
            
            System.out.print("Enter Author: ");
            String author = scan.nextLine();
            
            if (Validator.Text(author) == false || Validator.NoComma(author) == false){
                System.out.println("Invalid Author.");
                return;
            }
            
            registeredMaterial.setAuthor(author);
        }
        
        int yearPublished;
        
        try{
            System.out.print("Enter Year Published: ");
            yearPublished = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Year Published.");
            scan.nextLine();
            return;
        }
        
        if (Validator.Year(yearPublished) == false){
            System.out.println("Invalid Year Published.");
            return;
        }
        
        registeredMaterial.setYearPublished(yearPublished);
        
        System.out.print("Enter Publisher: ");
        String publisher = scan.nextLine();
        
        if (Validator.Text(publisher) == false || Validator.NoComma(publisher) == false){
            System.out.println("Invalid Publisher.");
            return;
        }
        
        registeredMaterial.setPublisher(publisher);
        
        int totalCopies;
        
        try{
            System.out.print("Enter Total Copies: ");
            totalCopies = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid number of copies.");
            scan.nextLine();
            return;
        }
        
        if (Validator.PositiveNumber(totalCopies) == false){
            System.out.println("Invalid Number of Copies.");
            return;
        }
        
        registeredMaterial.setTotalCopies(totalCopies);
        registeredMaterial.setAvailableCopies(totalCopies);
        
        if (checkDuplicateMaterial(registeredMaterial, -1)){
            System.out.println("Material is already registered.");
            return;
        }
        
        materials.add(registeredMaterial);
        registeredMaterial.SaveToFile();
        
        System.out.println("New material has been successfully added to the system.");
    }
    
    public void editMaterial(){
        
        int materialId;
        
        try{
            System.out.print("Enter Material ID Number to be Edited: ");
            materialId = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Material ID.");
            scan.nextLine();
            return;
        }
        
        Material registeredMaterial = searchMaterial(materialId);
        
        if (registeredMaterial != null){
            
            do{
                System.out.println("Edit Material Information");
                System.out.println("Material ID: " + materialId);
                System.out.println("[1] Edit Title or Name");
                System.out.println("[2] Edit Author");
                System.out.println("[3] Edit Year Published");
                System.out.println("[4] Edit Publisher");
                System.out.println("[5] Edit Total Copies");
                System.out.println("[6] Return to Asset Management");
                
                int choice;
                
                try{
                    System.out.print("Enter Choice: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a number from 1 to 6.");
                    scan.nextLine();
                    continue;
                }
                
                switch(choice){
                    case 1:
                        System.out.print("Edit Title or Name: ");
                        String changedTitle = scan.nextLine();
                        
                        if (Validator.Text(changedTitle) == false || Validator.NoComma(changedTitle) == false){
                            System.out.println("Invalid Title.");
                            break;
                        }
                        
                        String oldTitle = registeredMaterial.getTitle();
                        registeredMaterial.setTitle(changedTitle);//change in RAM
                        
                        if (checkDuplicateMaterial(registeredMaterial, materialId)){
                            registeredMaterial.setTitle(oldTitle);
                            System.out.println("Material is already registered.");
                            break;
                        }
                        
                        
                        if (registeredMaterial.getMaterialType().equals("Book")
                            ||registeredMaterial.getMaterialType().equals("Thesis Book")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateAuthoredMaterial(materialId, type);// save in txt/file
                        }
                        else if (registeredMaterial.getMaterialType().equals("Journal")
                            ||registeredMaterial.getMaterialType().equals("Magazine")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateUnauthoredMaterial(materialId, type); // save in txt/file
                        }
     
                       
                        System.out.println("Title has been updated successfully.");
                        break;
                        
                    case 2:
                        if (registeredMaterial.getMaterialType().equals("Book")
                                || registeredMaterial.getMaterialType().equals("Thesis Book")){
                            
                            System.out.print("Edit Author: ");
                            String changedAuthor = scan.nextLine();
                            
                            if (Validator.Text(changedAuthor) == false || Validator.NoComma(changedAuthor) == false){
                                System.out.println("Invalid Author.");
                                break;
                            }
                            
                            String oldAuthor = registeredMaterial.getAuthor();
                            registeredMaterial.setAuthor(changedAuthor);
                            
                            if (checkDuplicateMaterial(registeredMaterial, materialId)){
                                registeredMaterial.setAuthor(oldAuthor);
                                System.out.println("Material is already registered.");
                                break;
                            }
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateAuthoredMaterial(materialId, type);
                            System.out.println("Author has been updated successfully.");
                        }
                        else{
                            System.out.println("This material does not have an author field.");
                        }
                        break;
                        
                    case 3:
                        int ChangedYearPublished;
                        
                        try{
                            System.out.print("Edit Year Published: ");
                            ChangedYearPublished = scan.nextInt();
                            scan.nextLine();
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input. Please enter a valid year.");
                            scan.nextLine();
                            break;
                        }
                        
                        if (Validator.Year(ChangedYearPublished) == false){
                            System.out.println("Invalid Year Published.");
                            break;
                        }
                        
                        int oldYear = registeredMaterial.getYearPublished();
                        registeredMaterial.setYearPublished(ChangedYearPublished);
                        
                        if (checkDuplicateMaterial(registeredMaterial, materialId)){
                            registeredMaterial.setYearPublished(oldYear);
                            System.out.println("Material is already registered.");
                            break;
                        }
                        
                        
                        if (registeredMaterial.getMaterialType().equals("Book")
                            ||registeredMaterial.getMaterialType().equals("Thesis Book")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateAuthoredMaterial(materialId, type);
                        }
                        else if (registeredMaterial.getMaterialType().equals("Journal")
                            ||registeredMaterial.getMaterialType().equals("Magazine")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateUnauthoredMaterial(materialId, type);
                        }
                        System.out.println("Year Published has been updated successfully.");
                        break;
                        
                    case 4:
                        System.out.print("Edit Publisher: ");
                        String ChangedPublisher = scan.nextLine();
                        
                        if (Validator.Text(ChangedPublisher) == false){
                            System.out.println("Invalid Publisher.");
                            break;
                        }
                        
                        String oldPublisher = registeredMaterial.getPublisher();
                        registeredMaterial.setPublisher(ChangedPublisher);
                        
                        if (checkDuplicateMaterial(registeredMaterial, materialId)){
                            registeredMaterial.setPublisher(oldPublisher);
                            System.out.println("Material is already registered.");
                            break;
                        }
                        
                        if (registeredMaterial.getMaterialType().equals("Book")
                            ||registeredMaterial.getMaterialType().equals("Thesis Book")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateAuthoredMaterial(materialId, type);
                        }
                        else if (registeredMaterial.getMaterialType().equals("Journal")
                            ||registeredMaterial.getMaterialType().equals("Magazine")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateUnauthoredMaterial(materialId, type);
                        }
                        System.out.println("Publisher has been updated successfully.");
                        break;
                        
                    case 5:
                        int ChangedTotalCopies;
                        
                        try{
                            System.out.print("Edit Total Copies: ");
                            ChangedTotalCopies = scan.nextInt();
                            scan.nextLine();
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input. Please enter a valid number of copies.");
                            scan.nextLine();
                            break;
                        }
                        
                        int borrowedCopies = registeredMaterial.getTotalCopies()
                                - registeredMaterial.getAvailableCopies();
                        
                        if (Validator.PositiveNumber(ChangedTotalCopies) == false){
                            System.out.println("Invalid Number of Copies.");
                            break;
                        }
                        
                        if (ChangedTotalCopies < borrowedCopies){
                            System.out.println("Total Copies cannot be less than borrowed copies.");
                            break;
                        }
                        
                        registeredMaterial.setTotalCopies(ChangedTotalCopies);
                        registeredMaterial.setAvailableCopies(ChangedTotalCopies - borrowedCopies);
                        
                        if (registeredMaterial.getMaterialType().equals("Book")
                            ||registeredMaterial.getMaterialType().equals("Thesis Book")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateAuthoredMaterial(materialId, type);
                        }
                        else if (registeredMaterial.getMaterialType().equals("Journal")
                            ||registeredMaterial.getMaterialType().equals("Magazine")){
                            String type = registeredMaterial.getMaterialType();
                            registeredMaterial.UpdateUnauthoredMaterial(materialId, type);
                        }
                        System.out.println("Total Copies has been updated successfully.");
                        break;
                        
                    case 6:
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please enter a valid choice from 1 to 6.");
                }
                
            }while(true);
        }
        else{
            System.out.println("Material does not exist.");
        }
    }
    
    public void deleteMaterial(){
        
        int materialId;
        
        try{
            System.out.print("Enter Material ID Number to be Deleted: ");
            materialId = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Material ID.");
            scan.nextLine();
            return;
        }
        
        Material registeredMaterial = searchMaterial(materialId);
        
        if (registeredMaterial != null){
            
            if (registeredMaterial.getAvailableCopies() != registeredMaterial.getTotalCopies()){
                System.out.println("Material cannot be deleted because copies are currently borrowed.");
                return;
            }
            
            materials.remove(registeredMaterial);
            registeredMaterial.RemoveMaterial(materialId);
            System.out.println("Material has been successfully removed.");
        }
        else{
            System.out.println("Material does not exist.");
        }
    }
    
    public void viewMaterial(){
        
        int choice;
        
        do{
            System.out.println("\nView Material");
            System.out.println("[1] View All Materials");
            System.out.println("[2] View Specific Material");
            System.out.println("[3] Return to Asset Management");
            System.out.print("Enter Choice: ");
            
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
                    viewAllMaterial();
                    break;
                case 2:
                    viewSpecificMaterial();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice from 1 to 3.");
            }
            
        }while(true);
    }
    
    public void viewAllMaterial() {
        System.out.println("\nAll Records of Registered Materials");

        if (materials.isEmpty()) {
            System.out.println("No registered materials found.");
            return;
        }

        MaterialsTableHeader();

        for (Material registeredMaterial : materials) {
            MaterialsPrintValues(registeredMaterial);
        }
    }
    
    public Material searchMaterial(int materialId){
        
        for (Material registeredMaterial : materials){
            
            if (materialId == registeredMaterial.getMaterialId()){
                return registeredMaterial;
            }
        }
        
        return null;
    }
    
    public void viewSpecificMaterial(){
        
        int materialId;
        
        try{
            System.out.print("Enter Material ID to View: ");
            materialId = scan.nextInt();
            scan.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. Please enter a valid Material ID.");
            scan.nextLine();
            return;
        }
        
        Material registeredMaterial = searchMaterial(materialId);
        
        if (registeredMaterial != null){
            System.out.println();
            displayMaterial(registeredMaterial);
        }
        else{
            System.out.println("Material does not exist.");
        }
    }
    
    public void displayMaterial(Material registeredMaterial) {
        MaterialsTableHeader();
        MaterialsPrintValues(registeredMaterial);
    }
    
    
    public boolean checkDuplicateMaterial(Material registeredMaterial, int materialId){
        
        for (Material existingMaterial : materials){
            
            if (existingMaterial.getMaterialId() != materialId){
                
                if (existingMaterial.getMaterialType().equals(registeredMaterial.getMaterialType())){
                    
                    if (existingMaterial.getTitle().trim().equalsIgnoreCase(registeredMaterial.getTitle().trim())
                            && existingMaterial.getYearPublished() == registeredMaterial.getYearPublished()
                            && existingMaterial.getPublisher().trim().equalsIgnoreCase(registeredMaterial.getPublisher().trim())){
                        
                        if (registeredMaterial.getMaterialType().equals("Book")
                                || registeredMaterial.getMaterialType().equals("Thesis Book")){
                            
                            if (existingMaterial.getAuthor().trim().equalsIgnoreCase(registeredMaterial.getAuthor().trim())){
                                return true;
                            }
                        }
                        else{
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public void loadAsset(){
        Material registeredMaterial;
        FileHandler FILE = new FileHandler();
        ArrayList<String> records = FILE.readMaterials();
        for (String record: records){
            try {
                String[] values = record.split(",");

                if (values.length != 8 && values.length != 7 ) {
                    System.out.println("Invalid borrower record.");
                    continue; 
                }
                
                switch (values[1]){
                    case "Book":
                        registeredMaterial = new Book();
                        registeredMaterial.setMaterialId(Integer.parseInt(values[0]));
                        registeredMaterial.setTitle(values[2]);
                        registeredMaterial.setAuthor(values[3]);
                        registeredMaterial.setYearPublished(Integer.parseInt(values[4]));
                        registeredMaterial.setPublisher(values[5]);
                        registeredMaterial.setTotalCopies(Integer.parseInt(values[6]));
                        registeredMaterial.setAvailableCopies(Integer.parseInt(values[7]));
                        materials.add(registeredMaterial);
                        break;
                    case "Journal":
                        registeredMaterial = new Journal();
                        registeredMaterial.setMaterialId(Integer.parseInt(values[0]));
                        registeredMaterial.setTitle(values[2]);
                        registeredMaterial.setYearPublished(Integer.parseInt(values[3]));
                        registeredMaterial.setPublisher(values[4]);
                        registeredMaterial.setTotalCopies(Integer.parseInt(values[5]));
                        registeredMaterial.setAvailableCopies(Integer.parseInt(values[6]));
                        materials.add(registeredMaterial);
                        break;
                    case "Magazine":
                        registeredMaterial = new Magazine();
                        registeredMaterial.setMaterialId(Integer.parseInt(values[0]));
                        registeredMaterial.setTitle(values[2]);
                        registeredMaterial.setYearPublished(Integer.parseInt(values[3]));
                        registeredMaterial.setPublisher(values[4]);
                        registeredMaterial.setTotalCopies(Integer.parseInt(values[5]));
                        registeredMaterial.setAvailableCopies(Integer.parseInt(values[6]));
                        materials.add(registeredMaterial);
                        break;
                    case "Thesis Book":
                        registeredMaterial = new ThesisBook();
                        registeredMaterial.setMaterialId(Integer.parseInt(values[0]));
                        registeredMaterial.setTitle(values[2]);
                        registeredMaterial.setAuthor(values[3]);
                        registeredMaterial.setYearPublished(Integer.parseInt(values[4]));
                        registeredMaterial.setPublisher(values[5]);
                        registeredMaterial.setTotalCopies(Integer.parseInt(values[6]));
                        registeredMaterial.setAvailableCopies(Integer.parseInt(values[7]));
                        materials.add(registeredMaterial);
                        break;
                    default:
                        System.out.println("Unknown material type.");
                        continue;
                    
                }
                    
            }
            catch(NumberFormatException e) {
                    System.out.println("Invalid number in borrower record.");
            }catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Incomplete material record.");
            }
       
        }
    }
    
}
