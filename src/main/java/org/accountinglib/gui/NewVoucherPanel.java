package org.accountinglib.gui;

import org.accountinglib.data.Account;
import org.accountinglib.data.Context;
import org.accountinglib.data.Ledger;
import org.accountinglib.data.Posting;
import org.accountinglib.data.Voucher;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

public class NewVoucherPanel extends JPanel {
    private JComboBox<String> debitAccountCombo;
    private JComboBox<String> creditAccountCombo;
    private JTextField amountField;
    private JButton okButton;
    private JLabel statusLabel;
    private final JFrame parent;

    public NewVoucherPanel(JFrame parent) {
        super(new BorderLayout(10, 10));
        this.parent = parent;
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createTitledBorder("Create New Voucher")
        ));
        buildUI();
    }

    private void buildUI() {
        removeAll();
        if (Context.getAccountingCompany() == null) {
            JLabel noCompanyLabel = new JLabel("No company loaded. Please create or open a company first.");
            noCompanyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(noCompanyLabel, BorderLayout.CENTER);
            revalidate();
            repaint();
            return;
        }
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        okButton = new JButton("Create Voucher");
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
        statusLabel = new JLabel(" ");
        add(statusLabel, BorderLayout.NORTH);
        okButton.addActionListener(e -> onOk());
        revalidate();
        repaint();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Voucher Details"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.gridy = 0;

        Ledger ledger = Context.getLedger();
        Map<String, Account> accounts = (ledger != null) ? ledger.getAccounts() : null;
        String[] accountNumbers = (accounts != null) ? accounts.keySet().toArray(new String[0]) : new String[0];

        debitAccountCombo = new JComboBox<>(accountNumbers);
        creditAccountCombo = new JComboBox<>(accountNumbers);
        amountField = new JTextField();
        setFieldSizes(debitAccountCombo);
        setFieldSizes(creditAccountCombo);
        setFieldSizes(amountField);

        addRow(panel, gbc, 0, "Debit Account:", debitAccountCombo);
        addRow(panel, gbc, 1, "Credit Account:", creditAccountCombo);
        addRow(panel, gbc, 2, "Amount:", amountField);
        return panel;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(90, 22));
        panel.add(jLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        panel.add(field, gbc);
    }

    private void setFieldSizes(JComponent comp) {
        Dimension dim = new Dimension(120, 22);
        Dimension maxDim = new Dimension(200, 22);
        comp.setPreferredSize(dim);
        comp.setMinimumSize(dim);
        comp.setMaximumSize(maxDim);

    }

    public void updateNewVoucerPanel() {
        buildUI();
    }

    private void onOk() {
        try {
            String debitAccountNum = (String) debitAccountCombo.getSelectedItem();
            String creditAccountNum = (String) creditAccountCombo.getSelectedItem();
            BigDecimal amount = new BigDecimal(amountField.getText());
            Ledger ledger = Context.getLedger();
            Map<String, Account> accounts = (ledger != null) ? ledger.getAccounts() : null;
            Account debitAccount = (accounts != null) ? accounts.get(debitAccountNum) : null;
            Account creditAccount = (accounts != null) ? accounts.get(creditAccountNum) : null;
            if (debitAccount == null || creditAccount == null) {
                statusLabel.setText("Invalid account selection.");
                return;
            }
            java.time.LocalDate today = java.time.LocalDate.now();
            org.accountinglib.data.Currency currency = debitAccount.currency();
            Posting debitPosting = new Posting(
                System.currentTimeMillis(),
                debitAccount,
                today,
                currency,
                BigDecimal.ZERO,
                amount,
                "",
                null,
                null
            );
            Posting creditPosting = new Posting(
                System.currentTimeMillis() + 1,
                creditAccount,
                today,
                currency,
                amount,
                BigDecimal.ZERO,
                "",
                null,
                null
            );
            Voucher voucher = new Voucher();
            java.util.Map<Long, Posting> postings = new java.util.HashMap<>();
            postings.put(debitPosting.id(), debitPosting);
            postings.put(creditPosting.id(), creditPosting);
            voucher.setPostings(postings);
            voucher.setDate(today);
            // Add to ledger
            long voucherId = System.currentTimeMillis();
            voucher.setId(voucherId);
            ledger.getVouchers().put(voucherId, voucher);
            // Optionally clear fields
            amountField.setText("");
            statusLabel.setText("Voucher created.");
            if (parent instanceof AccountingLibApp app) {
                //app.updateLedgerTable();
            }
        } catch (Exception ex) {
            statusLabel.setText("Invalid input: " + ex.getMessage());
        }
    }
}
