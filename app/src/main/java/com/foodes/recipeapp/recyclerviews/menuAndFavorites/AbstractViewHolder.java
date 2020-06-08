package com.foodes.recipeapp.recyclerviews.menuAndFavorites;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.recyclerviews.ItemClickListener;

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

    protected abstract void present(T data);
}