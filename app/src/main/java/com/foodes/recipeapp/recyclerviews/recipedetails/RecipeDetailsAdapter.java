package com.foodes.recipeapp.recyclerviews.recipedetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.json.nutrientsModels.DiffItemCallbackClass;
import com.foodes.recipeapp.json.nutrientsModels.IngredientModel;

public class RecipeDetailsAdapter extends ListAdapter<Object, RecipeDetailsAbstractViewHolder<Object>> {


    public RecipeDetailsAdapter() {
        super(new DiffItemCallbackClass<Object>());
    }

    @NonNull
    @Override
    public RecipeDetailsAbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.ingredient_holder_item_row) {
            return new RecipeDetailsViewHolder(view);
        }else {
            return new RecipeDetailsEmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailsAbstractViewHolder<Object> holder, int position) {
        holder.present(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof IngredientModel) {
            return R.layout.ingredient_holder_item_row;
        }else {
            return R.layout.holder_empty;
        }
    }


}