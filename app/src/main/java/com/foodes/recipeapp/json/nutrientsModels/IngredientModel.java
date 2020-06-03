package com.foodes.recipeapp.json.nutrientsModels;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientModel implements Parcelable {
    String text;
    double weight;

    protected IngredientModel(Parcel in) {
        text = in.readString();
        weight = in.readDouble();
    }

    public static final Creator<IngredientModel> CREATOR = new Creator<IngredientModel>() {
        @Override
        public IngredientModel createFromParcel(Parcel in) {
            return new IngredientModel(in);
        }

        @Override
        public IngredientModel[] newArray(int size) {
            return new IngredientModel[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeDouble(weight);
    }
}
