package Example.Help;/*
 * Files.java
 * 
 * Copyright 2005-2012 Joaquim Laplana
 * Copyright 2011 Lázaro Márquez Pérez
 * Copyright 2012 Mònica Ramírez Arceda
 * 
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Utility methods for manipulating files.
 */
public abstract class Files {

    private static ArrayList<String> lines;

    /**
     * Removes a local file.
     * 
     * @param fileName the file's name
     * @return true if the file has been removed, false otherwise
     */
    public static boolean delete(final String fileName) {
        final File file = new File(fileName);
        final boolean cond = file.delete();
        return cond;
    }

    /**
     * Renames a local file.
     * 
     * @param fileName1 the name of the file to be renamed
     * @param fileName2 the new file name
     * @return true if the file has been renamed, false otherwise
     */
    public static boolean rename(final String fileName1, final String fileName2) {
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        final boolean cond = file1.renameTo(file2);
        return cond;
    }

    /**
     * Counts how many lines a text file has.
     * 
     * @param fileName the file's name
     * @return the number of lines of the file
     */
    private static int countLines(final String fileName) {
        BufferedReader br = null;
        int nl = 0;
        try {
            final File fitxerText = new File(fileName);
            final FileReader fileReader = new FileReader(fitxerText);
            br = new BufferedReader(fileReader);
            while (br.readLine() != null) {
                nl++;
            }
        } catch (final FileNotFoundException ex) {
            System.err.println("S'ha produit una FileNotFoundException: " + ex.getMessage());
        } catch (final IOException ex) {
            System.err.println("S'ha produit una IOException: " + ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (final IOException ex) {
                System.err.println("S'ha produit una IOException: " + ex.getMessage());
            }
        }
        return nl;
    }

    /**
     * Reads all lines of a local CSV file and stores them in the field arrayLines.
     * 
     * @param fileName the file's name
     */
    private static void readFile(final String fileName) {
        BufferedReader br = null;
        String readLine = "";
        int nl = countLines(fileName);
        lines = new ArrayList<String>();
        try {
            final File textFile = new File(fileName);
            final FileReader fileReader = new FileReader(textFile);
            br = new BufferedReader(fileReader);
            for (int i = 0; i < nl; i++) {
                readLine = br.readLine();
                lines.add(readLine);
            }
        } catch (final FileNotFoundException ex) {
            System.err.println("S'ha produit una FileNotFoundException: " + ex.getMessage());
        } catch (final IOException ex) {
            System.err.println("S'ha produit una IOException: " + ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (final IOException ex) {
                System.err.println("S'ha produit una IOException: " + ex.getMessage());
            }
        }
    }

    /**
     * Sorts a CSV file by one of its fields.
     * 
     * @param sourceFileName the source file's name
     * @param targetFileName the target file's name
     * @param fn the number of the field we want to order by
     * @param ft the type of the field (1 int, 2 double, 3 String)
     * @param fs the field separator
     * @return time wasted to order the file, in ms
     */
    public static long createOrderedCsvFile(String sourceFileName, String targetFileName, int fn,
            int ft, String fs) {
        final long t1 = System.currentTimeMillis();
        PrintWriter w = null;
        readFile(sourceFileName);
        try {
            final File targetFile = new File(targetFileName);
            final FileWriter fileWriter = new FileWriter(targetFile);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            w = new PrintWriter(bufferedWriter);
            if (ft == 1 || ft == 2) {
                // Build a number container
                ArrayList<Double> numbers = new ArrayList<Double>();
                // For each element of the lines, save in numbers the corresponding numeric value
                for (String lin : lines) {
                    numbers.add(Double.parseDouble(lin.split(fs)[fn]));
                }
                // Sort numbers
                Collections.sort(numbers);
                // For each element in numbers find the corresponding value in lines
                for (int i = 0; i < numbers.size(); i++) {
                    for (int j = 0; j < lines.size(); j++) {
                        if (Double.parseDouble(lines.get(j).split(fs)[fn]) == numbers.get(i)) {
                            w.print(lines.get(j) + System.getProperty("line.separator"));
                            lines.remove(j);
                        }
                    }
                }
                w.close();
            } else {
                // Build a String container
                ArrayList<String> fields = new ArrayList<String>();
                // For each element of lines, save the corresponding field to fields
                for (String lin : lines) {
                    fields.add(lin.split(fs)[fn]);
                }
                // Sort fields
                Collections.sort(fields);
                // For each element of fields, find the correponding value in lines
                for (int i = 0; i < fields.size(); i++) {
                    for (int j = 0; j < lines.size(); j++) {
                        if (lines.get(j).split(fs)[fn].equals(fields.get(i))) {
                            w.print(lines.get(j) + System.getProperty("line.separator"));
                            lines.remove(j);
                        }
                    }
                }
            }
            w.close();
        } catch (final FileNotFoundException ex) {
            System.err.println("S'ha produit una FileNotFoundException: " + ex.getMessage());
        } catch (final IOException ex) {
            System.err.println("S'ha produit una IOException: " + ex.getMessage());
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (final Exception ex) {
                System.err.println("S'ha produit una Exception: " + ex.getMessage());
            }
        }
        final long t2 = System.currentTimeMillis();
        return t2 - t1;
    }

    /**
     * Finds out if two files are equals, character by character.
     * 
     * @param fileName1 the file's name of one of the files
     * @param fileName2 the file's name of the other file
     * @return true if they are equals, false otherwise
     */
    public static boolean areEquals(final String fileName1, final String fileName2) {
        final Reader r1 = new Reader(fileName1);
        final Reader r2 = new Reader(fileName2);
        int c1 = r1.read();
        int c2 = r2.read();
        boolean found = false;
        while ((c1 != -1 && c2 != -1) && !found) {
            found = c1 != c2 ? true : false;
            c1 = r1.read();
            c2 = r2.read();
        }
        if (!found) {
            found = (c1 == -1 ^ c2 == -1) ? true : false;
        }
        r1.close();
        r2.close();
        return !found;
    }
}
