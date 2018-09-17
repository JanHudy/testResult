package com.test.test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Methods {

    public static java.sql.Date parseDate (String datum) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-M-d");
            LocalDate date = LocalDate.parse(datum, formatter);
            java.sql.Date dateSQL = Date.valueOf(date);
            return dateSQL;
        } catch (DateTimeParseException ex) {
            return null;
        }
    }


    public static void createObject(Date datum) {
        SQLcontrol sqlcontrol = new SQLcontrol();
        Object object = new Object(datum);
        sqlcontrol.insertDatum(object);
    }
}
