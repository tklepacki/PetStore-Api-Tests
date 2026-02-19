package com.petstore.factory;

import com.petstore.model.Pet;
import com.petstore.model.Tag;

public class PetFactory {

    private static final int DEFAULT_ID = 12345;
    private static final String DEFAULT_NAME = "doggie";
    private static final String DEFAULT_STATUS = "available";

    public static Pet defaultPet() {
        return Pet.builder()
            .id(DEFAULT_ID)
            .name(DEFAULT_NAME)
            .category(1, "Dogs")
            .status(DEFAULT_STATUS)
            .build();
    }

    public static Pet withStatus(String status) {
        return Pet.builder()
            .id(DEFAULT_ID)
            .name(DEFAULT_NAME)
            .category(1, "Dogs")
            .status(status)
            .build();
    }

    public static Pet withNameAndStatus(String name, String status) {
        return Pet.builder()
            .id(DEFAULT_ID)
            .name(name)
            .category(1, "Dogs")
            .status(status)
            .build();
    }

    public static Pet withTags(String... tagNames) {
        Pet.Builder builder = Pet.builder()
            .id(DEFAULT_ID)
            .name(DEFAULT_NAME)
            .category(1, "Dogs")
            .status(DEFAULT_STATUS);

        java.util.List<Tag> tags = new java.util.ArrayList<>();
        for (int i = 0; i < tagNames.length; i++) {
            tags.add(new Tag(i + 1, tagNames[i]));
        }
        builder.tags(tags);

        return builder.build();
    }
}
