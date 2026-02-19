package com.petstore.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.petstore.client.PetApiClient;
import com.petstore.factory.PetFactory;
import com.petstore.model.Pet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PetApiTests {

    private final PetApiClient petApi = new PetApiClient();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testAddPet() {
        Pet pet = PetFactory.defaultPet();

        petApi.createPet(pet)
            .statusCode(200)
            .body("id", equalTo(12345))
            .body("name", equalTo("doggie"));
    }

    @Test
    public void testGetPetById() {
        petApi.createPet(PetFactory.defaultPet()).statusCode(200);

        petApi.getPetById(12345)
            .statusCode(200)
            .body("id", equalTo(12345))
            .body("name", equalTo("doggie"));
    }

    @Test
    public void testUpdatePet() {
        petApi.createPet(PetFactory.defaultPet()).statusCode(200);

        Pet updatedPet = PetFactory.withNameAndStatus("doggie-updated", "sold");

        petApi.updatePet(updatedPet)
            .statusCode(200)
            .body("name", equalTo("doggie-updated"))
            .body("status", equalTo("sold"));
    }

    @Test
    public void testDeletePet() {
        petApi.createPet(PetFactory.defaultPet()).statusCode(200);

        petApi.deletePet(12345).statusCode(200);

        petApi.getPetById(12345).statusCode(404);
    }

    @Test
    public void testFindPetsByStatus() {
        petApi.createPet(PetFactory.defaultPet()).statusCode(200);

        petApi.findByStatus("available")
            .statusCode(200)
            .body("size()", notNullValue());
    }

    @Test
    public void testFindPetsByTags() {
        petApi.createPet(PetFactory.withTags("cute")).statusCode(200);

        petApi.findByTags("cute").statusCode(200);
    }

    @Test
    public void testUpdatePetWithFormData() {
        petApi.createPet(PetFactory.defaultPet()).statusCode(200);

        petApi.updateWithFormData(12345, "doggie-form-updated", "sold")
            .statusCode(200);

        petApi.getPetById(12345)
            .statusCode(200)
            .body("name", equalTo("doggie-form-updated"))
            .body("status", equalTo("sold"));
    }

    @Test
    public void testUploadPetImage() throws IOException {
        petApi.createPet(PetFactory.defaultPet()).statusCode(200);

        File tempFile = File.createTempFile("pet-image", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("fake image content");
        }

        petApi.uploadImage(12345, tempFile, "test image")
            .statusCode(200);
    }
}
