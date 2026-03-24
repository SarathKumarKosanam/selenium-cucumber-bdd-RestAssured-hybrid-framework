package com.sdet.api;

import com.sdet.utils.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsersApiTest {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = ConfigReader.get("api.base.url");
    }

    @Test(description = "GET /users should return user list")
    public void testGetUsers() {
        given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data", notNullValue())
                .body("data.size()", greaterThan(0));
    }

    @Test(description = "GET /users/{id} should return single user")
    public void testGetSingleUser() {
        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", notNullValue());
    }

    @Test(description = "POST /users should create user")
    public void testCreateUser() {
        String body = """
            {
                "name": "Sarath Kumar",
                "job": "SDET"
            }
            """;
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Sarath Kumar"))
                .body("id", notNullValue());
    }

    @Test(description = "PUT /users/{id} should update user")
    public void testUpdateUser() {
        String body = """
            {
                "name": "Sarath Kumar",
                "job": "Senior SDET"
            }
            """;
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo("Senior SDET"));
    }

    @Test(description = "DELETE /users/{id} should return 204")
    public void testDeleteUser() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test(description = "GET /users/9999 should return 404")
    public void testUserNotFound() {
        given()
                .when()
                .get("/users/9999")
                .then()
                .statusCode(404);
    }
}