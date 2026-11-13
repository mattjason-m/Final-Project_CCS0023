package com.ccs0023.librarymanagementsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class ReturnScreen {

    public static void showReturnMenu(JFrame mainFrame) {

        JFrame frame = new JFrame("Return Material");

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("RETURN MATERIAL");

        JButton returnButton = new JButton("[1] Return Material");
        JButton backButton = new JButton("[2] Return to Main Menu");

        buttonPanel.add(returnButton);
        buttonPanel.add(backButton);

        titlePanel.add(title);
        centerPanel.add(buttonPanel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        returnButton.addActionListener(e -> {
            Return returnSystem = new Return();
            returnSystem.returnBook();
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            mainFrame.setVisible(true);
        });

        frame.add(mainPanel);

        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
