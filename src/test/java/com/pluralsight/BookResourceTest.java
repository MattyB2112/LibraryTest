package com.pluralsight;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BookResourceTest {
    @Test
    void testFindByIdEndpoint() {

        given()
          .when().get("/api/books/1001")
          .then()
             .statusCode(200)
                .body("title", is("Advanced Java EE Development for RAD Guidebook"))
                .body("id", is(1001))
                .body("size()", is(7));
    }
    @Test
    void testAddBookEndpoint() {

        given()
                .body("{\"title\": \"This is a test book\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/api/books")
                .then()
                .statusCode(201);

        given()
                .when().get("/api/books/1")
                .then()
                .statusCode(200)
                .body("title", is("This is a test book"))
                .body("id", is(1));
    }

    @Test
    void testDeleteBookEndpoint() {

        given()
                .when().delete("/api/books/1001")
                .then()
                .statusCode(204);

    }

}