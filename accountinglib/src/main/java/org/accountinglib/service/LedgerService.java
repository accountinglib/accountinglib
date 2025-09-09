package org.accountinglib.service;

import javax.swing.table.DefaultTableModel;
import org.accountinglib.data.Ledger;

public class LedgerService {

    public void loadLedgerData(Ledger ledger, DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing data
        /*auditFile.getVouchers().values().forEach(voucher -> {
            for (Posting posting : voucher.getPostings()) {
                tableModel.addRow(new Object[]{
                    posting.getDate(),
                    posting.getAccount().getName(),
                    posting.getDescription(),
                    posting.getDebit(),
                    posting.getCredit()
                });
            }
        });*/
    }
}
