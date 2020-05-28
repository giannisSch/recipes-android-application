package com.foodes.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecViewHolder> {
    private ArrayList<Recipe> recipes;
    private ItemClickListener listener;
    public RecAdapter(ArrayList<Recipe> recipeArrayList, ItemClickListener listener) {
        recipes = recipeArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_holder_item_row, parent, false);
        return new RecViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        holder.present(recipes.get(position));

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
