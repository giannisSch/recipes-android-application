package com.foodes.recipeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.CustomAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class OtherUserFavorites extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<RecipeModel> otherUserFavorites;
    ArrayList<Object> otherUserFavoritesInObjectForm;
    private int userId;
    private CustomAdapter adapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private String loggedInUsername;
    private MaterialTextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        otherUserFavorites = intent.getParcelableArrayListExtra("selectedUserFavoritesList");
        loggedInUsername = getIntent().getStringExtra("Username");
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

        toggle.syncState();

    }

    private ArrayList<Object> convertToObjectList(ArrayList<RecipeModel> otherUserFavorites) {
        ArrayList<Object> otherUserListInObjectForm = new ArrayList<>();
        for (RecipeModel recipeModel : otherUserFavorites) {
            otherUserListInObjectForm.add(recipeModel);
        }
        return otherUserListInObjectForm;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent goToHomeScreen= new Intent(OtherUserFavorites.this, SearchActivity.class);
                goToHomeScreen.putExtra("Username", loggedInUsername);
                goToHomeScreen.putExtra("userId", userId);
                startActivity(goToHomeScreen);
                break;
            case R.id.nav_logout:
                askIfUserIsSure();
                break;
            case R.id.nav_profile:
                Intent goToProfile = new Intent(OtherUserFavorites.this,UserProfile.class);
                goToProfile.putExtra("Username", loggedInUsername);
                goToProfile.putExtra("userId", userId);
                startActivity(goToProfile);
                break;
            case R.id.nav_favorites:
                Intent goToFavoriteList = new Intent(OtherUserFavorites.this, FavoritesActivity.class);
                goToFavoriteList.putExtra("Username", loggedInUsername);
                goToFavoriteList.putExtra("userId", userId);
                startActivity(goToFavoriteList);
                break;
            case R.id.nav_otherUsers:
                Intent goToOtherUsers = new Intent(OtherUserFavorites.this, OtherUsers.class);
                goToOtherUsers.putExtra("Username", loggedInUsername);
                goToOtherUsers.putExtra("userId", userId);
                startActivity(goToOtherUsers);
                break;
            case R.id.nav_about:
                Intent goToAbout = new Intent(OtherUserFavorites.this, About.class);
                startActivity(goToAbout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(OtherUserFavorites.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent redirectToMainScreen = new Intent(OtherUserFavorites.this,MainActivity.class);
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