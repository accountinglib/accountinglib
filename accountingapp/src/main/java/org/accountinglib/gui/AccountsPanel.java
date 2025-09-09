package org.accountinglib.gui;

import org.accountinglib.data.Context;
import org.accountinglib.data.Ledger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountsPanel extends JPanel {

    private DefaultTableModel accountsTableModel;

    public AccountsPanel() {
        super(new BorderLayout());
        createAccountsPanel();
    }

    private void createAccountsPanel() {
        accountsTableModel = new DefaultTableModel(
                new Object[]{"Account ID", "Name", "Type", "Balance"}, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 3 -> Double.class;
                    default -> String.class;
                };
            }
        };

        JTable accountsTable = new JTable(accountsTableModel);
        accountsTable.setAutoCreateRowSorter(true);
        accountsTable.setFillsViewportHeight(true);
        accountsTable.setRowHeight(24);

        add(new JScrollPane(accountsTable), BorderLayout.CENTER);
    }

    public void updateAccountsTable() {
        accountsTableModel.setRowCount(0); // Clear existing rows
        Ledger ledger = Context.getLedger();
        if (ledger != null) {
            ledger.getAccounts().forEach((key, account) -> {
                accountsTableModel.addRow(new Object[]{
                        account.number(),
                        account.name()
                });
            });
        }
    }
}
