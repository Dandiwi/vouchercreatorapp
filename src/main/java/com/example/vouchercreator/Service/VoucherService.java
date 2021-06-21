package com.example.vouchercreator.Service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.printing.PDFPageable;

import com.example.vouchercreator.Model.VoucherForm;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class VoucherService {
    public static void printVoucher(VoucherForm voucherForm) {
        try {
            PDDocument pDDocument = PDDocument.load(new File("src/main/resources/test2.pdf"));
            PDFont font = PDTrueTypeFont.loadTTF(pDDocument, new File("src/main/resources/Cinzel-Regular.ttf"));

            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

            PDField name = pDAcroForm.getField("name");
            name.setValue(voucherForm.getName());

            PDField pakiet = pDAcroForm.getField("pakiet");
            pakiet.setValue((voucherForm.getPakiet()));

            PDField date = pDAcroForm.getField("date");
            date.setValue(voucherForm.getDate().toString());

            PDField email = pDAcroForm.getField("email");
            email.setValue(voucherForm.getEmail());

            System.out.println("processing");
            pDDocument.save("src/main/resources/pdf-java-output.pdf");
            PDDocument output = PDDocument.load(new File("src/main/resources/pdf-java-output.pdf"));
            pDDocument.close();
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(output));
            job.printDialog();
            job.print();
            output.close();
        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }

    public static void getAcroForm (String name) {

    }
}
