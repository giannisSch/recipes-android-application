package com.foodes.recipeapp.recyclerviews.menuAndFavorites.Favorites;


import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.ItemTouchHelperAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> implements ItemTouchHelperAdapter {

    private List<Favorites> mFavorites = new ArrayList<>();
    private ItemClickListener listener;
    ItemTouchHelper mItemTouchHelper;
    int userId;
    User currentUser;
    UsersDatabase database;

    public FavoritesAdapter(List<Favorites> mFavorites, ItemClickListener listener, User currentUser, UsersDatabase database) {
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
//        try {
//            String title = mFavorites.get(position).getFoodName();
//            String foodImg = mFavorites.get(position).getFoodImage();
//            holder.title.setText(title);
//            Picasso.get().load(foodImg).into(holder.recipeImg);
//        } catch (NullPointerException e) {
//            Log.e("ERROR", "onBindViewHolder: Null Pointer: " + e.getMessage());
//        }
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Favorites fromFavorite = mFavorites.get(fromPosition);
        mFavorites.remove(fromFavorite);
        mFavorites.add(toPosition, fromFavorite);
        notifyItemMoved(fromPosition, toPosition);
        for(Favorites favorites: mFavorites){
            currentUser.getFavorite().add(favorites);
        }
        database.getUserDao().update(currentUser);
    }

    @Override
    public void onItemSwiped(int position) {
        mFavorites.remove(position);
        notifyItemRemoved(position);
    }

    public void setmItemTouchHelper(ItemTouchHelper mItemTouchHelper) {
        this.mItemTouchHelper = mItemTouchHelper;
    }

//    public class ViewHolder extends RecyclerView.ViewHolder implements
//            View.OnTouchListener,
//            GestureDetector.OnGestureListener
//    {
//
//        TextView title;
//        ImageView recipeImg;
//        ItemClickListener listener;
//        GestureDetector mGestureDetector;
//
//        public ViewHolder(View itemView, ItemClickListener listener) {
//            super(itemView);
//            title = (TextView)itemView.findViewById(R.id.favoriteTitleTextView);
//            recipeImg = (ImageView)itemView.findViewById(R.id.favoriteHolderImageView);
//            this.listener = listener;
//
//            mGestureDetector = new GestureDetector(itemView.getContext(), this);
//            itemView.setOnTouchListener(this);
//        }
//
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            mGestureDetector.onTouchEvent(event);
//            return true;
//        }
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public void onShowPress(MotionEvent e) {
//
//        }
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            listener.onItemClick(getAdapterPosition());
//            return true;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            return true;
//        }
//
//        @Override
//        public void onLongPress(MotionEvent e) {
//            mItemTouchHelper.startDrag(this);
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            return false;
//        }
//
//    }
}

