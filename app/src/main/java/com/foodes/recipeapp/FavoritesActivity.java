package com.foodes.recipeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.Favorites.FavoritesAdapter;
import com.foodes.recipeapp.recyclerviews.recipedetails.MyItemTouchHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    UsersDatabase database;
    private ArrayList<RecipeModel> currentUserFavorites;
    private List<Object> currentUserFavoritesInObjectForm;
    private FavoritesAdapter adapter;
    int userId;
    private String loggedInUsername;
    ImageView noFavoritesImage;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

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

        database = UsersDatabase.getInstance(this);
        userId = getIntent().getIntExtra("userId", 0);
        loggedInUsername = getIntent().getStringExtra("Username");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.favoritesRecyclerView);
        currentUser = findCurrentUser();
        currentUserFavorites = new ArrayList<>();
        currentUserFavoritesInObjectForm = new ArrayList<>();
        navigationView.setNavigationItemSelectedListener(this);
        noFavoritesImage = (ImageView)findViewById(R.id.noFavoritesImg);

        currentUserFavorites = new ArrayList<>();
        currentUserFavorites.addAll(currentUser.getFavorite());
        convertToObjectList(currentUserFavorites);


        adapter = new FavoritesAdapter(currentUserFavorites, new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                //do nothing
            }
            @Override
            public void onFavoriteClick(RecipeModel data) {
                Intent intent = new Intent(FavoritesActivity.this, RecipeDetailsActivity.class);
                    intent.putExtra("RecipeModel", data);
                    intent.putExtra("userId", userId);
//                Bundle parameter = new Bundle();
//                parameter.putParcelable("RecipeModel", data);
//                parameter.putInt("userId", userId);
//                intent.putExtras(parameter);
                startActivity(intent);
            }

            @Override
            public void onOtherUserClick(User user) {
                //do nothing
            }

        }, currentUser, database);
        ItemTouchHelper.Callback callback = new MyItemTouchHelper(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        adapter.setmItemTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        if(adapter.getItemCount() == 0) {
            Toast.makeText(this, "There are no favorites", Toast.LENGTH_SHORT).show();
            noFavoritesImage.setVisibility(View.VISIBLE);
        }
        toggle.syncState();
    }

    private void convertToObjectList(List<RecipeModel> currentUserFavorites) {
        for (RecipeModel favorite : currentUserFavorites) {
            currentUserFavoritesInObjectForm.add(favorite);
        }
    }

    private User findCurrentUser() {
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        //this will never return
        return new User("douleies", "me", "foodes");
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
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent goToHomePage = new Intent(FavoritesActivity.this, SearchActivity.class);
                goToHomePage.putExtra("Username", loggedInUsername);
                goToHomePage.putExtra("userId", userId);
                startActivity(goToHomePage);
                break;
            case R.id.nav_logout:
                askIfUserIsSure();
                break;
            case R.id.nav_profile:
                Intent goToProfile = new Intent(FavoritesActivity.this, UserProfile.class);
                goToProfile.putExtra("Username", loggedInUsername);
                goToProfile.putExtra("userId", userId);
                startActivity(goToProfile);
                break;
            case R.id.nav_favorites:
                Toast.makeText(this, "Yor're already in your favorites list", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_otherUsers:
                Intent goToOtherUsers = new Intent(FavoritesActivity.this, OtherUsers.class);
                goToOtherUsers.putExtra("Username", loggedInUsername);
                goToOtherUsers.putExtra("userId", userId);
                startActivity(goToOtherUsers);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(FavoritesActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent redirectToMainScreen = new Intent(FavoritesActivity.this,MainActivity.class);
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