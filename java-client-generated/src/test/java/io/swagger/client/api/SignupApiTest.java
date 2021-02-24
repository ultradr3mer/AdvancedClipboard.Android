package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.SignupPostData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SignupApi
 */
public class SignupApiTest {

    private SignupApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(SignupApi.class);
    }


    /**
     * 
     *
     * 
     */
    @Test
    public void signupPostTest() {
        SignupPostData body = null;
        // Void response = api.signupPost(body);

        // TODO: test validations
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void signupTestAuthorizeGetTest() {
        String username = null;
        String password = null;
        // Void response = api.signupTestAuthorizeGet(username, password);

        // TODO: test validations
    }
}
