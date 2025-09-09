package org.accountinglib.data;

public class Context {

    static AccountingCompany accountingCompany;

    public static Ledger getLedger() {
        return accountingCompany.getLedger();
    }

    public static void setLedger(Ledger l) {
        accountingCompany.setLedger(l);
    }

    public static AccountingCompany getAccountingCompany() {
        return accountingCompany;
    }

    public static void setAccountingCompany(AccountingCompany accountingCompany) {
        Context.accountingCompany = accountingCompany;
    }
}
