
package com.ccs0023.librarymanagementsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class BorrowScreen {

    public static void showBorrowerMenu(JFrame mainFrame,
            BorrowerInformation borrowerSystem) {

        JFrame frame = new JFrame("Borrower Management");

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("BORROWER MANAGEMENT");

        JButton addButton = new JButton("[1] Add Borrower");
        JButton editButton = new JButton("[2] Edit Borrower");
        JButton deleteButton = new JButton("[3] Delete Borrower");
        JButton viewButton = new JButton("[4] View Borrower");
        JButton backButton = new JButton("[5] Return to Main Menu");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);

        titlePanel.add(title);
        centerPanel.add(buttonPanel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            System.out.println("Add Borrower selected.");
        });

        editButton.addActionListener(e -> {
            System.out.println("Edit Borrower selected.");
        });

        deleteButton.addActionListener(e -> {
            System.out.println("Delete Borrower selected.");
        });

        viewButton.addActionListener(e -> {
            System.out.println("View Borrower selected.");
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            mainFrame.setVisible(true);
        });

        frame.add(mainPanel);

        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
