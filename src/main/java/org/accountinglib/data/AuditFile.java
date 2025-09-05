package org.accountinglib.data;

import java.util.HashMap;
import java.util.Map;

public class AuditFile {

    private Company company;
    private Map<Long, Account> accounts = new HashMap<>();
    private Map<Long, Company> customers = new HashMap<>();
    private Map<Long, Company> suppliers = new HashMap<>();
    private Map<Long, Posting> postings = new HashMap<>();
    private Map<Long, Voucher> vouchers = new HashMap<>();
    private Map<Long, Invoice> invoices = new HashMap<>();
    private Map<Long, Document> documents = new HashMap<>();




}
