package com.foodes.recipeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String loggedInUsername;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent GreetLoggedInUser = getIntent();
        String username = GreetLoggedInUser.getStringExtra("Username");
        userId = getIntent().getIntExtra("userId",0);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent goToSearhActivity = new Intent(About.this, SearchActivity.class);
                goToSearhActivity.putExtra("Username", loggedInUsername);
                goToSearhActivity.putExtra("userId", userId);
                startActivity(goToSearhActivity);
                break;
            case R.id.nav_logout:
                askIfUserIsSure();
                break;
            case R.id.nav_profile:
                Intent goToProfile = new Intent(About.this,UserProfile.class);
                goToProfile.putExtra("Username", loggedInUsername);
                goToProfile.putExtra("userId", userId);
                startActivity(goToProfile);
                break;
            case R.id.nav_favorites:
                Intent goToFavoriteList = new Intent(About.this, FavoritesActivity.class);
                goToFavoriteList.putExtra("Username", loggedInUsername);
                goToFavoriteList.putExtra("userId", userId);
                startActivity(goToFavoriteList);
                break;
            case R.id.nav_otherUsers:
                Intent goToOtherUsers = new Intent(About.this, OtherUsers.class);
                goToOtherUsers.putExtra("Username", loggedInUsername);
                goToOtherUsers.putExtra("userId", userId);
                startActivity(goToOtherUsers);
            case R.id.nav_about:
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(About.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent redirectToMainScreen = new Intent(About.this,MainActivity.class);
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
