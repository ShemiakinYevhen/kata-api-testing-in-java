package com.booking.services;

import com.booking.model.LoginRequestBody;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static com.booking.util.PropertiesReader.getProperty;

public class AuthService {

    private static String baseUri = getProperty("application.url") + "/auth";
    private static Gson gson = new Gson();

    public static Response postLoginRequest(LoginRequestBody requestBody) {
        return given()
                .contentType("application/json")
                .baseUri(baseUri)
                .body(gson.toJson(requestBody))
                .post("/login");
    }

    public static Response postValidateRequest(JsonObject requestBody) {
        return given()
                .contentType("application/json")
                .baseUri(baseUri)
                .body(gson.toJson(requestBody))
                .post("/validate");
    }

    public static Response postLogoutRequest(JsonObject requestBody) {
        return given()
                .contentType("application/json")
                .baseUri(baseUri)
                .body(gson.toJson(requestBody))
                .post("/logout");
    }
}
