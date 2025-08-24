package com.booking.stepdefinitions.booking;

import com.booking.util.ContextHelper;
import io.cucumber.java.en.When;

import static com.booking.util.ContextHelper.getContextHelperInstance;

public class GetBookingStepDefinitions {

    private final ContextHelper contextHelper = getContextHelperInstance();

    @When("user sends GET request to get a booking by saved ID")
    public void userSendsGETRequestToGetABookingBySavedID() {
        String bookingId = String.valueOf(contextHelper.getBookingDetails().getBookingId());
        contextHelper.setResponse(contextHelper.getBooking().getBookingByIdRequest(contextHelper.getToken(), bookingId));
    }

    @When("user sends GET request to get a booking by {string} ID")
    public void userSendsGETRequestToGetABookingByID(String invalidBookingID) {
        contextHelper.setResponse(contextHelper.getBooking().getBookingByIdRequest(contextHelper.getToken(), invalidBookingID));
    }
}
