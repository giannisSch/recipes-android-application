package com.foodes.recipeapp.json.nutrientsModels;

class SubModel {
    String label;
    String fat;
    String schemaOrgTag;
    double total;
    boolean hasRDI;
    double daily;
    String unit;

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
}
