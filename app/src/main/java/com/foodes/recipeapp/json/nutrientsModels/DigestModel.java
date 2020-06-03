package com.foodes.recipeapp.json.nutrientsModels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.List;

public class DigestModel implements Parcelable {
    String label;
    String fat;
    String schemaOrgTag;
    double total;
    boolean hasRDI;
    double daily;
    String unit;

    @Nullable
    List<SubModel> sub;

    protected DigestModel(Parcel in) {
        label = in.readString();
        fat = in.readString();
        schemaOrgTag = in.readString();
        total = in.readDouble();
        hasRDI = in.readByte() != 0;
        daily = in.readDouble();
        unit = in.readString();
    }

    public static final Creator<DigestModel> CREATOR = new Creator<DigestModel>() {
        @Override
        public DigestModel createFromParcel(Parcel in) {
            return new DigestModel(in);
        }

        @Override
        public DigestModel[] newArray(int size) {
            return new DigestModel[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getSchemaOrgTag() {
        return schemaOrgTag;
    }

    public void setSchemaOrgTag(String schemaOrgTag) {
        this.schemaOrgTag = schemaOrgTag;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isHasRDI() {
        return hasRDI;
    }

    public void setHasRDI(boolean hasRDI) {
        this.hasRDI = hasRDI;
    }

    public double getDaily() {
        return daily;
    }

    public void setDaily(double daily) {
        this.daily = daily;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Nullable
    public List<SubModel> getSub() {
        return sub;
    }

    public void setSub(@Nullable List<SubModel> sub) {
        this.sub = sub;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeString(fat);
        dest.writeString(schemaOrgTag);
        dest.writeDouble(total);
        dest.writeByte((byte) (hasRDI ? 1 : 0));
        dest.writeDouble(daily);
        dest.writeString(unit);
    }
}
