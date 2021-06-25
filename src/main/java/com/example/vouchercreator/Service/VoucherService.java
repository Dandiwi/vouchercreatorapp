package com.example.vouchercreator.Service;

import com.example.vouchercreator.VouchercreatorApplication;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.printing.PDFPageable;

import com.example.vouchercreator.Model.VoucherForm;
import org.springframework.core.io.ClassPathResource;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VoucherService {
    public static void printVoucher(VoucherForm voucherForm) {
        try {
            PDDocument pDDocument = PDDocument.load(new File("src/main/resources/test2.pdf"));

            VoucherService app = new VoucherService();
            InputStream cinzel = app.getFileFromResourceAsStream("Cinzel-Regular.ttf");
            PDFont cinzelFont = PDType0Font.load(pDDocument, cinzel, false);

            InputStream cutive = app.getFileFromResourceAsStream("CutiveMono-Regular.ttf");
            PDFont cutiveFont = PDType0Font.load(pDDocument,cutive, false);



            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

            PDField name = pDAcroForm.getField("name");
            name.setValue(voucherForm.getName());

            PDField pakiet = pDAcroForm.getField("pakiet");
            pakiet.setValue((voucherForm.getPakiet()));

            PDField date = pDAcroForm.getField("date");
            date.setValue(voucherForm.getDate().toString());

            PDField email = pDAcroForm.getField("email");
            prepareFont(cutiveFont, email);
            email.setValue(voucherForm.getEmail());

            PDField insidePakiet = pDAcroForm.getField("insidePakiet");
            prepareFont(cinzelFont, insidePakiet);
            insidePakiet.setValue(voucherForm.getInsidePakiet());

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

    public static void prepareFont(PDFont font, PDField field) throws IOException {
        final PDResources resources = new PDResources();
        PDAcroForm fieldAcroForm = field.getAcroForm();
        fieldAcroForm.setDefaultResources(resources);
        final String fontName = resources.add(font).getName();
        fieldAcroForm.setDefaultResources(resources);
        String defaultAppearanceString= "/" + fontName + " 0 Tf 0 g";
        fieldAcroForm.setDefaultAppearance(defaultAppearanceString);
        field.getCOSObject().setString(COSName.DA, defaultAppearanceString);
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


}
