package com.booking.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookingRequests {

    CREATE_BOOKING("/booking"),
    //Separated booking by ID requests enum values to improve naming and take into account future possible difference in paths, can be reverted
    GET_BOOKING_BY_ID("/booking/%s"),
    UPDATE_BOOKING_BY_ID("/booking/%s"),
    DELETE_BOOKING_BY_ID("/booking/%s");

    private final String path;

    public String getPath() {
        return this.path;
    }
}
