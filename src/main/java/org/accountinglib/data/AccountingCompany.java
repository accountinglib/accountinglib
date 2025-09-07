package org.accountinglib.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

    /**
     * Reads the Norwegian chart of accounts CSV and creates a list of Account records.
     *
     * NOTE: The file must be saved as ISO_8859_1 for correct Norwegian character display.
     *
     * TODO: move to Account service class.
     */
    public void initializeChartOfAccounts() {

        Map<String, Account> accounts = new HashMap<>();

        Currency nok = new Currency("NOK", "Norwegian Krone");
        try {
            // Always read as UTF-8. If you see garbled characters, re-save the CSV as UTF-8.
            try (BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream("chart-of-accounts-norway.csv"), StandardCharsets.ISO_8859_1))) {
                CSVFormat format = CSVFormat.DEFAULT.builder()
                        .setDelimiter(';')
                        .setHeader("AccountID", "DescriptionNOR", "DescriptionENG")
                        .setSkipHeaderRecord(true)
                        .build();
                CSVParser parser = new CSVParser(reader, format);
                for (CSVRecord record : parser) {
                    String number = record.get("AccountID").trim();
                    String name = record.get("DescriptionNOR").trim();
                    accounts.put(number, new Account(number, name, nok));
                }
            }
        } catch (IOException | NullPointerException e) {
            System.err.println(e.getMessage());
        }

        if (ledger == null) ledger = new Ledger();
        ledger.setCompany(company);
        ledger.setAccounts(accounts);

    }

}
