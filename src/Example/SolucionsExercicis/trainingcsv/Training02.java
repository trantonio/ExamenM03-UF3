/*
 * Training02.java   
 *
 * Copyright 2010 Mònica Ramírez Arceda <mramirez@escoladeltreball.org>
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 *
 */

/**
 * Training text files queries.
 */
public class Training02 {

    /**
     * Cities, objectives and sales of a region where the current sales are greater than the objectives, ordered by
     * cities.
     *  
     * @param region the region
     */
    public static void operation11(String region) {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[4] + "\t" + fields[5];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            String regionR = fields[2];
            double objective = Double.parseDouble(fields[4]);
            double sales = Double.parseDouble(fields[5]);
            if (regionR.equals(region) && sales > objective) {
                l = fields[1] + "\t" + fields[4] + "\t" + fields[5];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output11.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Average of objectives and current sales of a region.
     * 
     * @param region the region
     */
    public static void operation12(String region) {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("output12.dat");
        double objectiveAvg = 0, salesTotal = 0;
        int n = 0;
        w.println("OBJ.MITJÀ\tVENDES");
        String record = r.readLine();
        record = r.readLine();
        while (record != null) {
            String[] fields = record.split("\\s+");
            String regionR = fields[2];
            if (regionR.equals(region)) {
                double objective = Double.parseDouble(fields[4]);
                double sales = Double.parseDouble(fields[5]);
                salesTotal = salesTotal + sales;
                objectiveAvg = objectiveAvg + objective;
                n++;
            }
            record = r.readLine();
        }
        objectiveAvg = objectiveAvg / n;
        w.println(objectiveAvg + " " + salesTotal);
        r.close();
        w.close();
    }

    /**
     * Names, offices and contract dates of all salesmen. 
     */
    public static void operation13() {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[2] + "\t" + fields[4] + "\t" + fields[6];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            l = fields[1] + "\t" + fields[2] + "\t" + fields[4] + "\t" + fields[6];
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output13.dat", 1, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Name, quota and current sales of an employee.
     * 
     * @param idEmp employee's code.
     */
    public static void operation14(int idEmp) {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("output14.dat");
        boolean found = false;
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[2] + "\t" + fields[8] + "\t" + fields[9];
        w.println(l);
        record = r.readLine();
        while (record != null && !found) {
            fields = record.split("\\s+");
            int idEmpR = Integer.parseInt(fields[0]);
            if (idEmp == idEmpR) {
                l = fields[1] + "\t" + fields[2] + "\t" + fields[8] + "\t" + fields[9];
                w.println(l);
                found = true;
            }
            record = r.readLine();
        }
        r.close();
        w.close();
    }

    /**
     * Average of all sales.
     */
    public static void operation15() {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("output15.dat");
        double tSales = 0;
        int n = 0;
        w.println("MITJA VENDES");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            double sales = Double.parseDouble(fields[9]);
            tSales += sales;
            n++;
            record = r.readLine();
        }
        w.println(tSales / n);
        r.close();
        w.close();
    }

    /**
     * Names and contract dates of salesmen that have current sales greater than a given value.
     * 
     * @param value the value.
     */
    public static void operation16(double value) {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[2] + "\t" + fields[6];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            double sales = Double.parseDouble(fields[9]);
            if (sales > value) {
                l = fields[1] + "\t" + fields[2] + "\t" + fields[6];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output16.dat", 1, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Stock of each product.
     */
    public static void operation17() {
        Reader r = new Reader("productos.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\tVALOR INVENTARI";
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            double price = Double.parseDouble(fields[3]);
            double stock = Double.parseDouble(fields[4]);
            l = fields[1] + "\t" + price * stock;
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output17.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Name, quota and increased quota by a given value of current sales.
     * 
     * @param percent the percentage
     */
    public static void operation18(double percent) {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[2] + "\t" + fields[8] + "\tQUOTA INCREMENTADA";
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            if (!fields[8].equals("\\N")) {
                double quota = Double.parseDouble(fields[8]);
                double incQuota = quota + quota * percent / 100;
                l = fields[1] + "\t" + fields[2] + "\t" + fields[8] + "\t" + incQuota;
            } else {
                l = fields[1] + "\t" + fields[2] + "\t" + fields[8] + "\t\\N";
            }
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output18.dat", 1, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Cities, current sales, objectives of offices where current sales are lesser than a given value.
     * 
     * @param percent the percentage
     */
    public static void operation19(double percent) {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[5] + "\t" + fields[4];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            double sales = Double.parseDouble(fields[5]);
            double objective = Double.parseDouble(fields[4]);
            if (sales < percent * objective / 100) {
                l = fields[1] + "\t" + fields[5] + "\t" + fields[4];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output19.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Cities where there are offices that are not managed by an employee.
     * 
     * @param idEmp the employee's id
     */
    public static void operation20(int idEmp) {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            int idManager = Integer.parseInt(fields[3]);
            if (idManager != idEmp) {
                l = fields[1];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output20.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }
}
