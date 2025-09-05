package org.accountinglib;

import org.accountinglib.data.Ledger;
import org.accountinglib.saft.SAFTImport;
import org.junit.jupiter.api.Test;
import org.accountinglib.saft.AuditFile;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class SAFTImportTest {

    @Test
    public void testParseSAFTFile() {
        File file = new File("src/main/resources/SAF-T Financial_999999999_20161125213512.xml");
        AuditFile auditFile = null;

        try {
            auditFile = SAFTImport.parseAuditFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(auditFile, "AuditFile should not be null");
        assertNotNull(auditFile.getHeader(), "Header should not be null");
        assertEquals("1.3", auditFile.getHeader().getAuditFileVersion(), "AuditFile version should be 1.3");
    }

    @Test
    public void testImportSAFTFile() {
        File file = new File("src/main/resources/SAF-T Financial_999999999_20161125213512.xml");

        try {
            Ledger ledger = SAFTImport.importSAFT(file);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
