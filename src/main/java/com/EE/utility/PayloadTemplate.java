package com.EE.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class PayloadTemplate {

    private String payloadTemplate = "{\n" +
            "  \"firstname\":" + "\""+ScenarioContext.tobe_registered_firstname + "\",\n" +
            "  \"lastname\":\"TestLastName\",\n" +
            "  \"totalprice\":\"12\",\n" +
            "  \"depositpaid\":\"true\",\n" +
            "  \"bookingdates\":{\n" +
            "    \"checkin\":\""+ ScenarioContext.getCheck_in_date()+"\",\n" +
            "    \"checkout\":\""+ ScenarioContext.getCheck_out_date() +"\"\n" +
            "  }\n" +
            "}";

    void writeToJSONFile(File file) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(payloadTemplate);

        out.close();
    }
}
