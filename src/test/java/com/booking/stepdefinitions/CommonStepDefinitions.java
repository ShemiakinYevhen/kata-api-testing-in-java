package com.booking.stepdefinitions;

import com.booking.entities.Booking;
import com.booking.util.ContextHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.booking.util.ContextHelper.getContextHelperInstance;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonStepDefinitions {

    private final ContextHelper contextHelper = getContextHelperInstance();

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int expectedStatusCode) {
        contextHelper.getResponse().then().statusCode(expectedStatusCode);
    }

    @Given("user enters booking details")
    public void saveBookingDetails(DataTable bookingDetails) {
        contextHelper.setBookingDetails(new Booking(bookingDetails.asMaps().get(0)));
    }

    @And("booking response should match the {string} json scheme")
    public void bookingResponseShouldMatchTheJsonScheme(String schemeFileName) {
        contextHelper.getResponse()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemes/" + schemeFileName + ".json"));
    }

    @And("booking response data should match data given by user")
    public void bookingResponseDataShouldMatchDataGivenByUser() {
        Booking actualBookingDetails = contextHelper.getBookingDetailsObjectFromResponse("booking");
        assertEquals(contextHelper.getBookingDetails(), actualBookingDetails, "Booking details data is not as expected!");
    }

    @And("booking response should contain the error message {string}")
    public void bookingResponseShouldContainTheErrorMessage(String expectedErrors) {
        List<String> expectedErrorMessages = Arrays.asList(expectedErrors.split("/"));
        List<String> actualErrorMessages = contextHelper.getResponse().jsonPath().getList("errors");

        Collections.sort(expectedErrorMessages);
        Collections.sort(actualErrorMessages);

        assertEquals(expectedErrorMessages, actualErrorMessages, "Error messages are not as expected!");
    }

    @And("response should be empty json object")
    public void responseShouldBeEmptyJsonObject() {
        contextHelper.getResponse().then().body(equalTo("{}"));
    }
}
