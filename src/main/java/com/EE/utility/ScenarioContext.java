package com.EE.utility;

import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScenarioContext {

    public static SimpleDateFormat formatter = new SimpleDateFormat("mmssSSS");
    public static Date date = new Date();
    public static int bookingID;
    public static String tobe_registered_firstname="Test"+ formatter.format(date);
    public static String booked_first_name;
    public static Response response = null;
    public static JSONParser jsonParser = new JSONParser();
}
