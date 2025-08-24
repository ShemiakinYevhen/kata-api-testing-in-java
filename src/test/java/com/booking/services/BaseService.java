package com.booking.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.booking.util.PropertiesReader.getProperty;

public class BaseService {
    protected static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(getProperty("application.url"))
            .setAccept("application/json")
            .setContentType("application/json").build();
}
