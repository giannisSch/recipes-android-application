package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class OtherUserFavorites extends AppCompatActivity {

    ArrayList<RecipeModel> otherUserFavorites;
    ArrayList<Object> otherUserFavoritesInObjectForm;
    private int userId;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_favorites);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Intent intent = getIntent();
        otherUserFavorites = intent.getParcelableArrayListExtra("selectedUserFavoritesList");
        userId = getIntent().getIntExtra("userId",0);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.otherUserFavoritesRecyclerView);

        otherUserFavoritesInObjectForm = convertToObjectList(otherUserFavorites);

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
                Intent intent = new Intent(OtherUserFavorites.this, RecipeDetailsActivity.class);
                intent.putExtra("RecipeModel", (Parcelable) item);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }

            @Override
            public void onFavoriteClick(RecipeModel data) {
                //do nothing
            }

            @Override
            public void onOtherUserClick(User user) {
                //do nothing
            }

        });
        recyclerView.setAdapter(adapter);
        adapter.submitList(otherUserFavoritesInObjectForm);

    }

    private ArrayList<Object> convertToObjectList(ArrayList<RecipeModel> otherUserFavorites) {
        ArrayList<Object> otherUserListInObjectForm = new ArrayList<>();
        for (RecipeModel recipeModel : otherUserFavorites) {
            otherUserListInObjectForm.add(recipeModel);
        }
        return otherUserListInObjectForm;
    }
}