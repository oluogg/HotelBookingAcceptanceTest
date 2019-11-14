package com.EE.utility;

import cucumber.api.Scenario;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.EE.utility.Hooks.driver;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Support {


    private static Properties configProperties = new Properties();
    private PayloadTemplate payloadTemplate = new PayloadTemplate();


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
        ScenarioContext.response = when().get(uri);
    }


    public void postRequest(String jsonFile, String uri) throws IOException {
        File file = new File("payload/" + jsonFile);
        payloadTemplate.writeToJSONFile(file);
        ScenarioContext.response = given().contentType(ContentType.JSON).body(file).post(uri);
        ScenarioContext.bookingID = ScenarioContext.response.jsonPath().getInt("bookingid");
        ScenarioContext.booked_first_name = ScenarioContext.response.jsonPath().getString("booking.firstname");
    }

    public void deleteRequest(String uri){

        ScenarioContext.response = given().when().contentType(ContentType.JSON).delete(uri+"/"+ScenarioContext.bookingID);
    }


    public void verifyResponseCode(int statusCode){
        System.out.println(ScenarioContext.response.body().print());
        Assert.assertEquals(statusCode, ScenarioContext.response.getStatusCode());
    }

    public void refreshPage(){
        driver.navigate().refresh();;
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
