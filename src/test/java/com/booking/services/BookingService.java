package com.booking.services;

import io.restassured.response.Response;

import static com.booking.enums.BookingRequests.*;
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

    public Response updateBookingByIdRequest(String token, String bookingId, String requestBody) {
        return given()
                .spec(requestSpecification)
                .header("Cookie", token)
                .body(requestBody)
                .put(UPDATE_BOOKING_BY_ID.getPath().formatted(bookingId));
    }

    public Response deleteBookingByIdRequest(String token, String bookingId) {
        return given()
                .spec(requestSpecification)
                .header("Cookie", token)
                .delete(DELETE_BOOKING_BY_ID.getPath().formatted(bookingId));
    }
}
