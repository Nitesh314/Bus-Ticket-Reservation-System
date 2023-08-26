package com.bus.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Convert Date to String
    public static String formatDateToString(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMAT.format(date);
    }

    // Convert String to Date
    public static Date parseStringToDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the parsing exception appropriately
            return null;
        }
    }
}
