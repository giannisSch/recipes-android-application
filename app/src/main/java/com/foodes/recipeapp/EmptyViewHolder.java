package com.foodes.recipeapp;

import android.view.View;

import androidx.annotation.NonNull;

public class EmptyViewHolder extends AbstractViewHolder<Object>{
    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    void present(Object data) {

    }
}
