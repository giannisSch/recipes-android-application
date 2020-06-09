package com.foodes.recipeapp.recyclerviews;

import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;

public interface ItemClickListener{
    public void onItemClick(Object item);

    public void onFavoriteClick(RecipeModel data);

}
