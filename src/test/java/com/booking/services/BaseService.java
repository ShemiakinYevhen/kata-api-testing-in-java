package com.booking.services;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.booking.util.PropertiesReader.getProperty;

public class BaseService {
    protected static final Gson gson = new Gson();
    protected static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(getProperty("application.url"))
            .setAccept("application/json")
            .setContentType("application/json").build();
}
