
package com.ccs0023.librarymanagementsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class HistoryScreen {

    public static void showHistory(JFrame mainFrame, boolean borrowerSearch) {

        BookAndBorrowerHistory historySystem = new BookAndBorrowerHistory();
        historySystem.loadHistory();

        JFrame frame = new JFrame("Borrowing History");

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        String searchType;

        if (borrowerSearch) {
            searchType = "Borrower";
        }
        else {
            searchType = "Material";
        }

        JLabel title = new JLabel(searchType + " History");
        JLabel idLabel = new JLabel(searchType + " ID:");

        JTextField idField = new JTextField(15);

        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Return to Main Menu");

        JTextArea resultArea = new JTextArea(15, 35);
        resultArea.setEditable(false);

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(searchButton);
        inputPanel.add(backButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(resultArea, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            resultArea.setText("");
            int searchID;
            try {
                searchID = Integer.parseInt(idField.getText());
            }catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID. Please enter a number.");
                return;
            }
            boolean found = false;

            for (BorrowBlueprint record : historySystem.borrowRecords) {

                int recordID;

                if (borrowerSearch) {
                    recordID = record.getBorrower().getBorrowerIdNumber();
                } 
                else {
                    recordID = record.getMaterial().getMaterialId();
                }
                if (searchID == recordID) {

                    BorrowersManagement borrower = record.getBorrower();
                    Material material = record.getMaterial();
                    resultArea.append("Borrower ID: " + borrower.getBorrowerIdNumber() + "\n");
                    resultArea.append("Borrower Name: "+ borrower.getFirstName() + " " + borrower.getMiddleName() + " "
                    + borrower.getLastName() + "\n");
                    resultArea.append("Material ID:" + material.getMaterialId() + "\n");
                    resultArea.append("Material Name: " + material.getTitle() + "\n");
                    resultArea.append("Date Borrowed: " + record.getBorrowDate() + "\n\n");
                    found = true;
                }
            }

            if (found == false) {
                resultArea.setText("No borrowing history found.");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            mainFrame.setVisible(true);
        });

        frame.add(mainPanel);

        frame.setSize(550, 550);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
