package com.booking.stepdefinitions.booking;

import com.booking.util.ContextHelper;
import io.cucumber.java.en.When;

import static com.booking.util.ContextHelper.getContextHelperInstance;

public class CreateBookingStepDefinitions {

    private final ContextHelper contextHelper = getContextHelperInstance();

    @When("user sends POST request to create a booking")
    public void sendPOSTRequestToCreateBooking() {
        contextHelper.setResponse(contextHelper.getBooking().postCreateBookingRequest(contextHelper.getBookingDetailsJsonBody()));
    }
}
