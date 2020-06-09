package com.foodes.recipeapp.json.nutrientsModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class RecipeModel  implements Parcelable {
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

    public RecipeModel(String label, String image, String url, String shareAs) {
        this.label = label;
        this.image = image;
        this.url = url;
        this.shareAs = shareAs;
    }

    protected RecipeModel(Parcel in) {
        uri = in.readString();
        label = in.readString();
        image = in.readString();
        source = in.readString();
        url = in.readString();
        shareAs = in.readString();
        yield = in.readDouble();
        dietLabels = in.createStringArrayList();
        healthLabels = in.createStringArrayList();
        cautions = in.createStringArrayList();
        ingredientLines = in.createStringArrayList();
        calories = in.readDouble();
        totalWeight = in.readDouble();
        totalTime = in.readDouble();
        //Parcelable
        totalNutrients = in.readParcelable(TotalNutrientsModel.class.getClassLoader());
        ingredients = in.createTypedArrayList(IngredientModel.CREATOR);
        digest = in.createTypedArrayList(DigestModel.CREATOR);
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };



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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uri);
        dest.writeString(label);
        dest.writeString(image);
        dest.writeString(source);
        dest.writeString(url);
        dest.writeString(shareAs);
        dest.writeDouble(yield);
        dest.writeStringList(dietLabels);
        dest.writeStringList(healthLabels);
        dest.writeStringList(cautions);
        dest.writeStringList(ingredientLines);
        dest.writeDouble(calories);
        dest.writeDouble(totalWeight);
        dest.writeDouble(totalTime);

        //parcelable
        dest.writeParcelable(totalNutrients, flags);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(digest);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeModel that = (RecipeModel) o;
        return Objects.equals(label, that.label) &&
                Objects.equals(image, that.image) &&
                Objects.equals(url, that.url) &&
                Objects.equals(shareAs, that.shareAs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, image, url, shareAs);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
