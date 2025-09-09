package org.accountinglib.pdf;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDF {

    public PDF () {

    }

    private void test () {
        Document d = new Document();
        try {
            PdfWriter.getInstance(d, new FileOutputStream("hello.pdf"));
            d.open();
            d.add(new Paragraph("Hello, OpenPDF!"));
            d.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
