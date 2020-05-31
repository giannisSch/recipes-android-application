package com.foodes.recipeapp;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class AbstractViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    @Nullable
    private ItemClickListener listener;
    T data;

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && data != null) {
                    listener.onItemClick(data);
                }
            }
        });
    }

    public void setListener(@NonNull ItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(T data) {
        this.data = data;
    }

    abstract void present(T data);
}