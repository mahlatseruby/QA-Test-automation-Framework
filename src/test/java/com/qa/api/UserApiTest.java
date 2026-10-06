package com.qa.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserApiTest {

   @BeforeAll
   public static void setup() {
       RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
   }

   @Test
   @DisplayName("GET /users - Should return status code 200")
   void testGetAllUsers_StatusCode() {
       given()
           .when()
               .get("/users")
           .then()
               .statusCode(200);
   }

   @Test
   @DisplayName("GET /users/1 - Should return correct user")
   void testGetSingleUser() {
       given()
           .when()
               .get("/users/1")
           .then()
               .statusCode(200)
               .body("id", equalTo(1))
               .body("name", equalTo("Leanne Graham"))
               .body("email", equalTo("Sincere@april.biz"));
   }

   @Test
   @DisplayName("GET /users - Should return 10 users")
   void testGetAllUsers_Count() {
       Response response = given()
           .when()
               .get("/users")
           .then()
               .statusCode(200)
               .extract().response();

       assertEquals(10, response.jsonPath().getList("$").size());
   }
   
    @Test
    @DisplayName("POST /posts - Should create a new post")
    void testCreatePost() {
        String requestBody = """
            {
                "title": "QA Automation Test",
                "body": "This is a test post created by RestAssured",
                "userId": 1
            }
            """;

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("QA Automation Test"))
            .body("body", equalTo("This is a test post created by RestAssured"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT /posts/1 - Should update an existing post")
    void testUpdatePost() {
        String requestBody = """
            {
                "id": 1,
                "title": "Updated Title by QA",
                "body": "Updated body content",
                "userId": 1
            }
            """;

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title by QA"));
    }

    @Test
    @DisplayName("DELETE /posts/1 - Should delete a post")
    void testDeletePost() {
        given()
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("GET /users/999 - Should return 404 for non-existing user")
    void testGetNonExistingUser() {
        given()
        .when()
            .get("/users/999")
        .then()
            .statusCode(404);
    }
}