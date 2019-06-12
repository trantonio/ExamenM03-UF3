/*
 * Crud.java             
 *
 * Copyright 2010 Joaquim Laplana, Mònica Ramírez Arceda
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 *
 */

/**
 * Modelizes file processing of CSV files.
 */
public class Crud {

    /** Master file path */
    private String masterFile;
    /** File path with new records */
    private String createFile;
    /** File path with records to be deleted */
    private String deleteFile;
    /** File path with records to be updated */
    private String updateFile;
    /** Fields delimiter */
    private String delimiter;

    // Constructor
    public Crud(String masterFile, String createFile, String deleteFile, String updateFile, String delimiter) {
        this.masterFile = masterFile;
        this.createFile = createFile;
        this.deleteFile = deleteFile;
        this.updateFile = updateFile;
        this.delimiter = delimiter;
    }

    /**
     * Creates new records.
     */
    public void create() {
        Reader m = new Reader(this.masterFile);
        Reader a = new Reader(this.createFile);
        Writer w = new Writer("masterWithNewRecords.dat");
        // Read headings
        String recMaster = m.readLine();
        String recCreate = a.readLine();
        String heading = recMaster;
        // Print heading
        w.println(heading);
        // Read second records
        recMaster = m.readLine();
        recCreate = a.readLine();
        // While there are records in both files...
        while (recMaster != null && recCreate != null) {
            // Get pk fields of each file
            String[] fieldsRecMaster = recMaster.split(this.delimiter);
            String pkRecMaster = fieldsRecMaster[0];
            String[] fieldsRecCreate = recCreate.split(this.delimiter);
            String pkRecCreate = fieldsRecCreate[0];
            // If pk field of master file is greater than pk field of create file...
            if (pkRecMaster.compareTo(pkRecCreate) < 0) {
                // Write record of master file
                w.println(recMaster);
                // Read a new record of master file
                recMaster = m.readLine();
            } else {
                // else, write and read records of create file
                w.println(recCreate);
                recCreate = a.readLine();
            }
        }
        // Write the remaining records of master file
        while (recMaster != null) {
            w.println(recMaster);
            recMaster = m.readLine();
        }
        // Write the remaining records of create file
        while (recCreate != null) {
            w.println(recCreate);
            recCreate = a.readLine();
        }
        m.close();
        a.close();
        w.close();
        // Remove master file
        Files.delete(this.masterFile);
        // Create new master file
        Files.rename("masterWithNewRecords.dat", this.masterFile);
    }

    /**
     * Deletes records.
     */
    public void delete() {
        Reader m = new Reader(masterFile);
        Reader b = new Reader(deleteFile);
        Writer w = new Writer("masterWithoutDeletedRecords.dat");
        String recMaster = m.readLine();
        String recDelete = b.readLine();
        String heading = recMaster;
        w.println(heading);
        recMaster = m.readLine();
        recDelete = b.readLine();
        // While there are records in both files...
        while (recMaster != null && recDelete != null) {
            String[] campsRegMestre = recMaster.split(delimiter);
            String pkRecMaster = campsRegMestre[0];
            String[] fieldsRecDelete = recDelete.split(delimiter);
            String pkRecDelete = fieldsRecDelete[0];
            // If the record must be deleted...
            if (pkRecMaster.equals(pkRecDelete)) {
                // Read the next record to be deleted
                recDelete = b.readLine();
            } else { // If the record must be kept...
                // Copy the record
                w.println(recMaster);
            }
            recMaster = m.readLine(); // Read the following record of master file
        }
        // Process the remaining records of master file
        while (recMaster != null) {
            String[] fieldsRecMaster = recMaster.split(delimiter);
            String pkRecMaster = fieldsRecMaster[0];
            w.println(recMaster);
            recMaster = m.readLine(); // Read the following master record
        }
        m.close();
        b.close();
        w.close();
        Files.delete(masterFile); // Remove master file
        // Create the new master file
        Files.rename("masterWithoutDeletedRecords.dat", masterFile);
    }

    /**
     * Updates records.
     */
    public void update() {
        Reader m = new Reader(masterFile);
        Reader c = new Reader(updateFile);
        Writer w = new Writer("masterWithUpdates.dat");
        String recMaster = m.readLine();
        String recUpdate = c.readLine();
        String heading = recMaster;
        w.println(heading);
        recMaster = m.readLine();
        recUpdate = c.readLine();
        // While there are records in both files...
        while (recMaster != null && recUpdate != null) {
            String[] fieldsRecMaster = recMaster.split(delimiter);
            String pkRecMaster = fieldsRecMaster[0];
            String[] fieldsRecUpdate = recUpdate.split(delimiter);
            String pkRecUpdate = fieldsRecUpdate[0];
            // If the record must be updated
            if (pkRecMaster.equals(pkRecUpdate)) {
                w.println(recUpdate); // Write updated record
                if (recUpdate != null) { // If there are more records to update...
                    recUpdate = c.readLine(); // Process the following record
                }
            } else { // If he record must be kept without updating
                w.println(recMaster); // Copy the record without changes
            }
            if (recMaster != null) { // If there are more records in master file
                recMaster = m.readLine(); // Read the following record
            }
        }
        // Process the remaining records of master file
        while (recMaster != null) {
            String[] fieldsRecMaster = recMaster.split(delimiter);
            String pkRecMaster = fieldsRecMaster[0];
            w.println(recMaster);
            recMaster = m.readLine(); // Read the following master record
        }
        m.close();
        c.close();
        w.close();
        // Delete master file
        Files.delete(masterFile);
        // Create the new master file
        Files.rename("masterWithUpdates.dat", masterFile);
    }

}
