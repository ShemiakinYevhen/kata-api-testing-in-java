package com.booking.stepdefinitions.booking;

import com.booking.util.ContextHelper;
import io.cucumber.java.en.When;

import static com.booking.util.ContextHelper.getContextHelperInstance;

public class DeleteBookingStepDefinitions {

    private final ContextHelper contextHelper = getContextHelperInstance();

    @When("user sends DELETE request to delete a booking by ID from saved data")
    public void userSendsDELETERequestToDeleteABooking() {
        contextHelper.setResponse(contextHelper.getBooking().deleteBookingByIdRequest(
                contextHelper.getToken(),
                String.valueOf(contextHelper.getBookingDetails().getBookingId())
        ));
    }

    @When("user sends DELETE request to delete a booking by {string} ID")
    public void userSendsDELETERequestToDeleteABookingByID(String invalidBookingID) {
        contextHelper.setResponse(contextHelper.getBooking().deleteBookingByIdRequest(
                contextHelper.getToken(),
                invalidBookingID
        ));
    }
}
