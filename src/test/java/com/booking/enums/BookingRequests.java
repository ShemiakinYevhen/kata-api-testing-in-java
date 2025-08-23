package com.booking.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookingRequests {

    CREATE_BOOKING("/booking"),
    GET_BOOKING_BY_ID("/booking/%s");

    private final String path;

    public String getPath() {
        return this.path;
    }
}
