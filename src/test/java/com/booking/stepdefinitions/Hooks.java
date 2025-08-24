package com.booking.stepdefinitions;

import com.booking.util.ContextHelper;
import org.junit.jupiter.api.AfterEach;

import static com.booking.util.ContextHelper.getContextHelperInstance;
import static org.hamcrest.Matchers.is;

public class Hooks {

    private ContextHelper contextHelper = getContextHelperInstance();

    @AfterEach
    public void tearDown() {
        if (contextHelper.getToken() != null) {
            logout();
            verifyTokenIsCancelled();
        }
    }

    private void logout() {
        contextHelper.setResponse(contextHelper.getAuth().postLogoutRequest(contextHelper.getTokenJsonBody()));
        contextHelper.getResponse().then().statusCode(200);
    }

    private void verifyTokenIsCancelled() {
        contextHelper.setResponse(contextHelper.getAuth().postValidateRequest(contextHelper.getTokenJsonBody()));
        contextHelper.getResponse().then()
                .statusCode(403)
                .body("error", is("Invalid token"));
    }
}
