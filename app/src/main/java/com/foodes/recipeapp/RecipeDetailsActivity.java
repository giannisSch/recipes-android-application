package com.foodes.recipeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.json.nutrientsModels.IngredientModel;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.recipedetails.RecipeDetailsAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView totalFat , totalCrabs, totalProtein, totalCalories;
    private ImageView foodImage, favoriteButton;
    private MaterialToolbar title;
    UsersDatabase database;
    Button gotoRecipeButton, gotoFavoritesList;
    int userId;
    private String recipeTitle, imageUrl, recipeUrl;
  //  User currentUser;



    private List<Object> ingredientsList;
    private RecipeDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ingredientsList = new ArrayList<Object>();
        initAdapter();
        //Get Details from Activity
        Intent intent = getIntent();
        RecipeModel recipe = intent.getParcelableExtra("RecipeModel");
        userId = getIntent().getIntExtra("UserId",0);
       // currentUser = getIntent().getParcelableExtra("User");

        database = UsersDatabase.getInstance(this);

        addIngredientsToList(recipe.getIngredients());
        adapter.submitList(ingredientsList);

        title = (MaterialToolbar)findViewById(R.id.topAppBar);
        totalFat = (TextView)findViewById(R.id.numberOfFat);
        totalCrabs = (TextView)findViewById(R.id.numberOfCrab);
        totalProtein = (TextView)findViewById(R.id.numberOfProtein);
        totalCalories = (TextView)findViewById(R.id.numberOfCalories);
        foodImage = (ImageView)findViewById(R.id.foodImg);
        favoriteButton = (ImageView)findViewById(R.id.favorite_button);
        gotoRecipeButton = (Button)findViewById(R.id.goToRecipeBtn);
        gotoFavoritesList = (Button)findViewById(R.id.goToFavoritesListBtn);

        recipeTitle = recipe.getLabel();
        int fat = (int) recipe.getDigest().get(0).getTotal();
        int crabs = (int) recipe.getDigest().get(1).getTotal();
        int protein = (int) recipe.getDigest().get(2).getTotal();
        int calories = (int) recipe.getCalories();
        imageUrl = recipe.getImage();
        recipeUrl = recipe.getUrl();


        title.setTitle(recipeTitle);
        totalFat.setText(fat+"g");
        totalCrabs.setText(crabs+"g");
        totalProtein.setText(protein+"g");
        totalCalories.setText(calories+"");
        Picasso.get().load(imageUrl).into(foodImage);

        if(isAlreadyFavorite(new Favorites(recipeTitle, imageUrl))){
            favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

        if (!recipeUrl.startsWith("http://") && !recipeUrl.startsWith("https://")){
            recipeUrl = "http://" + recipeUrl;
        }

        favoriteButton.setOnClickListener(this);
        gotoRecipeButton.setOnClickListener(this);
        gotoFavoritesList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.favorite_button:
                Favorites fav = new Favorites(recipeTitle, imageUrl);
                if(isAlreadyFavorite(fav)){
                    removeFromFavorites(fav);
                }else{
                    addFavoriteToUser(fav);
                }
                break;
            case R.id.goToRecipeBtn:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeUrl));
                startActivity(browserIntent);
                break;
            case R.id.goToFavoritesListBtn:
                Intent favoriteList = new Intent(RecipeDetailsActivity.this, FavoritesActivity.class);
                favoriteList.putExtra("userId", userId);
                startActivity(favoriteList);
                break;
        }
    }

    private void addFavoriteToUser(Favorites favorite){
        List<User> users = database.getUserDao().getAll();
        for(User user: users){
            if(user.getId() == userId){
                user.addFavorite(favorite);
                database.getUserDao().update(user);
                Log.e("list1",user.getFavorite().toString());
                Log.e("list1",user.getFavorite().size()+"");
                break;
            }
        }
        favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_24);
    }

    private void removeFromFavorites(Favorites favorites) {
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            if (user.getId() == userId) {
                List<Favorites> currentUserFavList = user.getFavorite();
                for (Favorites fav : currentUserFavList) {
                    if (favorites.equals(fav)) {
                        user.removeFavorite(favorites);
                        database.getUserDao().update(user);
                        Log.e("list2",user.getFavorite().toString());
                        Log.e("list2",user.getFavorite().size()+"");
                        break;
                    }
                }
            }
        }
        favoriteButton.setImageResource(R.drawable.ic_baseline_not_favorite);
    }

    private boolean isAlreadyFavorite(Favorites favorites){
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            if (user.getId() == userId) {
                List<Favorites> currentUserFavList = user.getFavorite();
                for (Favorites favorite : currentUserFavList) {
                    if (favorite.equals(favorites)) {
                       return true;
                    }
                }
            }
        }
        return false;
    }
    private void addIngredientsToList(List<IngredientModel> ingredients) {
        for(int i=0; i<ingredients.size(); i++){
            ingredientsList.add(ingredients.get(i));
        }
    }

    private void initAdapter(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recipeDetailsRecyclerView);
        adapter = new RecipeDetailsAdapter();
        recyclerView.setAdapter(adapter);
    }


}
