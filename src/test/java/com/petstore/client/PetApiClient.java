package com.petstore.client;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import com.petstore.model.Pet;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetApiClient {

    public ValidatableResponse createPet(Pet pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .post("/pet")
            .then();
    }

    public ValidatableResponse getPetById(int petId) {
        return given()
                .pathParam("petId", petId)
            .when()
                .get("/pet/{petId}")
            .then();
    }

    public ValidatableResponse updatePet(Pet pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
            .when()
                .put("/pet")
            .then();
    }

    public ValidatableResponse deletePet(int petId) {
        return given()
                .pathParam("petId", petId)
            .when()
                .delete("/pet/{petId}")
            .then();
    }

    public ValidatableResponse findByStatus(String status) {
        return given()
                .queryParam("status", status)
            .when()
                .get("/pet/findByStatus")
            .then();
    }

    public ValidatableResponse findByTags(String tag) {
        return given()
                .queryParam("tags", tag)
            .when()
                .get("/pet/findByTags")
            .then();
    }

    public ValidatableResponse updateWithFormData(int petId, String name, String status) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .pathParam("petId", petId)
                .formParam("name", name)
                .formParam("status", status)
            .when()
                .post("/pet/{petId}")
            .then();
    }

    public ValidatableResponse uploadImage(int petId, File file, String metadata) {
        return given()
                .contentType("multipart/form-data")
                .pathParam("petId", petId)
                .multiPart("file", file)
                .formParam("additionalMetadata", metadata)
            .when()
                .post("/pet/{petId}/uploadImage")
            .then();
    }
}
