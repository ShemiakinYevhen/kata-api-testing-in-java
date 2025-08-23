package com.booking.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookingRequests {

    CREATE_BOOKING("/booking");

    private final String path;

    public String getPath() {
        return this.path;
    }
}
