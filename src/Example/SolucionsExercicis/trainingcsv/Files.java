/*
 * Files.java
 * 
 * Copyright 2005-2012 Joaquim Laplana
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

/**
 * Utility methods for manipulating files.
 */
public abstract class Files {

    private static String[] arrayLines = null;

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
     * Reads all lines of a local CSV file and stores them in the field
     * arrayLines.
     * 
     * @param fileName the file's name
     */
    private static void readFile(final String fileName) {
        BufferedReader br = null;
        String readLine = "";
        int nl = countLines(fileName);
        arrayLines = new String[nl];
        try {
            final File textFile = new File(fileName);
            final FileReader fileReader = new FileReader(textFile);
            br = new BufferedReader(fileReader);
            for (int i = 0; i < nl; i++) {
                readLine = br.readLine();
                arrayLines[i] = readLine;
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
     * Sorts arrayLines by one of its fields.
     * 
     * @param fn the number of the field we want to order by.
     * @param ft the type of the field (1 int, 2 double, 3 String)
     * @param fs the field separator
     */
    private static void sort(final int fn, final int ft, final String fs) {
        if (ft == 1) {
            int n = arrayLines.length - 1;
            for (int pass = 1; pass < n; pass++) {
                for (int i = 1; i < n - pass + 1; i++) {
                    String[] fields1 = arrayLines[i].split(fs);
                    String[] fields2 = arrayLines[i + 1].split(fs);
                    int field1 = Integer.parseInt(fields1[fn]);
                    int field2 = Integer.parseInt(fields2[fn]);
                    if (field2 < field1) {
                        String temp = arrayLines[i];
                        arrayLines[i] = arrayLines[i + 1];
                        arrayLines[i + 1] = temp;
                    }
                }
            }
        } else if (ft == 2) {
            int n = arrayLines.length - 1;
            for (int pass = 1; pass < n; pass++) {
                for (int i = 1; i < n - pass; i++) {
                    String[] fields1 = arrayLines[i].split(fs);
                    String[] fields2 = arrayLines[i + 1].split(fs);
                    double field1 = Double.parseDouble(fields1[fn]);
                    double field2 = Double.parseDouble(fields2[fn]);
                    if (field2 < field1) {
                        String temp = arrayLines[i];
                        arrayLines[i] = arrayLines[i + 1];
                        arrayLines[i + 1] = temp;
                    }
                }
            }
        } else {
            int n = arrayLines.length - 1;
            for (int pass = 1; pass < n; pass++) {
                for (int i = 1; i < n - pass; i++) {
                    String[] fields1 = arrayLines[i].split(fs);
                    String[] fields2 = arrayLines[i + 1].split(fs);
                    String field1 = fields1[fn];
                    String field2 = fields2[fn];
                    final boolean cond = 0 < field1.toLowerCase().compareTo(field2.toLowerCase());
                    if (cond) {
                        String temp = arrayLines[i];
                        arrayLines[i] = arrayLines[i + 1];
                        arrayLines[i + 1] = temp;
                    }
                }
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
    public static long createOrderedCsvFile(final String sourceFileName,
            final String targetFileName, final int fn, final int ft, final String fs) {
        PrintWriter pw = null;
        readFile(sourceFileName);
        final long t1 = System.currentTimeMillis();
        sort(fn, ft, fs);
        final long t2 = System.currentTimeMillis();
        final long sortTime = t2 - t1;
        try {
            final File targetFile = new File(targetFileName);
            final FileWriter fileWriter = new FileWriter(targetFile);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            pw = new PrintWriter(bufferedWriter);
            for (int i = 0; i < arrayLines.length; i++) {
                pw.println(arrayLines[i]);
            }
        } catch (final FileNotFoundException ex) {
            System.err.println("S'ha produit una FileNotFoundException: " + ex.getMessage());
        } catch (final IOException ex) {
            System.err.println("S'ha produit una IOException: " + ex.getMessage());
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (final Exception ex) {
                System.err.println("S'ha produit una Exception: " + ex.getMessage());
            }
        }
        return sortTime;
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
