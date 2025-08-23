package com.booking.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthRequests {

    LOGIN("/auth/login"),
    LOGOUT("/auth/logout"),
    VALIDATE("/auth/validate");

    private final String path;

    public String getPath() {
        return this.path;
    }
}
