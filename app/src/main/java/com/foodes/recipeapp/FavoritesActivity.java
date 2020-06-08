package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menu.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    UsersDatabase database;
    private List<Favorites> currentUserFavorites;
    private List<Object> currentUserFavoritesInObjectForm;
    private CustomAdapter adapter;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        database = UsersDatabase.getInstance(this);
        userId = getIntent().getIntExtra("userId", 0);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.favoritesRecyclerView);
        User currentUser = findCurrentUser();
        currentUserFavorites = new ArrayList<>();
        currentUserFavoritesInObjectForm = new ArrayList<>();


            currentUserFavorites = currentUser.getFavorite();
            convertToObjectList(currentUserFavorites);


        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
//                Intent intent = new Intent(FavoritesActivity.this, RecipeDetailsActivity.class);
//                intent.putExtra("RecipeModel", (Parcelable) item);
//                intent.putExtra("UserId", userId);
//                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.submitList(currentUserFavoritesInObjectForm);
    }

    private void convertToObjectList(List<Favorites> currentUserFavorites) {
        for(Favorites favorite: currentUserFavorites){
           currentUserFavoritesInObjectForm.add(favorite);
        }
    }

    private User findCurrentUser() {
        List<User> users = database.getUserDao().getAll();
        for(User user: users){
            if(user.getId() == userId){
                return user;
            }
        }
        //this will never return
        return new User("douleies", "me","foodes");
    }
}