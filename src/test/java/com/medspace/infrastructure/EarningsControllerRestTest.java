package com.medspace.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
class EarningsControllerRestTest {
    @Test
    void testGetWeeklyEarningsUnauthorized() {
        // Should return 401 if not authenticated as landlord
        RestAssured.given().when().get("/api/landlords/earnings/weekly").then().statusCode(401);
    }
}
