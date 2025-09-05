package org.accountinglib.data;

import java.util.HashMap;
import java.util.Map;

public class Ledger {

    private Company company;
    private Map<String, Account> accounts = new HashMap<>();
    private Map<Long, Company> customers = new HashMap<>();
    private Map<Long, Company> suppliers = new HashMap<>();
    private Map<Long, Posting> postings = new HashMap<>();
    private Map<Long, Voucher> vouchers = new HashMap<>();
    private Map<Long, Invoice> invoices = new HashMap<>();
    private Map<Long, Document> documents = new HashMap<>();

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Map<Long, Company> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<Long, Company> customers) {
        this.customers = customers;
    }

    public Map<Long, Company> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Map<Long, Company> suppliers) {
        this.suppliers = suppliers;
    }

    public Map<Long, Posting> getPostings() {
        return postings;
    }

    public void setPostings(Map<Long, Posting> postings) {
        this.postings = postings;
    }

    public Map<Long, Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Map<Long, Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Map<Long, Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Map<Long, Invoice> invoices) {
        this.invoices = invoices;
    }

    public Map<Long, Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Map<Long, Document> documents) {
        this.documents = documents;
    }
}
