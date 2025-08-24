package com.booking.services;

import io.restassured.response.Response;

import static com.booking.enums.AuthRequests.*;
import static io.restassured.RestAssured.given;

public class AuthService extends BaseService {

    public Response postLoginRequest(String requestBody) {
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(LOGIN.getPath());
    }

    public Response postValidateRequest(String requestBody) {
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(VALIDATE.getPath());
    }

    public Response postLogoutRequest(String requestBody) {
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(LOGOUT.getPath());
    }
}
