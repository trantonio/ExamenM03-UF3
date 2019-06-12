/*
 * JodaDT.java
 * 
 * Copyright 2010-2012 Joaquim Laplana
 * 
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Implements JodaTime library utilities.
 */
public class JodaDT {

    /**
     * Constructor. Private to forbid instances.
     */
    private JodaDT() {
    }

    /**
     * Creates a DateTime object from the milliseconds attribute of another
     * DateTime object. It's a kind of cloner.
     * 
     * @param dateTime a DateTime object
     * @return a DateTime object
     */
    public static DateTime rebuild(DateTime dateTime) {
        if (dateTime != null) {
            long milis = dateTime.getMillis();
            DateTime dateTime2 = new DateTime(milis);
            return new DateTime(dateTime2);
        } else {
            return null;
        }

    }

    /**
     * Creates a DateTime object from a String with this format
     * DD/MM/YYYY-hh:mm:ss.
     * 
     * @param date with format DD/MM/YYYY-hh:mm:ss
     * @return a DateTime object or null
     */
    public static DateTime parseDDMMYYYYhhmmss(String date) {
        if (date != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            dtfb.appendLiteral('-');
            dtfb.appendHourOfDay(2);
            dtfb.appendLiteral(':');
            dtfb.appendMinuteOfHour(2);
            dtfb.appendLiteral(':');
            dtfb.appendSecondOfMinute(2);
            DateTimeFormatter dtf = dtfb.toFormatter();
            DateTime dt = dtf.parseDateTime(date);
            return dt;
        } else {
            return null;
        }
    }

    /**
     * Creates a DateTime object from a String with this format
     * DD/MM/YYYY-hh:mm.
     * 
     * @param date with format DD/MM/YYYY-hh:mm
     * @return a DateTime object or null
     */
    public static DateTime parseDDMMYYYYhhmm(String date) {
        if (date != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            dtfb.appendLiteral('-');
            dtfb.appendHourOfDay(2);
            dtfb.appendLiteral(':');
            dtfb.appendMinuteOfHour(2);
            DateTimeFormatter dtf = dtfb.toFormatter();
            DateTime dt = dtf.parseDateTime(date);
            return dt;
        } else {
            return null;
        }
    }

    /**
     * Creates a DateTime object from a String with this format DD/MM/YYYY-hh
     * 
     * @param date with format DD/MM/YYYY-hh
     * @return a DateTime object or null
     */
    public static DateTime parseDDMMYYYYhh(String data) {
        if (data != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            dtfb.appendLiteral('-');
            dtfb.appendHourOfDay(2);
            DateTimeFormatter dtf = dtfb.toFormatter();
            DateTime dt = dtf.parseDateTime(data);
            return dt;
        } else {
            return null;
        }
    }

    /**
     * Creates a DateTime object from a String with this format DD/MM/YYYY
     * 
     * @param date with format DD/MM/YYYY
     * @return a DateTime object or null
     */
    public static DateTime parseDDMMYYYY(String data) {
        if (data != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            DateTimeFormatter dtf = dtfb.toFormatter();
            DateTime dt = dtf.parseDateTime(data);
            return dt;
        } else {
            return null;
        }
    }

    /**
     * Converts a DateTimeObject into a string with format DD/MM/YYYY-hh:mm:ss.
     * 
     * @param dt a DateTime object
     * @return the formatted String or null
     */
    public static String formatDDMMYYYYhhmmss(DateTime dt) {
        if (dt != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            dtfb.appendLiteral('-');
            dtfb.appendHourOfDay(2);
            dtfb.appendLiteral(':');
            dtfb.appendMinuteOfHour(2);
            dtfb.appendLiteral(':');
            dtfb.appendSecondOfMinute(2);
            DateTimeFormatter dtf = dtfb.toFormatter();
            String str = dt.toString(dtf);
            return str;
        } else {
            return null;
        }
    }

    /**
     * Converts a DateTimeObject into a string with format DD/MM/YYYY-hh:mm.
     * 
     * @param dt a DateTime object
     * @return the formatted String or null
     */
    public static String formatDDMMYYYYhhmm(DateTime dt) {
        if (dt != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            dtfb.appendLiteral('-');
            dtfb.appendHourOfDay(2);
            dtfb.appendLiteral(':');
            dtfb.appendMinuteOfHour(2);
            DateTimeFormatter dtf = dtfb.toFormatter();
            String str = dt.toString(dtf);
            return str;
        } else {
            return null;
        }
    }

    /**
     * Converts a DateTimeObject into a string with format DD/MM/YYYY-hh.
     * 
     * @param dt a DateTime object
     * @return the formatted String or null
     */
    public static String formatDDMMYYYYhh(DateTime dt) {
        if (dt != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            dtfb.appendLiteral('-');
            dtfb.appendHourOfDay(2);
            DateTimeFormatter dtf = dtfb.toFormatter();
            String str = dt.toString(dtf);
            return str;
        } else {
            return null;
        }
    }

    /**
     * Converts a DateTimeObject into a string with format DD/MM/YYYY
     * 
     * @param dt a DateTime object
     * @return the formatted String or null
     */
    public static String formatDDMMYYYY(DateTime dt) {
        if (dt != null) {
            DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
            dtfb.appendDayOfMonth(2);
            dtfb.appendLiteral('/');
            dtfb.appendMonthOfYear(2);
            dtfb.appendLiteral('/');
            dtfb.appendYear(4, 4);
            DateTimeFormatter dtf = dtfb.toFormatter();
            String str = dt.toString(dtf);
            return str;
        } else {
            return null;
        }
    }

    /**
     * Checks if an instant dt is within the time interval (dt1, dt2).
     * 
     * @param dt an instant
     * @param dt1 the lower limit of the interval
     * @param dt2 the upper limit of the interval
     * @return true if dt is within (dt1, dt2), false otherwise
     */
    public static boolean isInInterval(DateTime dt, DateTime dt1, DateTime dt2) {
        if (dt != null && dt1 != null && dt2 != null) {
            boolean cond = dt.isAfter(dt1) && dt.isBefore(dt2);
            return cond;
        }
        return false;
    }

    /**
     * Checks if a time interval (dt1,dt2) is within another time interval (dt3,
     * dt4).
     * 
     * @param dt1 the lower limit of the first interval
     * @param dt2 the upper limit of the first interval
     * @param dt3 the lower limit of the second interval
     * @param dt4 the upper limit of the second interval
     * @return true if (dt1, dt2) is within (dt3, dt4), false otherwise
     */
    public static boolean isInInterval(DateTime dt1, DateTime dt2, DateTime dt3, DateTime dt4) {
        if (dt1 != null && dt2 != null && dt3 != null && dt4 != null) {
            boolean cond = dt1.isAfter(dt3) && dt2.isBefore(dt4);
            return cond;
        }
        return false;
    }

    /**
     * Calculates the number of seconds between two instants dt1 and dt2.
     * 
     * @param dt1 an instant
     * @param dt2 an instant
     * @return the number of seconds
     */
    public static long durationInSeconds(DateTime dt1, DateTime dt2) {
        if (dt1 != null && dt2 != null) {
            long segons = (dt2.getMillis() - dt1.getMillis()) / 1000;
            return segons;
        }
        return 0;
    }
}
