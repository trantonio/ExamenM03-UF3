/*
 * Reader.java
 * 
 * Copyright 2005-2012 Joaquim Laplana
 * 
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Modelizes reader objects of text files.
 */
public class Reader {

    private BufferedReader br = null;

    /**
     * Constructor
     */
    public Reader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Constructor.
     * 
     * @param fileName the file's name.
     */
    public Reader(final String fileName) {
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads a line from the keyboard or from the text file.
     * 
     * @return a string with the read line without the ending \n
     */
    public String readLine() {
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line;
    }

    /**
     * Reads a character from the keyboard or from the text file.
     * 
     * @return the int value of the read character or -1 if reading another
     *         character is not possible.
     */
    public int read() {
        int c = -1;
        try {
            c = br.read();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    /**
     * Closes the file.
     */
    public void close() {
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
