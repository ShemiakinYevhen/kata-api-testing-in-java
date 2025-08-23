package com.booking.stepdefinitions;

import com.booking.entities.Booking;
import com.booking.entities.LoginRequestBody;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.booking.services.AuthService.*;
import static com.booking.services.AuthService.postValidateRequest;
import static com.booking.services.BookingService.getBookingByIdRequest;
import static com.booking.services.BookingService.postCreateBookingRequest;
import static com.booking.util.PropertiesReader.getProperty;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class BookingStepDefinitions {

    private Booking bookingDetails;
    protected String token;
    protected Response response;
    protected Gson gson;

    @Given("user logged in as an admin")
    public void loginAsAnAdmin() {
        LoginRequestBody requestBody = new LoginRequestBody(getProperty("user.name"), getProperty("user.password"));
        response = postLoginRequest(requestBody);
        response.then()
                .statusCode(200)
                .body("token", not(emptyString()));
        token = response.jsonPath().getString("token");
    }

    @Given("access token is valid")
    public void validateAccessToken() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("token", token);
        response = postValidateRequest(requestBody);
        response.then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @AfterEach
    public void tearDown() {
        if (token != null) {
            logout();
            verifyTokenIsCancelled();
        }
    }

    private void logout() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("token", token);
        response = postLogoutRequest(requestBody);
        response.then()
                .statusCode(200);
    }

    private void verifyTokenIsCancelled() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("token", token);
        response = postValidateRequest(requestBody);
        response.then()
                .statusCode(403)
                .body("error", is("Invalid token"));
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Given("user enters booking details")
    public void saveBookingDetails(DataTable bookingDetails) {
        this.bookingDetails = new Booking(bookingDetails.asMaps().get(0));
    }

    @When("user sends POST request to create a booking")
    public void sendPOSTRequestToCreateBooking() {
        response = postCreateBookingRequest(bookingDetails);
    }

    @And("booking response should match the {string} json scheme")
    public void createBookingResponseShouldMatchTheJsonScheme(String schemeFileName) {
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchemaInClasspath("schemes/" + schemeFileName + ".json"));
    }

    @And("booking response data should match data given by user")
    public void createBookingResponseDataShouldMatchDataGivenByUser() {
        Booking actualBookingDetails = gson.fromJson(response.jsonPath().getString("booking"), Booking.class);
        Assertions.assertEquals(bookingDetails, actualBookingDetails, "Booking details data is not as expected!");
    }

    @And("booking response should contain the error message {string}")
    public void createBookingResponseShouldContainTheErrorMessage(String expectedErrors) {
        List<String> expectedErrorMessages = Arrays.asList(expectedErrors.split("/"));
        List<String> actualErrorMessages = response.jsonPath().getList("errors");

        Collections.sort(expectedErrorMessages);
        Collections.sort(actualErrorMessages);

        Assertions.assertEquals(expectedErrorMessages, actualErrorMessages, "Error messages are not as expected!");
    }

    @When("user sends GET request to get a booking by saved ID")
    public void userSendsGETRequestToGetABookingBySavedID() {
        String bookingId = String.valueOf(bookingDetails.getBookingId());
        response = getBookingByIdRequest(token, bookingId);
    }

    @When("user sends GET request to get a booking by {string} ID")
    public void userSendsGETRequestToGetABookingByID(String invalidBookingID) {
        response = getBookingByIdRequest(token, invalidBookingID);
    }
}
