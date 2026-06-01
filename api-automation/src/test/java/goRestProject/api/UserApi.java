package goRestProject.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import goRestProject.utils.RequestSpecFactory;

import static io.restassured.RestAssured.given;

import java.util.Map;

public class UserApi {

    private final RequestSpecification baseSpec;

    public UserApi() {
        this.baseSpec = RequestSpecFactory.getRequestSpec();
    }

    /**
     * Generic method to create user with a JSON string body.
     */
    public Response createUser(String jsonBody) {
        return given()
                .spec(baseSpec)
                .body(jsonBody)
            .when()
                .post("/public/v2/users")
            .andReturn();
    }

    /**
     * Generic method to create user using a Map payload (converted to JSON by RestAssured).
     */
    public Response createUser(Map<String, Object> payload) {
        return given()
                .spec(baseSpec)
                .body(payload)
            .when()
                .post("/public/v2/users")
            .andReturn();
    }

    /**
     * Get user by id
     */
    public Response getUserById(String id) {
        return given()
                .spec(baseSpec)
            .when()
                .get("/public/v2/users/{id}", id)
            .andReturn();
    }

    // add other generic methods: updateUser, deleteUser, searchUsers, etc.
}