package com.EE.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PayloadTemplate {

    private String payloadTemplate = "{\n" +
            "  \"firstname\":" + "\""+ScenarioContext.tobe_registered_firstname + "\",\n" +
            "  \"lastname\":\"TestLastName\",\n" +
            "  \"totalprice\":\"12\",\n" +
            "  \"depositpaid\":\"true\",\n" +
            "  \"bookingdates\":{\n" +
            "    \"checkin\":\"2019-11-14\",\n" +
            "    \"checkout\":\"2019-11-14\"\n" +
            "  }\n" +
            "}";

    public void writeToJSONFile(File file) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(payloadTemplate);

        out.close();
    }
}
