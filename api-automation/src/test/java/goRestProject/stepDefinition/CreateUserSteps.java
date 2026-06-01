package goRestProject.stepDefinition;

import goRestProject.api.UserApi;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import goRestProject.utils.RequestSpecFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CreateUserSteps {

    private final UserApi userApi = new UserApi();
    private Response response;
    private Map<String, Object> payload;
    private final Properties config = RequestSpecFactory.getConfig();

    @Given("I prepare a valid user payload")
    public void i_prepare_a_valid_user_payload() {
        // dynamic email to avoid duplicates
        String email = "rifka" + System.currentTimeMillis() + "@gmail.com";

        payload = new HashMap<>();
        payload.put("email", email);
        payload.put("name", "rifkaM");
        payload.put("gender", "female");
        payload.put("status", "active");
    }

    @When("I call create user API")
    public void i_call_create_user_api() {
        response = userApi.createUser(payload);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus.intValue(),
            "unexpected status code. Response body: " + response.getBody().asString());
    }

    @Then("the response time should be less than configured timeout")
    public void the_response_time_should_be_less_than_configured_timeout() {
        long timeout = Long.parseLong(config.getProperty("default.timeout.ms", "5000"));
        Assert.assertTrue(response.time() < timeout,
                "response took longer than expected: " + response.time() + "ms (expected < " + timeout + "ms)");
    }

    @Then("the response body should contain name {string}")
    public void the_response_body_should_contain_name(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName, "Name mismatch in response");
    }
}