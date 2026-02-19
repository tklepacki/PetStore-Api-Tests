package com.petstore.model;

import java.util.List;

public class Pet {
    private int id;
    private String name;
    private String status;
    private Category category;
    private List<Tag> tags;

    public Pet() {}

    private Pet(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.status = builder.status;
        this.category = builder.category;
        this.tags = builder.tags;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public static class Builder {
        private int id;
        private String name;
        private String status;
        private Category category;
        private List<Tag> tags;

        public Builder id(int id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder category(int id, String name) { this.category = new Category(id, name); return this; }
        public Builder tags(List<Tag> tags) { this.tags = tags; return this; }

        public Pet build() {
            return new Pet(this);
        }
    }
}
