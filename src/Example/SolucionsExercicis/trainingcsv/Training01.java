/*
 * Training01.java 		
 *
 * Copyright 2010 Mònica Ramírez Arceda <mramirez@escoladeltreball.org>
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 *
 */

import org.joda.time.DateTime;

/**
 * Training text files queries.
 */
public class Training01 {
    /**
     * Offices, cities where the offices are and current sales.
     */
    public static void operation01() {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        while (record != null) {
            String[] fields = record.split("\\s+");
            String office = fields[0];
            String city = fields[1];
            String sales = fields[5];
            String l = office + "\t" + city + "\t" + sales;
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output01.dat", 1, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Salesmen names, offices where they work, current sales and quota of each salesman.
     */
    public static void operation02() {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        while (record != null) {
            String[] fields = record.split("\\s+");
            String l = fields[2] + "\t" + fields[1] + "\t" + fields[4] + "\t" + fields[8] + "\t" + fields[9];
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output02.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Salesmen names, current sales, quota of each salesman, deviation of quota relative to the sales.
     */
    public static void operation03() {
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[2] + "\t" + fields[1] + "\t" + fields[4] + "\t" + fields[8] + "\t" + fields[9];
        w.println(l + "\tDESVIACIÓ");
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            if (!fields[8].equals("\\N") && !fields[9].equals("\\N")) {
                double deviation = Double.parseDouble(fields[9]) - Double.parseDouble(fields[8]);
                l = fields[2] + "\t" + fields[1] + "\t" + fields[4] + "\t" + fields[8] + "\t" + fields[9] + "\t"
                        + deviation;
            } else {
                l = fields[2] + "\t" + fields[1] + "\t" + fields[4] + "\t" + fields[8] + "\t" + fields[9] + "\t\\N";
            }
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output03.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Order number, client, product, amount, where orders have imports greater than a given value, ordered by amount.
     * 
     * @param minAmount the given amount.
     */
    public static void operation04(double minAmoutn) {
        Reader r = new Reader("pedidos.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[0] + "\t" + fields[2] + "\t" + fields[5] + "\t" + fields[6] + "\t" + fields[7];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            if (Double.parseDouble(fields[7]) > minAmoutn) {
                l = fields[0] + "\t" + fields[2] + "\t" + fields[5] + "\t" + fields[6] + "\t" + fields[7];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output04.dat", 4, 2, "\t");
        Files.delete("outputtmp.dat");

    }

    /**
     * Sales average amount.
     */
    public static void operation05() {
        Reader r = new Reader("pedidos.dat");
        Writer w = new Writer("output05.dat");
        double sum = 0;
        int n = 0;
        w.println("IMPORT MITJÀ");
        String record = r.readLine();
        record = r.readLine();
        while (record != null) {
            String[] fields = record.split("\\s+");
            sum = sum + Double.parseDouble(fields[7]);
            n++;
            record = r.readLine();
        }
        w.println(sum / n);
        r.close();
        w.close();
    }

    /**
     * Sales average amount done by a client.
     * 
     * @param clientNumber the client number.
     */
    public static void operation06(int clientNumber) {
        Reader r = new Reader("pedidos.dat");
        Writer w = new Writer("output06.dat");
        double sum = 0;
        int n = 0;
        w.println("IMPORT MITJÀ");
        String record = r.readLine();
        record = r.readLine();
        while (record != null) {
            String[] fields = record.split("\\s+");
            if (Integer.parseInt(fields[2]) == clientNumber) {
                sum = sum + Double.parseDouble(fields[7]);
                n++;
            }
            record = r.readLine();
        }
        if (n == 0) {
            w.println(0);
        } else {
            w.println(sum / n);
        }
        r.close();
        w.close();
    }

    /**
     * Total amount of sales sent to each client.
     */
    public static void operation07() {
        Reader rc = new Reader("clientes.dat");
        Writer w = new Writer("output07.dat");
        w.println("CLIENT\tIMPORT");
        String record = rc.readLine();
        record = rc.readLine();
        while (record != null) {
            String[] fields = record.split("\\s+");
            int idClient = Integer.parseInt(fields[0]);
            Training01.operation06(idClient);
            Reader r06 = new Reader("output06.dat");
            String importClient = r06.readLine();
            w.println(fields[0] + "\t" + importClient);
            r06.close();
            record = rc.readLine();
        }
        rc.close();
        w.close();
    }

    /**
     * Salesmen names and contract date where dates are after a given date.
     * 
     * @param date the date with format dd/mm/yyyy
     */
    public static void operation08(String date) {
        DateTime dt = JodaDT.parseDDMMYYYY(date);
        Reader r = new Reader("repventas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[2] + "\t" + fields[1] + "\t" + fields[6];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            String d = fields[6];
            d = d.split("-")[2] + "/" + d.split("-")[1] + "/" + d.split("-")[0];
            DateTime drt = JodaDT.parseDDMMYYYY(d);
            if (drt.isAfter(dt.getMillis())) {
                l = fields[2] + "\t" + fields[1] + "\t" + fields[6];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output08.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Cities where there are offices where the objective is greater than a given value of sales quota plus a
     * percentage.
     * 
     * @param value the value
     * @param percent the percentage
     */
    public static void operation09(double value, double percent) {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1];
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            double objective = Double.parseDouble(fields[4]);
            double sales = Double.parseDouble(fields[5]);
            if (objective > value + sales + sales * percent / 100.) {
                l = fields[1];
                w.println(l);
            }
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output09.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

    /**
     * Cities, objectives, current sales, current sales in % relative to objectives.
     */
    public static void operation10() {
        Reader r = new Reader("oficinas.dat");
        Writer w = new Writer("outputtmp.dat");
        String record = r.readLine();
        String[] fields = record.split("\\s+");
        String l = fields[1] + "\t" + fields[4] + "\t" + fields[5] + "\t%";
        w.println(l);
        record = r.readLine();
        while (record != null) {
            fields = record.split("\\s+");
            double objective = Double.parseDouble(fields[4]);
            double sales = Double.parseDouble(fields[5]);
            double percent = sales / objective * 100;
            l = fields[1] + "\t" + fields[4] + "\t" + fields[5] + "\t" + percent;
            w.println(l);
            record = r.readLine();
        }
        r.close();
        w.close();
        Files.createOrderedCsvFile("outputtmp.dat", "output10.dat", 0, 3, "\t");
        Files.delete("outputtmp.dat");
    }

}
