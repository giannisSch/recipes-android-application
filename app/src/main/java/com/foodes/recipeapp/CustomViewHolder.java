package com.foodes.recipeapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomViewHolder extends AbstractViewHolder<Object> {
    private ImageView recipeImage;
    private TextView descriptionText;
    private TextView ingredientsText;

    public CustomViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        setListener(listener);
        recipeImage = itemView.findViewById(R.id.recipeImageView);
        descriptionText = itemView.findViewById(R.id.dietLabelTextView);
        ingredientsText = itemView.findViewById(R.id.caloriesTextView);
    }


    @Override
    void present(Object data) {
        setData(data);
        if (data instanceof Recipe) {
            //recipeImage.setText(((Recipe) data).getImage());
            descriptionText.setText(((Recipe) data).getDescription());
            ingredientsText.setText(((Recipe) data).getIngredients().toString());
        } else {
            //Do something for better User Experience
        }
    }
}
