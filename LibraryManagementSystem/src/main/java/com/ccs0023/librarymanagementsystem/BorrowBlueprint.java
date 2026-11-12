package com.ccs0023.librarymanagementsystem;

import java.time.LocalDateTime;

public class BorrowBlueprint {

    private Material material;
    private BorrowersManagement borrower;

    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;

    public BorrowBlueprint(Material material, BorrowersManagement borrower, 
            LocalDateTime BorrowDate, LocalDateTime DueDate) {
        this.material = material;
        this.borrower = borrower;
        
        borrowDate = BorrowDate;
        dueDate = DueDate;
    }
    
    public Material getMaterial() {
        return material;
    }

    public BorrowersManagement getBorrower() {
        return borrower;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public void setMaterial(Material enteredMaterial) {
        material = enteredMaterial;
    }

    public void setBorrower(BorrowersManagement enteredBorrower) {
        borrower = enteredBorrower;
    }

    public void setBorrowDate(LocalDateTime enteredBorrowDate) {
        borrowDate = enteredBorrowDate;
    }

    public void setDueDate(LocalDateTime enteredDueDate) {
        dueDate = enteredDueDate;
    }

}
