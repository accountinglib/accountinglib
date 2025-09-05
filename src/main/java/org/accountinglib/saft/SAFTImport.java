package org.accountinglib.saft;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.accountinglib.data.Company;
import org.accountinglib.data.Ledger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
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


            return ledger;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
