package com.foodes.recipeapp.recyclerviews.recipedetails;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecipeDetailsAbstractViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    public RecipeDetailsAbstractViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void present(T data);
}