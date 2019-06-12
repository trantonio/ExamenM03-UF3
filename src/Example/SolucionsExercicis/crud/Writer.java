/*
 * Writer.java
 * 
 * Copyright 2005-2012 Joaquim Laplana
 * 
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Modelizes writer objects of text files.
 */
public class Writer {

    private PrintWriter pw = null;

    /**
     * Constructor.
     */
    public Writer() {
        pw = new PrintWriter(System.out);
    }

    /**
     * Constructor.
     */
    public Writer(final String fileName) {
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes a char in the text file.
     * 
     * @param c a character
     */
    public void print(final char c) {
        pw.print(c);
    }

    /**
     * Writes a string in the text file.
     * 
     * @param str a string
     */
    public void print(final String str) {
        pw.print(str);
    }

    /**
     * Writes an integer number of type int in the text file.
     * 
     * @param num an integer number
     */
    public void print(final int num) {
        pw.print(num);
    }

    /**
     * Writes a real number of type double in the text file.
     * 
     * @param num a real number
     */
    public void print(final double num) {
        pw.print(num);
    }

    /**
     * Writes a string plus a break line in the text file.
     * 
     * @param str a string
     */
    public void println(final String str) {
        pw.println(str);
    }

    /**
     * Writes an integer number of type int plus a break line in the text file.
     * 
     * @param num an integer number
     */
    public void println(final int num) {
        pw.println(num);
    }

    /**
     * Writes a real number of type double plus a break line in the text file.
     * 
     * @param num a real number
     */
    public void println(final double num) {
        pw.println(num);
    }

    /**
     * Closes the buffer.
     */
    public void close() {
        pw.close();
    }
}
