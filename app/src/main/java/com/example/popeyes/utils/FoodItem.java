package com.example.popeyes.utils;

import org.json.JSONObject;

public class FoodItem {

    private int id;
    private String title;
    private String image;

    FoodItem(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.image = builder.image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public static class Builder {
        private int id;
        private String title;
        private String image;

        public Builder setFromJSONObject(JSONObject object) {
            this.id = object.optInt("cat_id");
            this.title = object.optString("cat_name");
            this.image = object.optString("cat_image");
            return this;
        }

        public FoodItem build() {
            return new FoodItem(this);
        }
    }
}
