package com.foodes.recipeapp.recyclerviews.menuAndFavorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.json.nutrientsModels.DiffItemCallbackClass;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;

public class CustomAdapter extends ListAdapter<Object, AbstractViewHolder<Object>> {


    private ItemClickListener listener;
    public CustomAdapter(ItemClickListener listener) {
        super(new DiffItemCallbackClass<Object>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.recipe_holder_item_row) {
            return new CustomViewHolder(view, listener);
        }else {
            return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder<Object> holder, int position) {
        holder.present(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof RecipeModel || item instanceof Favorites) {
            return R.layout.recipe_holder_item_row;
        }else {
            return R.layout.holder_empty;
        }
    }


}