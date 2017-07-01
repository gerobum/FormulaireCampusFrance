package pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;
import javax.swing.JFileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author yvan
 */
public class RenommageDossiersPDF {
    private PDDocument document;
    public String lastname, firstname, firstline;

    private void setNames(String flname) {
        int i = flname.length() - 1;
        while (!Character.isLowerCase(flname.charAt(i))) {
            --i;
        }
        lastname = flname.substring(i + 1).trim();

        int j = i - 1;
        while (!Character.isDigit(flname.charAt(j))) {
            --j;
        }
        firstname = flname.substring(j + 1, i + 1).trim();
    }

    public RenommageDossiersPDF(File f) {
        try {
            this.document = PDDocument.load(f);

            //System.out.println(stripper.getText(document));
            Scanner cin = new Scanner(new PDFTextStripper().getText(document));

            // cin.nextLine(); // Ref. dossier : MA17-06608-C01-98 Abdelhamid AZMI
            firstline = cin.nextLine();
            setNames(firstline);
            document.close();

        } catch (IOException ex) {
            System.out.println(f.getName());
        }
    }

    public static void main(String[] args) throws IOException {
        File config = new File(".configuration.rename.dossierCF");
        File directory;
        if (Files.exists(config.toPath())) {
            try (BufferedReader in = new BufferedReader(new FileReader(config))) {
                directory = new File(in.readLine());
            }
        } else {
            directory = new File(".");
        }
        JFileChooser directoryChooser = new JFileChooser(directory);
        directoryChooser.setDialogTitle("Choix un dossier contenant des dossiers CF à renommer");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   
        if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            directory = directoryChooser.getSelectedFile();
            int x = 0;
//            for (File f : directory.listFiles((d,n)->{return n.matches("[A-Z]{2}?-[0-9]{5}-[A-Z][0-9]{2}-[0-9]{2}.pdf");})) {
            for (File f : directory.listFiles((d, n) -> {
                return n.matches("[A-Z]{2}?[0-9]{2}?-[0-9]{5}-[A-Z][0-9]{2}-[0-9]{2}\\.pdf");
            })) {

                RenommageDossiersPDF dcpdf = new RenommageDossiersPDF(f);
                System.out.println("---------------------------------------------------");
                System.out.println(f.getName());
                System.out.println(dcpdf.firstline);
                System.out.println("Prénom : " + dcpdf.firstname);
                System.out.println("Nom : " + dcpdf.lastname);
                ++x;
                File dest = new File(f.getParentFile(), dcpdf.lastname + " " + dcpdf.firstname + "_" + f.getName());
                f.renameTo(dest);
            }
            System.out.printf("%d fichiers traités\n", x);
            
            try(PrintWriter out = new PrintWriter(new FileWriter(config))) {
                out.println(directory.getAbsolutePath());
            } 
        }
    }
}
