package com.test.test;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Methods {

    public static java.sql.Date parse (String datum) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(datum);
            java.sql.Date dateSQL = new java.sql.Date(date.getTime());
            return dateSQL;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createObject(Date datum) {
        SQLcontrol sqlcontrol = new SQLcontrol();
        Object object = new Object(datum);
        sqlcontrol.insertDatum(object);
    }
}
