package com.foodes.recipeapp.json.nutrientsModels;

import android.os.Parcel;
import android.os.Parcelable;

public class CaModel implements Parcelable {
    String label;
    double quantity;
    String unit;

    protected CaModel(Parcel in) {
        label = in.readString();
        quantity = in.readDouble();
        unit = in.readString();
    }

    public static final Creator<CaModel> CREATOR = new Creator<CaModel>() {
        @Override
        public CaModel createFromParcel(Parcel in) {
            return new CaModel(in);
        }

        @Override
        public CaModel[] newArray(int size) {
            return new CaModel[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeDouble(quantity);
        dest.writeString(unit);
    }
}
