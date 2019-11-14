package com.EE.utility;

import com.EE.pageobject.HotelBookingPage;
import io.cucumber.core.api.Scenario;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.EE.utility.Hooks.driver;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Support {

    JSONParser jsonParser = new JSONParser();
    private static Properties configProperties = new Properties();
    PayloadTemplate payloadTemplate = new PayloadTemplate();
    private Response response = null;


    /* Reads the config data property file */
    private static Properties readConfigDataFIle() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        configProperties.load(fis);
        return configProperties;
    }


    /* Returns value of key passed as argument from the config data property file */
    public static String getConfigDataFileData(String required_data) throws IOException {

        configProperties = readConfigDataFIle();
        required_data = configProperties.getProperty(required_data);
        return required_data;
    }


    /* Insert text into text fields */
    public void enterText(By locator, String data) {
        driver.findElement(locator).sendKeys(data);
    }


    /* Selects from a list */
    public void select(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }


    /* Navigates to a page */
    public void gotoPage(String url) {
        driver.get(url);
    }


    /* Clicks on an element */
    public void click(By locator) {
        driver.findElement(locator).click();
    }


    public void getRequest(String uri){
        response = when().get(uri);
    }


    public void postRequest(String jsonFile, String uri) throws IOException {
        File file = new File("payload/" + jsonFile);
        payloadTemplate.writeToJSONFile(file);
        response = given().contentType(ContentType.JSON).body(file).post(uri);
//        ScenarioContext.bookingID = response.jsonPath().getInt("bookingid");
//        ScenarioContext.booked_first_name = response.jsonPath().getString("firstname");
    }

    public void deleteRequest(String uri){
        System.out.println("========+++++++++"+uri+"/"+ScenarioContext.bookingID);
        response = given().when().contentType(ContentType.JSON).delete(uri+"/"+ScenarioContext.bookingID);
    }


    public void verifyResponseCode(int statusCode){
        System.out.println(response.body().print());
        Assert.assertEquals(statusCode, response.getStatusCode());
    }


    /* Captures a screen shot of the page where a failure occurs */
    static void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) Hooks.driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
    }
}
