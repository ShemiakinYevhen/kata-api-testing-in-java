package com.booking.stepdefinitions;

import com.booking.entities.Booking;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.booking.services.BookingService.postCreateBookingRequest;
import static org.junit.Assert.assertEquals;

public class BookingStepDefinitions extends BaseStepDefinitions {

    private Booking bookingDetails;

    @Given("user enters booking details")
    public void saveBookingDetails(DataTable bookingDetails) {
        this.bookingDetails = new Booking(bookingDetails.asMap());
    }

    @When("user sends POST request to create a booking")
    public void sendPOSTRequestToCreateBooking() {
        response = postCreateBookingRequest(bookingDetails);
    }

    @And("create booking response should match the {string} json scheme")
    public void createBookingResponseShouldMatchTheJsonScheme(String schemeFileName) {
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchemaInClasspath("schemes/" + schemeFileName + ".json"));
    }

    @And("create booking response data should match data given by user")
    public void createBookingResponseDataShouldMatchDataGivenByUser() {
        Booking actualBookingDetails = gson.fromJson(response.jsonPath().getString("booking"), Booking.class);
        assertEquals("Booking details data is not as expected!", bookingDetails, actualBookingDetails);
    }

    @And("create booking response should contain the error message {string}")
    public void createBookingResponseShouldContainTheErrorMessage(String expectedErrors) {
        List<String> expectedErrorMessages = Arrays.asList(expectedErrors.split("/"));
        List<String> actualErrorMessages = response.jsonPath().getList("errors");

        Collections.sort(expectedErrorMessages);
        Collections.sort(actualErrorMessages);

        assertEquals("Error messages are not as expected!", expectedErrorMessages, actualErrorMessages);
    }
}
