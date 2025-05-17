package org.acme.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Class for integration tests between the controller, service and Redis cache
 */
@QuarkusTest
class LabseqControllerIntegrationTest {

    /**
     * Test l(0) = 0
     */
    @Test
    void shouldReturnZeroWhenGivenZero() {
        given()
                .when().get("/labseq/0")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    /**
     * Test l(1) = 1
     */
    @Test
    void shouldReturnOneWhenGivenOne() {
        given()
                .when().get("/labseq/1")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }


    /**
     * Test l(2) = 0
     */
    @Test
    void shouldReturnZeroWhenGivenTwo() {
        given()
                .when().get("/labseq/2")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    /**
     * Test l(3) = 1
     */
    @Test
    void shouldReturnOneWhenGivenThree() {
        given()
                .when().get("/labseq/3")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    /**
     * Test l(10) = 3
     */
    @Test
    void shouldReturnThreeWhenGivenTen() {
        given()
                .when().get("/labseq/10")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    /**
     * Test l(-1), response should return a BAD REQUEST
     */
    @Test
    void shouldFailWhenGivenNegativeValue() {
        given()
                .when().get("/labseq/-1")
                .then()
                .statusCode(400)
                .body(is("Input 'n' must be non-negative"));
    }

}

