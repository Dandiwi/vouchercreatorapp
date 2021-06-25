package com.example.vouchercreator.Service;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.printing.PDFPageable;

import com.example.vouchercreator.Model.VoucherForm;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;

public class VoucherService {
    public static void printVoucher(VoucherForm voucherForm) {
        try {
            VoucherService app = new VoucherService();

            InputStream newPdf = app.getFileFromResourceAsStream("test2.pdf");

            File file = app.copyInputStreamToFile(newPdf);

            PDDocument pDDocument = PDDocument.load(file);

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

    public File copyInputStreamToFile(InputStream inputStream) throws IOException {

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        File targetFile = new File("src/main/resources/targetFile.tmp");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
        return targetFile;
    }

}
