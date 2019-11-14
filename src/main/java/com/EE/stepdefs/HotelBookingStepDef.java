package com.EE.stepdefs;

import com.EE.pageobject.HotelBookingPage;
import com.EE.utility.Support;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HotelBookingStepDef {

    private HotelBookingPage hotelBookingPage;
    private Support support;

    public HotelBookingStepDef() {
        hotelBookingPage = new HotelBookingPage();
        support = new Support();
    }

    @And("^user submits the \"([^\"]*)\" with \"([^\"]*)\"$")
    public void submitsTheWith(String form, String data_type, DataTable data) throws Throwable {

        hotelBookingPage.completeHotelBooking(data);
    }

    @Given("^the user is on the \"([^\"]*)\" page$")
    public void theUserIsOnThePage(String url) throws Throwable {
        url = Support.getConfigDataFileData(url);
        System.out.println(url);
        support.gotoPage(url);
    }


    @When("^user clicks on the the delete button$")
    public void userClicksOnTheTheDeleteButton() throws Throwable {
        hotelBookingPage.deleteBooking();
    }

    @Then("^the booking is \"([^\"]*)\" on the page$")
    public void theUserOnThePage(String status) throws Throwable {
        switch (status) {
            case "Displayed":
                hotelBookingPage.verifyStatus(Boolean.TRUE);
                break;
            case "Not Displayed":
                hotelBookingPage.verifyStatus(Boolean.FALSE);
                break;
            default:
                break;
        }
    }

    @When("^the performs a GET request to the \"([^\"]*)\"$")
    public void thePerformsAGETRequestToThe(String endpoint) throws Throwable {
        endpoint = Support.getConfigDataFileData(endpoint);
        support.getRequest(endpoint);
    }

    @Then("^the user gets a \"([^\"]*)\" successful response$")
    public void theUserGetsASuccessfulResponse(int status) throws Throwable {
        support.verifyResponseCode(status);
    }

    @When("^the user sends a \"([^\"]*)\" request \"([^\"]*)\" to the \"([^\"]*)\"$")
    public void theUserSendsAPOSTRequestToThe(String requestType, String jsonFile, String endpoint) throws Throwable {
        endpoint = Support.getConfigDataFileData(endpoint);


        switch (requestType) {
            case "POST":
                support.postRequest(jsonFile, endpoint);
                break;
            case "DELETE":
                support.deleteRequest(endpoint);
            default:
                break;
        }

    }

    @And("^the booking is registered")
    public void theBookingIsDsplayed() throws Throwable {
        hotelBookingPage.verifyRegisteredBooking();
    }

    @Given("^the registered booking is displayed on the UI$")
    public void theRegisteredBookingIsDisplayedOnTheUI() throws Throwable {
        support.refreshPage();
        hotelBookingPage.verifyRegisteredBookingOnUI();
    }

    @When("^the user deletes the booking from the UI$")
    public void theUserDeletesTheBookingFromTheUI() throws Throwable {
        hotelBookingPage.deleteBookingFromUI();
    }
}
