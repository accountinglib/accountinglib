package org.accountinglib.gui;

import org.accountinglib.data.Company;

import javax.swing.*;
import java.awt.*;

public class NewCompanyDialog extends JDialog {
    private JTextField nameField;
    private JTextField orgNumberField;
    private boolean confirmed = false;

    public NewCompanyDialog(Frame parent) {
        super(parent, "New Company", true);
        setLayout(new BorderLayout(10, 10));

        // Introduction text
        JLabel introLabel = new JLabel("Create a new company account in the Accounting Library.\n ");
        introLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(introLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Company information:"));
        inputPanel.add(new JLabel("Company Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Organization Number:"));
        orgNumberField = new JTextField();
        inputPanel.add(orgNumberField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        cancelButton.addActionListener(e -> setVisible(false));

        setPreferredSize(new Dimension(400, 250));
        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Company getCompany() {
        if (!confirmed) return null;
        return new Company(nameField.getText(), orgNumberField.getText(), false, false);
    }
}
