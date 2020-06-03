package com.foodes.recipeapp.recyclerviews.recipedetails;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.json.nutrientsModels.IngredientModel;

public class RecipeDetailsViewHolder extends RecipeDetailsAbstractViewHolder<Object> {
    private TextView ingredientText;


    public RecipeDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredientText = itemView.findViewById(R.id.ingredientTextView);
    }



    @Override
    void present(Object data) {
        if (data instanceof IngredientModel) {
            ingredientText.setText(((IngredientModel) data).getText().toString());
        } else {
            //Do something for better User Experience
        }
    }
}
