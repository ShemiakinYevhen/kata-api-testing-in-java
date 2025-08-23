package com.booking.services;

import com.booking.entities.Booking;
import io.restassured.response.Response;

import static com.booking.enums.BookingRequests.CREATE_BOOKING;
import static com.booking.enums.BookingRequests.GET_BOOKING_BY_ID;
import static io.restassured.RestAssured.given;

public class BookingService extends BaseService {

    public static Response postCreateBookingRequest(Booking bookingDetails) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(bookingDetails))
                .post(CREATE_BOOKING.getPath());
    }

    public static Response getBookingByIdRequest(String token, String bookingId) {
        return given()
                .spec(requestSpecification)
                .header("Cookie", token)
                .get(GET_BOOKING_BY_ID.getPath().formatted(bookingId));
    }
}
