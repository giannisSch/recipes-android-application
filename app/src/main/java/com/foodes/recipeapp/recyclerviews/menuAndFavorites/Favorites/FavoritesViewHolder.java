package com.foodes.recipeapp.recyclerviews.menuAndFavorites.Favorites;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.ItemTouchHelperAdapter;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.AbstractViewHolder;
import com.foodes.recipeapp.recyclerviews.recipedetails.MyItemTouchHelper;
import com.squareup.picasso.Picasso;

public class FavoritesViewHolder extends AbstractViewHolder<RecipeModel> implements View.OnTouchListener, GestureDetector.OnGestureListener{
    TextView title;
    ImageView recipeImg;
    GestureDetector mGestureDetector;
    ItemTouchHelper mItemTouchHelper;
    RecipeModel data;


    public FavoritesViewHolder(@NonNull View itemView, ItemClickListener listener, ItemTouchHelper mItemTouchHelper) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.favoriteTitleTextView);
        recipeImg = (ImageView)itemView.findViewById(R.id.favoriteHolderImageView);
        setListener(listener);
        this.mItemTouchHelper = mItemTouchHelper;
        mGestureDetector = new GestureDetector(itemView.getContext(), this);
        itemView.setOnTouchListener(this);
    }

    @Override
    protected void present(RecipeModel data) {
        this.data = data;
        Picasso.get().load(data.getImage()).into(recipeImg);
        title.setText(data.getLabel());
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        listener.onFavoriteClick(data);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        mItemTouchHelper.startDrag(this);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }
}
