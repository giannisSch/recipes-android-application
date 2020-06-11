package com.foodes.recipeapp.recyclerviews.suggestions;

import android.view.View;

import androidx.annotation.NonNull;

public class EmptyViewHolder extends SuggestionAbstractViewHolder<Object> {
    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void present(Object data) {

    }
}
