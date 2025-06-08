package com.medspace;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class CreateUserRestTest {

    @Test
    public void testCreateUserEndpointWithoutFirebaseUid() {
        // Test should expect 401 since no Firebase UID is provided
        // and the endpoint requires Firebase authentication
        String requestBody = """
                {
                    "fullName": "Test User",
                    "email": "test@example.com",
                    "phoneNumber": "1234567890",
                    "pfpPath": "test-path.jpg",
                    "bio": "Test bio",
                    "userType": "LANDLORD"
                }
                """;

        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer testtoken").body(requestBody).when().post("/users")
                .then().statusCode(401); // Expecting UNAUTHORIZED since no valid Firebase UID
    }

    @Test
    public void testCreateUserEndpointWithoutAuthHeader() {
        // Test without Authorization header should also fail
        String requestBody = """
                {
                    "fullName": "Test User",
                    "email": "test@example.com",
                    "phoneNumber": "1234567890",
                    "pfpPath": "test-path.jpg",
                    "bio": "Test bio",
                    "userType": "LANDLORD"
                }
                """;

        given().header("Content-Type", "application/json").body(requestBody).when().post("/users")
                .then().statusCode(401); // Expecting UNAUTHORIZED
    }
}
