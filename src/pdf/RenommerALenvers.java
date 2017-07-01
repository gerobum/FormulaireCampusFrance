package pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 *
 * @author yvan
 */
public class RenommerALenvers {


 

    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("(.*)_([A-Z]{2}[0-9]{2}?-[0-9]{5}-[A-Z][0-9]{2}-[0-9]{2}\\.pdf)");
        File config = new File(".configuration.rename.dossierCF");
        File directory;
        if (Files.exists(config.toPath())) {
            try(BufferedReader in = new BufferedReader(new FileReader(config))) {
                directory = new File(in.readLine());
            } 
        } else {
            directory = new File(".");
        }
        JFileChooser directoryChooser = new JFileChooser(directory);
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
  
        if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            directory = directoryChooser.getSelectedFile();
            int x = 0;
            for (File f : directory.listFiles()) {
              
                System.out.println("---------------------------------------------------");
                System.out.println(f.getName());
         
                Matcher m = pattern.matcher(f.getName());
                if (m.matches()) {
                    //System.out.println(m.group(1));
                    //System.out.println(m.group(2));
                    File dest = new File(f.getParent(), m.group(2));
                    f.renameTo(dest);
                }
                ++x;
            }
            System.out.printf("%d fichiers traités\n", x);
            try(PrintWriter out = new PrintWriter(new FileWriter(config))) {
                out.println(directory.getAbsolutePath());
            } 
        }
    }
}
