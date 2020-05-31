package com.foodes.recipeapp;

import java.util.List;

public class RecipeModel {
    String uri;
    String label;
    String image;
    String source;
    String url;
    String shareAs;
    double yield;
    List<String> dietLabels;
    List<String> healthLabels;
    List<String> cautions;
    List<String> ingredientLines;
    List<IngredientModel> ingredients;
    double calories;
    double totalWeight;
    double totalTime;
    TotalNutrientsModel totalNutrients;
    TotalDailyModel totalDaily;
    List<DigestModel> digest;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShareAs() {
        return shareAs;
    }

    public void setShareAs(String shareAs) {
        this.shareAs = shareAs;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public List<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public TotalNutrientsModel getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(TotalNutrientsModel totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public TotalDailyModel getTotalDaily() {
        return totalDaily;
    }

    public void setTotalDaily(TotalDailyModel totalDaily) {
        this.totalDaily = totalDaily;
    }

    public List<DigestModel> getDigest() {
        return digest;
    }

    public void setDigest(List<DigestModel> digest) {
        this.digest = digest;
    }
}
