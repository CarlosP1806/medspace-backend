package com.medspace.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


@QuarkusTest
class NotificationRestTest {

    @Test
    void testGetNotificationsUnauthorized() {
        // Should return 401 if not authenticated
        RestAssured.given().when().get("/notifications").then().statusCode(401);
    }

    @Test
    void testMarkAsReadUnauthorized() {
        // Should return 401 if not authenticated
        RestAssured.given().when().put("/notifications/1/read").then().statusCode(401);
    }
}
