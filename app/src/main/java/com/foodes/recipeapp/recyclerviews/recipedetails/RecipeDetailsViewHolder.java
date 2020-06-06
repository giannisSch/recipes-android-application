package com.foodes.recipeapp.recyclerviews.recipedetails;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.json.nutrientsModels.IngredientModel;

public class RecipeDetailsViewHolder extends RecipeDetailsAbstractViewHolder<Object> {
    private TextView ingredientText;
    private TextView ingredientWeight;

    public RecipeDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredientText = itemView.findViewById(R.id.ingredientTextTextView);
        ingredientWeight = itemView.findViewById(R.id.ingredientWeightTextView);
    }



    @Override
    void present(Object data) {
        if (data instanceof IngredientModel) {
            ingredientText.setText(((IngredientModel) data).getText().toString());
            double  weight = Math.round(((IngredientModel) data).getWeight() * 100) / 100;
            ingredientWeight.setText(weight + "g"); //round to 2 decimals
        } else {
            //Do something for better User Experience
        }
    }
}
