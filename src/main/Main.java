/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

/**
 *
 * @author yvan
 */
public class Main {

    private Main() {
    }

    /*public static void main(String[] args) throws IOException
    {
        if( args.length != 2 )
        {
            System.err.println("usage: " + Main.class.getName() + " <output-file> <Message>");
            System.exit(1);
        }

        String filename = args[0];
        String message = args[1];
        
        try (PDDocument doc = new PDDocument())
        {
            PDPage page = new PDPage();
            doc.addPage(page);
            
            PDFont font = PDType1Font.HELVETICA_BOLD;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.showText(message);
                contents.endText();
            }
            
            doc.save(filename);
        }
    }*/
    public static void main(String[] args) throws IOException {
        // Load the PDF document created by SimpleForm.java
        try (PDDocument document = PDDocument.load(new File("documents/FormulaireCF2017.pdf"))) {
            // Note that the JavaScript will depend on the reader application.
            // The classes and methods available to Adobe Reader and Adobe Acrobat
            // are documented in the Acrobat SDK.
            PDDocumentCatalog catalog = document.getDocumentCatalog();
            for (PDField field : catalog.getAcroForm().getFields()) {
                System.out.println(field.getFullyQualifiedName() + " -> " + field.getFieldType());
                //System.out.println(field.getFullyQualifiedName());
                //System.out.println(field.getValueAsString());
                //field.setValue("Off");
                if ("Btn".equals(field.getFieldType())) {
                    //System.out.println(field.getFullyQualifiedName());
                    //field.setValue(field.getFullyQualifiedName());
                    //PDCheckBox pd = (PDCheckBox) field;
                    
                }
            }

            PDField L2 = document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1");
            L2.setValue("Off");
            PDField M1 = document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1_2");
            M1.setValue("Off");
            PDField L3 = document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1_3");
            L3.setValue("Off");
            PDField M2 = document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1_4");
            M2.setValue("Off");
            PDField ProposeEn = document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 2");
            ProposeEn.setValue("Off");
            PDField RefuseEn = document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 3");
            RefuseEn.setValue("Off");
            PDField Observations = document.getDocumentCatalog().getAcroForm().getField("Zone de texte 1_2");
            Observations.setValue("Observations");
            PDField NomPrenomID = document.getDocumentCatalog().getAcroForm().getField("Zone de texte 3");
            NomPrenomID.setValue("Nom prénom");
            PDField ComplementRefus = document.getDocumentCatalog().getAcroForm().getField("Zone de texte 1");
            ComplementRefus.setValue("Complément refus");
            PDField date = document.getDocumentCatalog().getAcroForm().getField("Champ de date 1");
            date.setValue(DateFormat.getDateInstance(DateFormat.SHORT).format(new Date()));
            PDComboBox specialite = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 5");
            specialite.setEdit(true);
            specialite.setValue("specialité");
            PDComboBox formationrefuse = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 4");
            formationrefuse.setEdit(true);
            formationrefuse.setValue("formation refusée");
            PDComboBox motif = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 1");
            motif.setEdit(true);
            motif.setValue("motif");
            PDComboBox responsable = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 2");
            responsable.setEdit(true);
            responsable.setValue("responsable");

            //System.out.println(document.getNumberOfPages());
            document.save("documents/tmp.pdf");
        }
    }
}
