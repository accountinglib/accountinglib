package org.accountinglib.gui;

import org.accountinglib.data.AccountingCompany;
import org.accountinglib.data.Context;
import org.accountinglib.data.Ledger;

import javax.swing.*;
import java.awt.*;

public class CompanyInformationPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel orgNumberLabel;
    private JLabel accountsCountLabel;
    private JLabel vouchersCountLabel;

    public CompanyInformationPanel() {
        super(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Company Information"));
        initUI();
        updateInfo();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Company name:"), gbc);
        gbc.gridx = 1;
        nameLabel = new JLabel();
        add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Organization number:"), gbc);
        gbc.gridx = 1;
        orgNumberLabel = new JLabel();
        add(orgNumberLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Number of accounts:"), gbc);
        gbc.gridx = 1;
        accountsCountLabel = new JLabel();
        add(accountsCountLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Number of vouchers:"), gbc);
        gbc.gridx = 1;
        vouchersCountLabel = new JLabel();
        add(vouchersCountLabel, gbc);
    }

    public void updateInfo() {
        AccountingCompany company = Context.getAccountingCompany();
        if (company != null) {
            nameLabel.setText(company.getCompany().name());
            orgNumberLabel.setText(company.getCompany().number());
            Ledger ledger = company.getLedger();
            accountsCountLabel.setText(ledger != null ? String.valueOf(ledger.getAccounts().size()) : "0");
            vouchersCountLabel.setText(ledger != null ? String.valueOf(ledger.getVouchers().size()) : "0");
        } else {
            nameLabel.setText("-");
            orgNumberLabel.setText("-");
            accountsCountLabel.setText("-");
            vouchersCountLabel.setText("-");
        }
    }
}

