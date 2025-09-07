package org.accountinglib.data;

public class Context {

    static AccountingCompany accountingCompany;
    static Ledger ledger = new Ledger();

    public static Ledger getLedger() {
        return ledger;
    }

    public static void setLedger(Ledger l) {
        ledger = l;
    }

    public static AccountingCompany getAccountingCompany() {
        return accountingCompany;
    }

    public static void setAccountingCompany(AccountingCompany accountingCompany) {
        Context.accountingCompany = accountingCompany;
    }
}
