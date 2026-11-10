
package com.ccs0023.librarymanagementsystem;

public abstract class Material {
    
    private int materialId;
    private String title;
    private String author;
    private int yearPublished;
    private String publisher;
    private int totalCopies;
    private int availableCopies;
    
    public Material(){
        materialId = 0;
        title = "Title";
        author = "Author";
        yearPublished = 0;
        publisher = "Publisher";
        totalCopies = 0;
        availableCopies = 0;
    }
    
    public int getMaterialId(){
        return materialId;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public int getYearPublished(){
        return yearPublished;
    }
    
    public String getPublisher(){
        return publisher;
    }
    
    public int getTotalCopies(){
        return totalCopies;
    }
    
    public int getAvailableCopies(){
        return availableCopies;
    }
    
    public void setMaterialId(int enteredID){
        materialId = enteredID;
    }
    
    public void setTitle(String enteredTitle){
        title = enteredTitle;
    }
    
    public void setAuthor(String enteredAuthor){
        author = enteredAuthor;
    }
    
    public void setYearPublished(int enteredYear){
        yearPublished = enteredYear;
    }
    
    public void setPublisher(String enteredPublisher){
        publisher = enteredPublisher;
    }
    
    public void setTotalCopies(int enteredTotalCopies){
        totalCopies = enteredTotalCopies;
    }
    
    public void setAvailableCopies(int enteredAvailableCopies){
        availableCopies = enteredAvailableCopies;
    }
    
    public abstract int getBorrowingDays();
    
    public abstract String getMaterialType();
    
    public void SaveToFile(){
        FileHandler FILE = new FileHandler();
        String material = getMaterialType();
        if (material.equals("Book")
                            ||material.equals("Thesis Book")){
        FILE.addMaterials(String.join(",", String.valueOf(materialId), material, title, author, 
                String.valueOf(yearPublished), publisher, String.valueOf(totalCopies), String.valueOf(availableCopies)));
        }
        else{
            FILE.addMaterials(String.join(",", String.valueOf(materialId), material, title, 
                String.valueOf(yearPublished), publisher, String.valueOf(totalCopies), String.valueOf(availableCopies)));
        }
    }
    
    public void UpdateAuthoredMaterial(int MaterialID, String type){
        FileHandler FILE = new FileHandler();
        
        String ChangedData = String.join(",",String.valueOf(MaterialID), type, title, author, String.valueOf(yearPublished), publisher, String.valueOf(totalCopies), String.valueOf(availableCopies));
        FILE.MaterialsUpdate(MaterialID, ChangedData);  
    }
    
    public void UpdateUnauthoredMaterial(int MaterialID, String type){
        FileHandler FILE = new FileHandler();
        
        String ChangedData = String.join(",",String.valueOf(MaterialID), type,title, String.valueOf(yearPublished), publisher, String.valueOf(totalCopies), String.valueOf(availableCopies));
        FILE.MaterialsUpdate(MaterialID, ChangedData);  
    }
    
    public void RemoveMaterial(int MaterialID){
        FileHandler FILE = new FileHandler();
        FILE.MaterialsRemove(MaterialID);
    }
    
    
}

class Book extends Material {
    
    public Book(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 7;
    }
    
    @Override
    public String getMaterialType(){
        return "Book";
    }
}

class Journal extends Material {
    
    public Journal(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 3;
    }
    
    @Override
    public String getMaterialType(){
        return "Journal";
    }
}

class Magazine extends Material {
    
    public Magazine(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 0;
    }
    
    @Override
    public String getMaterialType(){
        return "Magazine";
    }
}

class ThesisBook extends Material {
    
    public ThesisBook(){
        super();
    }
    
    @Override
    public int getBorrowingDays(){
        return 2;
    }
    
    @Override
    public String getMaterialType(){
        return "Thesis Book";
    }
}
