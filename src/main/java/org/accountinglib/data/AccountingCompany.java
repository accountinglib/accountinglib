package org.accountinglib.data;

public class AccountingCompany {

    private Company company;
    private Ledger ledger;

    public AccountingCompany(Company company, Ledger ledger) {
        this.company = company;
        this.ledger = ledger;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    
}
