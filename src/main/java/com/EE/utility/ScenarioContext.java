package com.EE.utility;

import io.restassured.response.Response;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScenarioContext {

    public static SimpleDateFormat formatter1 = new SimpleDateFormat("mmssSSS");
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("YYYY-MM-dd");
    public static Date date = new Date();

    static int bookingID;
    public static String tobe_registered_firstname = "Test" + formatter1.format(date);
    public static String booked_first_name;
    static Response response = null;
    private static Calendar c = Calendar.getInstance();
    private static String check_in_date;

    public static String getCheck_in_date() {
        return check_in_date;
    }

    public static void setCheck_in_date(int date_increase) {
        c.setTime(date);
        c.add(Calendar.DATE, date_increase);
        check_in_date = formatter2.format(c.getTime());
    }

    public static String getCheck_out_date() {
        return check_out_date;
    }

    public static void setCheck_out_date(int date_increase) {
        c.setTime(date);
        c.add(Calendar.DATE, date_increase);
        check_out_date = formatter2.format(c.getTime());
    }

    private static String check_out_date;
}
