package com.booking.services;

import com.booking.entities.Booking;
import io.restassured.response.Response;

import static com.booking.enums.BookingRequests.CREATE_BOOKING;
import static io.restassured.RestAssured.given;

public class BookingService extends BaseService {

    public static Response postCreateBookingRequest(Booking bookingDetails) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(bookingDetails))
                .post(CREATE_BOOKING.getPath());
    }
}
