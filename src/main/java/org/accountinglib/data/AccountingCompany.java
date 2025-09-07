package org.accountinglib.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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
     */
    public List<Account> initializeChartOfAccounts() {
        List<Account> accounts = new ArrayList<>();
        Currency nok = new Currency("NOK", "Norwegian Krone");
        try {
            Path path = Path.of(getClass().getClassLoader().getResource("chart-of-accounts-norway.csv").toURI());
            try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                CSVFormat format = CSVFormat.DEFAULT.builder()
                        .setDelimiter(';')
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .build();
                CSVParser parser = new CSVParser(reader, format);
                for (CSVRecord record : parser) {
                    String number = record.get(0).trim();
                    String name = record.get(1).trim();
                    accounts.add(new Account(number, name, nok));
                }
            }
        } catch (IOException | URISyntaxException | NullPointerException e) {

        }
        return accounts;
    }

}
