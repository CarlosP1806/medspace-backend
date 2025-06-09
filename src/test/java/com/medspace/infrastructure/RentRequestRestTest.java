package com.medspace.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
class RentRequestRestTest {

    @Test
    void testGetAllRentRequestsEndpoint() {
        RestAssured.given().when().get("/rent-requests").then().statusCode(200);
    }

    @Test
    void testCreateRentRequestUnauthorized() {
        RestAssured.given().contentType("application/json").body("{}").when().post("/rent-requests")
                .then().statusCode(401);
    }

    @Test
    void testGetMyRequestsUnauthorized() {
        RestAssured.given().when().get("/rent-requests/my-requests").then().statusCode(401);
    }
}
