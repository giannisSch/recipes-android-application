package com.foodes.recipeapp.recyclerviews.recipedetails;

import android.view.View;

import androidx.annotation.NonNull;

public class RecipeDetailsEmptyViewHolder extends RecipeDetailsAbstractViewHolder<Object> {
    public RecipeDetailsEmptyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    void present(Object data) {

    }
}
