package org.accountinglib.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LedgerPanel extends JPanel {

    private final DefaultTableModel ledgerTableModel;

    public LedgerPanel() {
        super(new BorderLayout());
        ledgerTableModel = new DefaultTableModel(
                new Object[]{"Date", "Account", "Description", "Debit", "Credit"}, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 3, 4 -> Double.class;
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

    public void updateLedgerTable(Object[][] data) {
        ledgerTableModel.setRowCount(0); // Clear existing rows
        for (Object[] row : data) {
            ledgerTableModel.addRow(row);
        }
    }
}
