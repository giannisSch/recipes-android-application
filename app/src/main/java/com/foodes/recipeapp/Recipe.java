package com.foodes.recipeapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Recipe {
    private String image;
    private String description;
    private ArrayList<String> ingredients;

    public Recipe(String image, String description, ArrayList<String> ingredients) {
        this.image = image;
        this.description = description;
        this.ingredients = ingredients;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
