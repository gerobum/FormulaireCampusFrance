package pdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

/**
 *
 * @author yvan
 */
public class PDF {

    private final PDDocument document;
    private final PDCheckBox checkL2, checkL3, checkM1, checkM2;
    private final PDCheckBox checkProposition, checkRefus;
    private final PDTextField observations, nomPrenomId, complementRefus, date;
    private final PDComboBox specialite, formationRefuse, motif, responsable;

    public PDF() throws IOException {
        this(PDF.class.getResourceAsStream("FormulaireCF2017.pdf"));
    }

    private PDF(InputStream in) throws IOException {
        this.document = PDDocument.load(in);
        
        checkL2 = (PDCheckBox) document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1");
        checkM1 = (PDCheckBox) document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1_2");
        checkL3 = (PDCheckBox) document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1_3");
        checkM2 = (PDCheckBox) document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 1_4");
        checkProposition = (PDCheckBox) document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 2");
        checkRefus = (PDCheckBox) document.getDocumentCatalog().getAcroForm().getField("Case #C3#A0 cocher 3");
        observations = (PDTextField) document.getDocumentCatalog().getAcroForm().getField("Zone de texte 1_2");
        nomPrenomId = (PDTextField) document.getDocumentCatalog().getAcroForm().getField("Zone de texte 3");
        complementRefus = (PDTextField) document.getDocumentCatalog().getAcroForm().getField("Zone de texte 1");
        date = (PDTextField) document.getDocumentCatalog().getAcroForm().getField("Champ de date 1");
        date.setValue(DateFormat.getDateInstance(DateFormat.SHORT).format(new Date()));

        specialite = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 5");
        specialite.setEdit(true);
        formationRefuse = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 4");
        formationRefuse.setEdit(true);
        motif = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 1");
        motif.setEdit(true);

        responsable = (PDComboBox) document.getDocumentCatalog().getAcroForm().getField("Zone de liste 2");
        responsable.setEdit(true);

    }

    public void checkL3() {
        try {
            checkL3.check();
        } catch (IOException ex) {
        }
    }

    public void unCheckL3() {
        try {
            checkL3.unCheck();
        } catch (IOException ex) {
        }
    }

    public boolean isL3Checked() {
        return checkL3.isChecked();
    }

    public void setL3Checked(boolean checked) {
        if (checked) {
            checkL3();
        } else {
            unCheckL3();
        }
    }

    public void checkL2() {
        try {
            checkL2.check();
        } catch (IOException ex) {
        }
    }

    public void unCheckL2() {
        try {
            checkL2.unCheck();
        } catch (IOException ex) {
        }
    }

    public boolean isL2Checked() {
        return checkL2.isChecked();
    }

    public void setL2Checked(boolean checked) {
        if (checked) {
            checkL2();
        } else {
            unCheckL2();
        }
    }
    

    public void checkM1() {
        try {
            checkM1.check();
        } catch (IOException ex) {
        }
    }

    public void unCheckM1() {
        try {
            checkM1.unCheck();
        } catch (IOException ex) {
        }
    }

    public boolean isM1Checked() {
        return checkM1.isChecked();
    }

    public void setM1Checked(boolean checked) {
        if (checked) {
            checkM1();
        } else {
            unCheckM1();
        }
    }

    public void checkM2() {
        try {
            checkM2.check();
        } catch (IOException ex) {
        }
    }

    public void unCheckM2() {
        try {
            checkM2.unCheck();
        } catch (IOException ex) {
        }
    }

    public boolean isM2Checked() {
        return checkM2.isChecked();
    }

    public void setM2Checked(boolean checked) {
        if (checked) {
            checkM2();
        } else {
            unCheckProposition();
        }
    }

    public void checkProposition() {
        try {
            checkProposition.check();
        } catch (IOException ex) {
        }
    }

    public void unCheckProposition() {
        try {
            checkProposition.unCheck();
        } catch (IOException ex) {
        }
    }

    public boolean isPropositionChecked() {
        return checkProposition.isChecked();
    }

    public void setPropositionChecked(boolean checked) {
        if (checked) {
            checkProposition();
        } else {
            unCheckProposition();
        }
    }

    public void checkRefus() {
        try {
            checkRefus.check();
        } catch (IOException ex) {
        }
    }

    public void unCheckRefus() {
        try {
            checkRefus.unCheck();
        } catch (IOException ex) {
        }
    }

    public boolean isRefusChecked() {
        return checkRefus.isChecked();
    }

    public void setRefusChecked(boolean checked) {
        if (checked) {
            checkRefus();
        } else {
            unCheckRefus();
        }
    }

    public String getObservations() {
        return observations.getValueAsString();
    }

    public String getNomPrenomId() {
        return nomPrenomId.getValueAsString();
    }

    public String getComplementRefus() {
        return complementRefus.getValueAsString();
    }

    public String getSpecialite() {
        return specialite.getValueAsString();
    }

    public String getFormationRefuse() {
        return formationRefuse.getValueAsString();
    }

    public String getMotif() {
        return motif.getValueAsString();
    }

    public String getResponsable() {
        return responsable.getValueAsString();
    }
    
    
    public void setObservations(String value) {
        try {
            observations.setValue(value);
        } catch (IOException ex) {}
    }

    public void setNomPrenomId(String value) {
        try {
            nomPrenomId.setValue(value);
        } catch (IOException ex) {}
    }

    public void setComplementRefus(String value) {
        try {
            complementRefus.setValue(value);
        } catch (IOException ex) {}
    }

    public void setSpecialite(String value) {
        try {
            specialite.setValue(value);
        } catch (IOException ex) {}
    }

    public void setFormationRefuse(String value) {
        try {
            formationRefuse.setValue(value);
        } catch (IOException ex) {}
    }

    public void setMotif(String value) {
        try {
            motif.setValue(value);
        } catch (IOException ex) {}
    }

    public void setResponsable(String value) {
        try {
            responsable.setValue(value);
        } catch (IOException ex) {}
    }
    
    public void save(String file) throws IOException {
        document.save(file);
    }
}
