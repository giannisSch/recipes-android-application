package com.foodes.recipeapp.recyclerviews.menuAndFavorites.Favorites;


import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.FavoritesActivity;
import com.foodes.recipeapp.R;
import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.ItemTouchHelperAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<RecipeModel> mFavorites = new ArrayList<>();
    private ItemClickListener listener;
    ItemTouchHelper mItemTouchHelper;
    int userId;
    User currentUser;
    UsersDatabase database;

    public FavoritesAdapter(ArrayList<RecipeModel> mFavorites, ItemClickListener listener, User currentUser, UsersDatabase database) {
        this.mFavorites = mFavorites;
        this.listener = listener;
        this.currentUser = currentUser;
        this.database = database;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_favorites, parent, false);
        return new FavoritesViewHolder(view, listener, mItemTouchHelper);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        holder.present(mFavorites.get(position));
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        RecipeModel fromFavorite = mFavorites.get(fromPosition);
        mFavorites.remove(fromFavorite);
        mFavorites.add(toPosition, fromFavorite);
        notifyItemMoved(fromPosition, toPosition);
        database.getUserDao().update(currentUser);
    }

    @Override
    public void onItemSwiped(int position) {
        mFavorites.remove(position);
        notifyItemRemoved(position);
        database.getUserDao().update(currentUser);
    }

    public void setmItemTouchHelper(ItemTouchHelper mItemTouchHelper) {
        this.mItemTouchHelper = mItemTouchHelper;
    }
}

