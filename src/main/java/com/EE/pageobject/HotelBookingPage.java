package com.EE.pageobject;

import com.EE.utility.ScenarioContext;
import com.EE.utility.Support;
import cucumber.api.DataTable;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.EE.utility.Hooks.driver;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class HotelBookingPage {

    private Support support;
    private static By created_user_locator;
    private static By delete_user;
    private static By first_name = By.id("firstname");
    private static By last_name = By.id("lastname");
    private static By price = By.id("totalprice");
    private static By deposit = By.id("depositpaid");
    private static By check_in = By.id("checkin");
    private static By check_out = By.id("checkout");
    private static By delete_btn = By.xpath("//*[@type = 'button']");


    public static String getUser_created_name() {
        return user_created_name;
    }


    private static void setUser_created_name(String user_created_name) {
        HotelBookingPage.user_created_name = user_created_name;
    }


    private static String user_created_name;
    //    private static String displayed_name;


    public HotelBookingPage() {
        support = new Support();
    }


    /*completes the form with data from the feature file*/
    public void completeHotelBooking(DataTable data) throws InterruptedException {
        By save_btn = By.xpath("//input[@id = 'firstname']/following::input[@type]");
        Map<String, String> m = data.asMap(String.class, String.class);
        setUser_created_name(m.get("first_name") + ScenarioContext.formatter.format(ScenarioContext.date));
        support.enterText(first_name, user_created_name);
        support.enterText(last_name, m.get("last_name"));
        support.enterText(price, m.get("price"));
        support.select(deposit, m.get("deposit"));
        support.enterText(check_in, m.get("check_in"));
        support.enterText(check_out, m.get("check_out"));
        support.click(save_btn);
    }


    /* Verifies a user is either displayed or not */
    public void verifyStatus(Boolean status) throws InterruptedException {
        created_user_locator = By.xpath("//*[text() = '" + user_created_name + "']");

        if (status.equals(true)) {
            Assert.assertEquals(driver.findElement(created_user_locator).isDisplayed(), status);
        } else
            Assert.assertEquals(!driver.findElement(created_user_locator).isDisplayed(), status);
    }


    /* Deletes a booking by name of registered user */
    public void deleteBooking() {
        delete_user = By.xpath("(//*[text() = '" + user_created_name + "']/following::input[@type = 'button'])[1]");
        support.click(delete_user);
    }

    public void verifyRegisteredBooking() {
        Assert.assertEquals(ScenarioContext.tobe_registered_firstname, ScenarioContext.booked_first_name);
    }

    public void verifyRegisteredBookingOnUI() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[text() = '"+ScenarioContext.booked_first_name+"']")).isDisplayed());
    }

    public void deleteBookingFromUI() {
        delete_user = By.xpath("(//*[text() = '" + ScenarioContext.booked_first_name + "']/following::input[@type = 'button'])[1]");
        support.click(delete_user);
    }
}
