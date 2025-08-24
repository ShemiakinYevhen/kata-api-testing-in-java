package com.booking.services;

import io.restassured.response.Response;

import static com.booking.enums.BookingRequests.CREATE_BOOKING;
import static com.booking.enums.BookingRequests.GET_BOOKING_BY_ID;
import static io.restassured.RestAssured.given;

public class BookingService extends BaseService {

    public Response postCreateBookingRequest(String requestBody) {
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(CREATE_BOOKING.getPath());
    }

    public Response getBookingByIdRequest(String token, String bookingId) {
        return given()
                .spec(requestSpecification)
                .header("Cookie", token)
                .get(GET_BOOKING_BY_ID.getPath().formatted(bookingId));
    }
}
