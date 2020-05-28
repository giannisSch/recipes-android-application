package com.foodes.recipeapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecViewHolder extends RecyclerView.ViewHolder {
    private TextView recipeImage;
    private TextView descriptionText;
    private TextView ingredientsText;
    private ItemClickListener itemClickListener;
    private Recipe recipe;

    public RecViewHolder(@NonNull View itemView, final ItemClickListener itemClickListener ) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(recipe);
            }
        });
        recipeImage = itemView.findViewById(R.id.recipeImageView);
        descriptionText = itemView.findViewById(R.id.descriptionTextView);
        ingredientsText = itemView.findViewById(R.id.ingredientsTextView);
    }
    public void present(Recipe recipe){
        this.recipe =  recipe;
        recipeImage.setText(recipe.getImage());
        descriptionText.setText(recipe.getDescription());
        ingredientsText.setText(recipe.getIngredients().toString());
    }
}

