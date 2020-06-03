package com.foodes.recipeapp;

import android.view.View;

import androidx.annotation.NonNull;

public class CustomViewHolder extends RecipeViewHolder {
    //constructor that gets the view
    public CustomViewHolder(@NonNull View itemView, @NonNull ItemClickListener listener) {
        super(itemView);
    }

    @Override
    void present(Object data) { }
}
