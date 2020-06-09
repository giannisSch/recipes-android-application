package com.foodes.recipeapp.database.UsersDb;

import androidx.room.TypeConverter;

import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<RecipeModel> fromFavorite(String favorite) {
        Type listType = new TypeToken<ArrayList<RecipeModel>>() {}.getType();
        return new Gson().fromJson(favorite, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<RecipeModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
