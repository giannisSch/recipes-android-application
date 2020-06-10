package com.foodes.recipeapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.HitModel;
import com.foodes.recipeapp.json.nutrientsModels.JsonModel;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.CustomAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipesMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<Object> recipeList;
    private CustomAdapter adapter;
    private String loggedInUsername;
    int userId;
    private ProgressBar progressBar;
    private String ingredient;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
   // User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes__menu);
        ingredient = getIntent().getStringExtra("ingredient");

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Results for "+ingredient);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        recipeList = new ArrayList<Object>();
        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        userId = getIntent().getIntExtra("userId",0);
        loggedInUsername = getIntent().getStringExtra("Username");
       // currentUser = getIntent().getParcelableExtra("User");

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
                Intent intent = new Intent(RecipesMenuActivity.this, RecipeDetailsActivity.class);
                intent.putExtra("RecipeModel", (Parcelable) item);
                intent.putExtra("userId", userId);
                intent.putExtra("Username", loggedInUsername);
    //            intent.putExtra("User", currentUser);
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

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.edamam.com/search?q=" + ingredient + "&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";
//        String url = "https://api.edamam.com/search?q=egg&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE); // make progress bar invisible
                        JsonModel jsonModel = new Gson().fromJson(response, JsonModel.class);
                        if (jsonModel.isMore()){    //check if json response have data
                            addRecipesToList(jsonModel);
                            adapter.submitList(recipeList);
                        }else{
                            showToast("There are not available recipes with this ingredient", Toast.LENGTH_LONG);
                            goToSearchActivity();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    showToast("Please check your internet connection", Toast.LENGTH_LONG);
                }
                goToSearchActivity();
            }
        });
        toggle.syncState();
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void addRecipesToList(JsonModel jsonModel){
        List<HitModel> hits =  jsonModel.getHit();
        for(int i = 0; i < hits.size(); i++){
            recipeList.add(hits.get(i).getRecipe());
        }

    }

    private void showToast(CharSequence text, int duration){
        Toast.makeText(getApplicationContext(), text, duration).show();
    }

    private void goToSearchActivity(){
        Intent intent = new Intent(RecipesMenuActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent goToHomePage = new Intent(RecipesMenuActivity.this, drawer_test.class);
                goToHomePage.putExtra("Username", loggedInUsername);
                goToHomePage.putExtra("userId", userId);
                startActivity(goToHomePage);
                break;
            case R.id.nav_logout:
                askIfUserIsSure();
                break;
            case R.id.nav_profile:
                Intent goToProfile = new Intent(RecipesMenuActivity.this,UserProfile.class);
                goToProfile.putExtra("Username", loggedInUsername);
                goToProfile.putExtra("userId", userId);
                startActivity(goToProfile);
                break;
            case R.id.nav_favorites:
                Intent goToFavoriteList = new Intent(RecipesMenuActivity.this, FavoritesActivity.class);
                goToFavoriteList.putExtra("Username", loggedInUsername);
                goToFavoriteList.putExtra("userId", userId);
                startActivity(goToFavoriteList);
                break;
            case R.id.nav_otherUsers:
                Intent goToOtherUsers = new Intent(RecipesMenuActivity.this, OtherUsers.class);
                goToOtherUsers.putExtra("Username", loggedInUsername);
                goToOtherUsers.putExtra("userId", userId);
                startActivity(goToOtherUsers);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(RecipesMenuActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent redirectToMainScreen = new Intent(RecipesMenuActivity.this,MainActivity.class);
                startActivity(redirectToMainScreen);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //close drawer
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        builder.show();
    }

}