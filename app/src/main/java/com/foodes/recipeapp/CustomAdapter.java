package com.foodes.recipeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ListAdapter<Object, AbstractViewHolder<Object>> {


    private ItemClickListener listener;
    public CustomAdapter(ItemClickListener listener) {
        super(new DiffItemCallbackClass<Object>());
        this.listener = listener;

    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_holder_item_row, parent, false);
        return new CustomViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder<Object> holder, int position) {
        holder.present(getItem(position));
    }


}