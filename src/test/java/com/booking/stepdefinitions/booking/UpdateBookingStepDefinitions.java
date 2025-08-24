package com.booking.stepdefinitions.booking;

import com.booking.util.ContextHelper;
import io.cucumber.java.en.When;

import static com.booking.util.ContextHelper.getContextHelperInstance;

public class UpdateBookingStepDefinitions {

    private final ContextHelper contextHelper = getContextHelperInstance();

    @When("user sends PUT request to update a booking using saved data")
    public void userSendsPUTRequestToUpdateABooking() {
        contextHelper.setResponse(contextHelper.getBooking().updateBookingByIdRequest(
                contextHelper.getToken(),
                String.valueOf(contextHelper.getBookingDetails().getBookingId()),
                contextHelper.getBookingDetailsJsonBody()
        ));
    }

    @When("user sends PUT request to update a booking by {string} ID")
    public void userSendsPUTRequestToUpdateABookingByID(String invalidBookingID) {
        contextHelper.setResponse(contextHelper.getBooking().updateBookingByIdRequest(
                contextHelper.getToken(),
                invalidBookingID,
                contextHelper.getBookingDetailsJsonBody()
        ));
    }
}
