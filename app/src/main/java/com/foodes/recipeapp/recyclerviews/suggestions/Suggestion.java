package com.foodes.recipeapp.recyclerviews.suggestions;

public class Suggestion {
    private String parameter;
    private int imageId;

    public Suggestion(String parameter, int imageId) {
        this.parameter = parameter;
        this.imageId = imageId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
