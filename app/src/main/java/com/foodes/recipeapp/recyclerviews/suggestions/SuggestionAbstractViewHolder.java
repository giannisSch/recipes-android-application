package com.foodes.recipeapp.recyclerviews.suggestions;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.recyclerviews.ItemClickListener;

public abstract class SuggestionAbstractViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    @Nullable
    protected ItemClickListener listener;
    T data;

    public SuggestionAbstractViewHolder(@NonNull View itemView) {
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