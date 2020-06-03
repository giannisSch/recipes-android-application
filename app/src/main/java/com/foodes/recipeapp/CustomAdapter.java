package com.foodes.recipeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends ListAdapter<Object, RecipeViewHolder<Object>> {

    private ItemClickListener listener;

    //constructor of the CustomAdapter
    protected CustomAdapter(ItemClickListener listener) {
        super(new DiffItemCallbackClass());
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder<Object> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout to the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_row,parent,false);
        return new CustomViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder<Object> holder, int position) {
        //presenting the data of the holder
        holder.present(getItem(position));
    }
}
