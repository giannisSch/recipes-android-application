package com.foodes.recipeapp.database.UsersDb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.foodes.recipeapp.database.UsersDb.User;

import java.util.List;
import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;


public class Favorites {

    private String foodName;
    private String foodImage;

    public Favorites(@NonNull String foodName, @NonNull String foodImage) {
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    @NonNull
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(@NonNull String foodName) {
        this.foodName = foodName;
    }

    @NonNull
    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(@NonNull String foodImage) {
        this.foodImage = foodImage;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "foodName='" + foodName + '\'' +
                ", foodImage='" + foodImage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return foodName.equals(favorites.foodName) &&
                foodImage.equals(favorites.foodImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, foodImage);
    }
}
