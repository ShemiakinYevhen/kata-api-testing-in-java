package com.booking.services;

import com.booking.entities.LoginRequestBody;
import com.google.gson.JsonObject;
import io.restassured.response.Response;

import static com.booking.enums.AuthRequests.*;
import static io.restassured.RestAssured.given;

public class AuthService extends BaseService {

    public static Response postLoginRequest(LoginRequestBody requestBody) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(requestBody))
                .post(LOGIN.getPath());
    }

    public static Response postValidateRequest(JsonObject requestBody) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(requestBody))
                .post(VALIDATE.getPath());
    }

    public static Response postLogoutRequest(JsonObject requestBody) {
        return given()
                .spec(requestSpecification)
                .body(gson.toJson(requestBody))
                .post(LOGOUT.getPath());
    }
}
