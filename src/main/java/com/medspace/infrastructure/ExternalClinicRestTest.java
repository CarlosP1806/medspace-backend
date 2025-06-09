package com.medspace.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class ExternalClinicRestTest {


    @Test
    void testGetDashboardData() {
        given().when().get("/api/external-clinics/dashboard").then().statusCode(200).body("success",
                is(true));
    }
}
