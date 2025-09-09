package org.accountinglib.gui;

import org.accountinglib.data.Ledger;
import org.accountinglib.data.Posting;
import org.accountinglib.data.Voucher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LedgerPanel extends JPanel {

    private final DefaultTableModel ledgerTableModel;

    public LedgerPanel() {
        super(new BorderLayout());
        ledgerTableModel = new DefaultTableModel(
                new Object[]{"Date", "Voucher", "Account", "Description", "Debit", "Credit"}, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 4, 5 -> Double.class;
                    default -> String.class;
                };
            }
        };

        JTable ledgerTable = new JTable(ledgerTableModel);
        ledgerTable.setAutoCreateRowSorter(true);
        ledgerTable.setFillsViewportHeight(true);
        ledgerTable.setRowHeight(24);

        add(new JScrollPane(ledgerTable), BorderLayout.CENTER);
    }

    public void updateLedgerTable(Ledger ledger) {
        ledgerTableModel.setRowCount(0); // Clear existing rows
        for (Voucher voucher : ledger.getVouchers().values()) {
            for (Posting posting : voucher.getPostings().values()) {
                ledgerTableModel.addRow(new Object[]{
                        voucher.getDate(),
                        voucher.getId(),
                        null,
                        posting.description(),
                        posting.debitAmount(),
                        posting.creditAmount()
                });
            }
        }
    }
}
