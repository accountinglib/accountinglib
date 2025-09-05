package org.accountinglib.data;

public class Context {

    static Ledger ledger = new Ledger();

    public static Ledger getLedger() {
        return ledger;
    }

    public static void setLedger(Ledger l) {
        ledger = l;
    }



}
