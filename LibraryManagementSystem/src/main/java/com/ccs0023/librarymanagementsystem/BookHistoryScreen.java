package com.ccs0023.librarymanagementsystem;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class BookHistoryScreen {

    public static void showBookHistory(JFrame mainFrame,
            ArrayList<BorrowBlueprint> borrowRecords) {

        JFrame frame = new JFrame("Book History");
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel title = new JLabel("BOOK BORROWING HISTORY");
        JLabel materialLabel = new JLabel("Material ID:");

        JTextField materialField = new JTextField(15);

        JButton searchButton = new JButton("Search Material");
        JButton backButton = new JButton("Return to Main Menu");

        JTextArea resultArea = new JTextArea(15, 35);
        resultArea.setEditable(false);

        inputPanel.add(materialLabel);
        inputPanel.add(materialField);
        inputPanel.add(searchButton);
        inputPanel.add(backButton);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(resultArea, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {

            resultArea.setText("");

            int materialID;

            try {
                materialID = Integer.parseInt(materialField.getText());
            }
            catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter a number.");
                return;
            }

            boolean found = false;

            for (BorrowBlueprint registeredRecord : borrowRecords) {

                if (materialID == registeredRecord.getMaterial().getMaterialId()) {
                    BorrowersManagement registeredBorrower =
                            registeredRecord.getBorrower();
                    resultArea.append("Material ID: " + registeredRecord.getMaterial().getMaterialId() + "\n");
                    resultArea.append("Material Name: " + registeredRecord.getMaterial().getTitle() + "\n");
                    resultArea.append("Borrower ID: " + registeredBorrower.getBorrowerIdNumber() + "\n");
                    resultArea.append("Borrower Name: " + registeredBorrower.getFirstName() + " " + registeredBorrower.getMiddleName() + " "
                    + registeredBorrower.getLastName() + "\n");
                    resultArea.append("Date Borrowed: "+ registeredRecord.getBorrowDate() + "\n\n");
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
