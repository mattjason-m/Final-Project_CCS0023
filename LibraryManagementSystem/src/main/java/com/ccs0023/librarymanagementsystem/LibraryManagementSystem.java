package com.ccs0023.librarymanagementsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        //initializing the files here
        BorrowerInformation borrowerSystem = new BorrowerInformation();
        AssetManagement assetSystem = new AssetManagement();
        BorrowAMaterial borrowSystem = new BorrowAMaterial();

        JFrame frame = new JFrame("Library Management System");
  
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("LIBRARY MANAGEMENT SYSTEM");

        
        //all needed buttons here
        JButton borrowerButton = new JButton("Borrowers Management");
        JButton assetButton = new JButton("Asset Management");
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");
        JButton borrowerHistoryButton = new JButton("Borrower History");
        JButton bookHistoryButton = new JButton("Book History");
        JButton exitButton = new JButton("Exit");
        
        //include in the panel (need aligned in function description below)
        buttonPanel.add(borrowerButton);
        buttonPanel.add(assetButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(borrowerHistoryButton);
        buttonPanel.add(bookHistoryButton);
        buttonPanel.add(exitButton);

        titlePanel.add(title);
        centerPanel.add(buttonPanel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        //gui clicked functions here (must be align with declaration above)
        
        borrowerButton.addActionListener(e -> {
            borrowerSystem.loadBorrowers();
            frame.setVisible(false); //remove main menu
            BorrowScreen.showBorrowerMenu(frame, borrowerSystem);//swicth frame
        });

        assetButton.addActionListener(e -> {
            assetSystem.loadAsset();
            frame.setVisible(false);
            AssetScreen.showAssetMenu(frame, assetSystem);});

        returnButton.addActionListener(e -> {
            frame.setVisible(false);
            ReturnScreen.showReturnMenu(frame);
        });

        borrowerHistoryButton.addActionListener(e -> {
            frame.setVisible(false);
            HistoryScreen.showHistory(frame, true);
        });

        bookHistoryButton.addActionListener(e -> {
            frame.setVisible(false);
            HistoryScreen.showHistory(frame, false);
        });


        frame.add(mainPanel);

        frame.setSize(500, 600);//size here
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
