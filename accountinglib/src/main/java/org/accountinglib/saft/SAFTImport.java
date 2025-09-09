package org.accountinglib.saft;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.accountinglib.data.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;


public class SAFTImport {

    public static AuditFile parseAuditFile(File file) throws Exception {

            byte[] xmlContent = Files.readAllBytes(file.toPath());

            jakarta.xml.bind.JAXBContext context = JAXBContext.newInstance(AuditFile.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(xmlContent);
            XMLInputFactory xif = XMLInputFactory.newFactory();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xmlStream = xif.createXMLStreamReader(stream);
            return (AuditFile) unmarshaller.unmarshal(xmlStream);

    }

    public static Ledger importSAFT(File file) {

        try {
            AuditFile auditFile = parseAuditFile(file);

            Ledger ledger = new Ledger();
            Company company = new Company(auditFile.getHeader().getCompany().getName(), auditFile.getHeader().getCompany().getRegistrationNumber(),  false, false);
            ledger.setCompany(company);


            for (AuditFile.MasterFiles.GeneralLedgerAccounts.Account account : auditFile.getMasterFiles().getGeneralLedgerAccounts().getAccount()) {
                Account a = new Account(account.getAccountID(), account.getAccountDescription(), new Currency("NOK", "NOK"));
                ledger.getAccounts().put(account.getAccountID(), a);
            }


            for (AuditFile.GeneralLedgerEntries.Journal journal : auditFile.getGeneralLedgerEntries().journal) {
                for (AuditFile.GeneralLedgerEntries.Journal.Transaction transaction : journal.getTransaction()) {
                    Voucher v = new Voucher();
                    v.setId(Long.parseLong(transaction.getTransactionID()));
                    v.setDate(transaction.getTransactionDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
                    for (AuditFile.GeneralLedgerEntries.Journal.Transaction.Line line : transaction.getLine()) {

                        BigDecimal creditAmount = line.getCreditAmount() != null ? line.getCreditAmount().getAmount() : BigDecimal.ZERO;
                        BigDecimal debitAmount  = line.getDebitAmount()  != null ? line.getDebitAmount().getAmount()  : BigDecimal.ZERO;
                        BigDecimal amount = debitAmount.signum() != 0 ? debitAmount : creditAmount.negate();

                        Posting posting = new Posting(Long.parseLong(line.getRecordID()), null,
                                line.getValueDate().toGregorianCalendar().toZonedDateTime().toLocalDate(),
                                null,
                                amount,
                                line.getDescription(),
                                null,
                                null);
                        v.getPostings().put(Long.parseLong(line.getRecordID()), posting);
                    }
                    ledger.getVouchers().put(v.getId(), v);
                }
            }

            return ledger;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
