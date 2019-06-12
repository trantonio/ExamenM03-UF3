

/*
 * BookTxt.java             
 *
 * Copyright 2010 Joaquim Laplana, Mònica Ramírez Arceda
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 *
 */

/**
 * Plain text file processing.
 */
public class BookTxt {

    private String filePath;

    /**
     * Constructor.
     * 
     * @param filePath the file path of the book.
     */
    public BookTxt(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the content of the file in the terminal.
     */
    public void showFile() {
        Reader r = new Reader(this.filePath);
        String line = r.readLine();
        while (line != null) {
            System.out.printf("%s\n", line);
            line = r.readLine();
        }
        r.close();
    }

    /**
     * Counts how many lines the file has.
     * 
     * @return the number of lines.
     */
    public int countLines() {
        Reader r = new Reader(this.filePath);
        String line = r.readLine();
        int nLines = 0;
        while (line != null) {
            nLines++;
            line = r.readLine();
        }
        r.close();
        return nLines;
    }

    /**
     * Counts how many words the file has.
     * 
     * @return the number of words.
     */
    public int countWords() {
        Reader r = new Reader(this.filePath);
        String line = r.readLine();
        int nWords = 0;
        while (line != null) {
            // A word is a set of characters separated by blank spaces
            String[] words = line.split("\\s+");
            nWords += words.length;
            line = r.readLine();
        }
        r.close();
        return nWords;
    }

    /**
     * Counts how many characters the file has.
     * 
     * @return the number of characters.
     */
    public int countChars() {
        Reader r = new Reader(this.filePath);
        int nChars = 0;
        int c = r.read();
        while (c != -1) {
            nChars++;
            c = r.read();
        }
        r.close();
        return nChars;
    }

    /**
     * Makes a copy of the file.
     * 
     * @param outputFilePath the output file
     */
    public void copy(String outputFilePath) {
        Reader r = new Reader(this.filePath);
        Writer w = new Writer(outputFilePath);
        String line = r.readLine();
        while (line != null) {
            w.println(line);
            line = r.readLine();
        }
        r.close();
        w.close();
    }

    /**
     * Counts how many times a word appears in the file.
     * 
     * @param word the word to be counted
     * @return the number of times 'word' appears
     */
    public int countAWord(String word) {
        Reader r = new Reader(this.filePath);
        String line = r.readLine();
        int nWords = 0;
        while (line != null) {
            String[] words = line.split("\\s+");
            for (String w : words) {
                if (w.equals(word)) {
                    nWords++;
                }
            }
            line = r.readLine();
        }
        r.close();
        return nWords;
    }

    /**
     * Counts how many times a character appears in the file.
     * 
     * @param c the character to be counted
     * @return the number of times 'c' appears
     */
    public int countAChar(char c) {
        Reader r = new Reader(this.filePath);
        int nChars = 0;
        int cc = r.read();
        while (cc != -1) {
            if (cc == c) {
                nChars++;
            }
            cc = r.read();
        }
        r.close();
        return nChars;
    }

    /**
     * Finds out the book's title.
     * 
     * @return the book's title.
     */
    public String getTitle() {
        Reader r = new Reader(this.filePath);
        String line = null;
        for (int i = 0; i < 9; i++) {
            line = r.readLine();
        }
        line = line.substring(7);
        r.close();
        return line;
    }

    /**
     * Finds out the book's author.
     * 
     * @return the book's title.
     */
    public String getAuthor() {
        Reader r = new Reader(this.filePath);
        String line = null;
        for (int i = 0; i < 11; i++) {
            line = r.readLine();
        }
        line = line.substring(8);
        r.close();
        return line;
    }

    /**
     * Counts how many lines the book has.
     * 
     * @return the number of lines.
     */
    public int countLinesBook() {
        Reader r = new Reader(this.filePath);
        String line = r.readLine();
        boolean read = true;
        while (read) {
            if (line.length() > 40) {
                read = !line.substring(0, 41).equals("*** START OF THIS PROJECT GUTENBERG EBOOK");
            }
            line = r.readLine();
        }
        int nLines = 0;
        while (line != null) {
            nLines++;
            line = r.readLine();
        }
        r.close();
        return nLines;
    }
    
}
