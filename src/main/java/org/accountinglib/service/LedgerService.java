package org.accountinglib.service;

import javax.swing.table.DefaultTableModel;
import org.accountinglib.data.AuditFile;
import org.accountinglib.data.Voucher;
import org.accountinglib.data.Posting;

public class LedgerService {

    public void loadLedgerData(AuditFile auditFile, DefaultTableModel tableModel) {
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
