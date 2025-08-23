package com.booking.stepdefinitions;

import com.booking.entities.LoginRequestBody;
import com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;

import static com.booking.services.AuthService.*;
import static com.booking.util.PropertiesReader.getProperty;
import static org.hamcrest.Matchers.*;

public class BaseStepDefinitions {

    protected String token;
    protected Response response;

    @Given("user logged in as an admin")
    public void loginAsAnAdmin() {
        LoginRequestBody requestBody = new LoginRequestBody(getProperty("user.name"), getProperty("user.password"));
        response = postLoginRequest(requestBody);
        response.then()
                .statusCode(200)
                .body("token", not(emptyString()));
        token = response.jsonPath().getString("token");
    }

    @Given("access token is valid")
    public void validateAccessToken() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("token", token);
        response = postValidateRequest(requestBody);
        response.then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @AfterEach
    public void tearDown() {
        logout();
        verifyTokenIsCancelled();
    }

    private void logout() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("token", token);
        response = postLogoutRequest(requestBody);
        response.then()
                .statusCode(200);
    }

    private void verifyTokenIsCancelled() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("token", token);
        response = postValidateRequest(requestBody);
        response.then()
                .statusCode(403)
                .body("error", is("Invalid token"));
    }
}
