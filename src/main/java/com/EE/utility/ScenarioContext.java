package com.EE.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScenarioContext {

    public static SimpleDateFormat formatter = new SimpleDateFormat("mmssSSS");
    public static Date date = new Date();
    public static int bookingID;
    public static String tobe_registered_firstname="Test"+ formatter.format(date);
    public static String booked_first_name;
}
