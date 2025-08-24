package com.booking.stepdefinitions;

import com.booking.entities.LoginRequestBody;
import com.booking.util.ContextHelper;
import io.cucumber.java.en.Given;

import static com.booking.util.ContextHelper.getContextHelperInstance;
import static com.booking.util.PropertiesReader.getProperty;
import static org.hamcrest.Matchers.*;

public class AuthStepDefinitions {

    private ContextHelper contextHelper = getContextHelperInstance();

    @Given("user logged in as an admin")
    public void loginAsAnAdmin() {
        LoginRequestBody userCredentials = new LoginRequestBody(getProperty("user.name"), getProperty("user.password"));
        String requestBody = contextHelper.getGson().toJson(userCredentials);
        contextHelper.setResponse(contextHelper.getAuth().postLoginRequest(requestBody));
        contextHelper.getResponse().then()
                .statusCode(200)
                .body("token", not(emptyString()));
        contextHelper.setToken(contextHelper.getResponse().jsonPath().getString("token"));
    }

    @Given("access token is valid")
    public void validateAccessToken() {
        contextHelper.setResponse(contextHelper.getAuth().postValidateRequest(contextHelper.getTokenJsonBody()));
        contextHelper.getResponse().then()
                .statusCode(200)
                .body("valid", is(true));
    }
}
