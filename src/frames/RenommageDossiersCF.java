package frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RenommageDossiersCF extends JFrame implements ActionListener {

    private final JButton choix;
    private final JButton renommage;
    private final JButton annuler;
    private final List<File> list;
    private final JTextArea ta;

    public RenommageDossiersCF() {
        choix = new JButton("Choisir un dossier contenant des dossiers CF");
        renommage = new JButton("Renommer ces fichiers ? ");
        annuler = new JButton("Annuler");
        renommage.setVisible(false);
        annuler.setVisible(false);
        list = new LinkedList<>();
        ta = new JTextArea();
        ta.setEditable(false);
        JPanel north = new JPanel();
        north.add(choix);
        north.add(renommage);
        north.add(annuler);

        getContentPane().add(north, "North");
        JScrollPane center = new JScrollPane(ta);
        center.setAutoscrolls(true);
        center.setPreferredSize(new Dimension(800, 400));

        getContentPane().add(center, "Center");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        choix.addActionListener(this);
        annuler.addActionListener(this);
        renommage.addActionListener(this);
    }

    public static void main(String[] args) {
        new RenommageDossiersCF();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ta.setText("");
        if (e.getSource() == choix) {
            File config = new File(".configuration.rename.dossierCF");
            File directory = null;

            if (Files.exists(config.toPath())) {
                try (BufferedReader in = new BufferedReader(new FileReader(config))) {
                    directory = new File(in.readLine());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(RenommageDossiersCF.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RenommageDossiersCF.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                directory = new File(".");
            }
            JFileChooser directoryChooser = new JFileChooser(directory);
            directoryChooser.setDialogTitle("Choisir un dossier contenant des dossiers CF à renommer");
            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                list.clear();
                directory = directoryChooser.getSelectedFile();

                for (File f : directory.listFiles((d, n) -> {
                    return n.matches("[A-Z]{2}?[0-9]{2}?-[0-9]{5}-[A-Z][0-9]{2}-[0-9]{2}\\.pdf");
                })) {

                    list.add(f);
                    ta.append(f.getName() + "\n");
                    System.out.println(f.getName());
                }
                if (!list.isEmpty()) {
                    choix.setVisible(false);
                    annuler.setVisible(true);
                    renommage.setVisible(true);
                } else {
                    ta.append("Il n'y a pas de dossiers CF dans " + directory);
                }

                pack();

                int x = 0;
                /*for (File f : directory.listFiles((d, n) -> {
                 return n.matches("[A-Z]{2}?[0-9]{2}?-[0-9]{5}-[A-Z][0-9]{2}-[0-9]{2}\\.pdf");
                 })) {

                 PanneauRenommageDossiersPDF dcpdf = new PanneauRenommageDossiersPDF(f);
                 System.out.println("---------------------------------------------------");
                 System.out.println(f.getName());
                 System.out.println(dcpdf.firstline);
                 System.out.println("Prénom : " + dcpdf.firstname);
                 System.out.println("Nom : " + dcpdf.lastname);
                 ++x;
                 File dest = new File(f.getParentFile(), dcpdf.lastname + " " + dcpdf.firstname + "_" + f.getName());
                 f.renameTo(dest);
                 }
                 System.out.printf("%d fichiers traités\n", x);*/

                try (PrintWriter out = new PrintWriter(new FileWriter(config))) {
                    out.println(directory.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(RenommageDossiersCF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (e.getSource() == annuler) {
            list.clear();
            choix.setVisible(true);
            annuler.setVisible(false);
            renommage.setVisible(false);
        } else if (e.getSource() == renommage) {
            choix.setVisible(true);
            annuler.setVisible(false);
            renommage.setVisible(false);
            for (File f : list) {
                PanneauRenommageDossiersPDF dcpdf = new PanneauRenommageDossiersPDF(f);
                File dest = new File(f.getParentFile(), dcpdf.lastname + " " + dcpdf.firstname + "_" + f.getName());                
                ta.append(f.getName() + " ==> " + dest.getName() + "\n");
                f.renameTo(dest);
            }
        }
    }

}
