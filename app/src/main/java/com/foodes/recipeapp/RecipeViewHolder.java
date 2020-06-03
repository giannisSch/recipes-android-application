package com.foodes.recipeapp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecipeViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    @Nullable
    private ItemClickListener listener;

    @NonNull
    T data;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //checks if listener & data are not null
                if(listener != null && data !=null){
                    listener.onItemClick(data);
                }
            }
        });
    }

    public void setListener(@Nullable ItemClickListener listener) {
        this.listener = listener;
    }

    abstract void present(T data);
}
