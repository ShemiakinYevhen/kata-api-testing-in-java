package com.booking.util;

import com.booking.entities.Booking;
import com.booking.services.AuthService;
import com.booking.services.BookingService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

//Can be also implemented using Spring annotations, but for me, it feels like an overhead in this case
public class ContextHelper {

    private static ContextHelper contextHelper;

    //Gson instance for working with JSON files
    @Getter
    @Setter
    private Gson gson;

    //Services instances for sending requests
    @Getter
    private AuthService auth;
    @Getter
    private BookingService booking;


    //Runtime data
    @Getter
    @Setter
    private Response response;
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private Booking bookingDetails;


    private ContextHelper() {
        gson = new Gson();
        auth = new AuthService();
        booking = new BookingService();
    }

    /**
     * This method initializes helper only once, to use the same instance in different step definitions classes
     *
     * @return an instance of ContextHelper class
     */
    public static synchronized ContextHelper getContextHelperInstance() {
        if (contextHelper == null) {
            contextHelper = new ContextHelper();
        }

        return contextHelper;
    }

    /**
     * This method converts token value stored in class field to a JSON object
     *
     * @return string value containing JSON object with the token
     */
    public String getTokenJsonBody() {
        JsonObject requestBodyObject = new JsonObject();
        requestBodyObject.addProperty("token", token);
        return gson.toJson(requestBodyObject);
    }

    /**
     * This method converts stored Booking class object to JSON object
     *
     * @return string value containing JSON object with booking details data
     */
    public String getBookingDetailsJsonBody() {
        return gson.toJson(bookingDetails);
    }


    /**
     * This method fetches part of response body JSON via provided json path and parses
     *
     * @param jsonPath - string value of path for fetching from response body
     * @return an instance of Booking class with data from the response body
     */
    public Booking getBookingDetailsObjectFromResponse(String jsonPath) {
        return gson.fromJson(response.jsonPath().getString(jsonPath), Booking.class);
    }
}
