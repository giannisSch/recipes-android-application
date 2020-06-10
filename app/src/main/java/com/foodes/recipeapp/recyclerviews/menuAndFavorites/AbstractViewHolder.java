package com.foodes.recipeapp.recyclerviews.menuAndFavorites;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;

import java.util.ArrayList;

public abstract class AbstractViewHolder<T extends Object> extends RecyclerView.ViewHolder {

    @Nullable
    protected ItemClickListener listener;
    T data;
    User dataTwo;

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && data != null) {
                    listener.onItemClick(data);
                    listener.onOtherUserClick(dataTwo);
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