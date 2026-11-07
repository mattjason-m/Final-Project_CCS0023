package com.ccs0023.librarymanagementsystem;

public abstract class Material {

    private int materialId;
    private String title;
    private int yearPublished;
    private String publisher;
    private int totalCopies;
    private int availableCopies;

    public Material(){
        materialId = 0;
        title = "Title";
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
}