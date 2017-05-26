/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import frames.FormulaireCampusFrance;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yvan
 */
public class TestRessource {

    public TestRessource() throws FileNotFoundException {
        File file = new File(getClass().getResource("initiales.txt").getFile());
        System.out.println(file);
        try (Scanner in = new Scanner(file)) {
            System.out.println(in.next());
        }
        try (PrintWriter out = new PrintWriter(new File(getClass().getResource("initiales.txt").getFile()))) {
            out.println("AB");
        } catch (IOException ex) {}
    }

    public static void main(String[] args) throws FileNotFoundException {
        //new TestRessource();
        //System.out.println(TestRessource.class.getResource("initiales.txt").getAuthority());
        /*System.out.println(TestRessource.class.getResource("initiales.txt").toExternalForm());
        try(PrintWriter out = new PrintWriter(TestRessource.class.getResource("initiales.txt").getFile())) {
            out.println("Good bye");
        }*/
        try(Scanner in = new Scanner(new File("main/initiales.txt"))) {
            System.out.println(in.nextLine());
            Properties prop;
        }
    }
}
