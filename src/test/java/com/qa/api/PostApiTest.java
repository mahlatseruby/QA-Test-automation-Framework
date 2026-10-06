
package com.qa.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostApiTest extends BaseApiTest {

    @Test
    @DisplayName("GET /posts - Should return 100 posts")
    void testGetAllPosts() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(100));
    }

    @Test
    @DisplayName("GET /posts/1 - Should return correct post")
    void testGetSinglePost() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", equalTo(1));
    }
}