package com.foodes.recipeapp.recyclerviews;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public interface ItemClickListener{
    public void onItemClick(Object item);

    public void onFavoriteClick(RecipeModel data);

    public void onOtherUserClick(User user);

}
