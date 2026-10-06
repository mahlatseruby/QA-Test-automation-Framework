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
}