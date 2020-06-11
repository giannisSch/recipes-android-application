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
import android.service.autofill.UserData;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.CustomAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class OtherUsers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    UsersDatabase database;
    private int userId;
    private List<User> allUsers;
    private CustomAdapter adapter;
    private double kapa, k;
    private String loggedInUsername;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private MaterialTextView greeting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_users);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);

        database = UsersDatabase.getInstance(this);
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        navigationView.setNavigationItemSelectedListener(this);


        loggedInUsername = getIntent().getStringExtra("Username");
        userId = getIntent().getIntExtra("userId", 0);
        allUsers = database.getUserDao().getAll();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.otherUsersRecyclerView);

        deleteCurrentUserFromUsersList(allUsers);

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                if(item instanceof User){
                    List<RecipeModel> otherUserFavoritesList = ((User) item).getFavorite();
                    ArrayList<RecipeModel> otherUserFavoritesArrayList = convertListToArrayList(otherUserFavoritesList);
                    Intent intent = new Intent(OtherUsers.this, OtherUserFavorites.class);
                    Bundle parameter = new Bundle();
                    parameter.putParcelableArrayList("selectedUserFavoritesList", otherUserFavoritesArrayList);
                    parameter.putInt("userId", userId);
                    intent.putExtras(parameter);
                    startActivity(intent);
                }
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

        adapter.submitList(convertToObjectList(allUsers));

        toggle.syncState();

    }



    private void deleteCurrentUserFromUsersList(List<User> allUsersList) {
        for (User user : allUsersList) {
            if(user.getId() == userId){
                allUsersList.remove(user);
                break;
            }
        }
    }

    private List<Object> convertToObjectList(List<User> currentUserFavorites) {
        List<Object> currentUserListInObjectForm = new ArrayList<>();
        for (User user : currentUserFavorites) {
            currentUserListInObjectForm.add(user);
        }
        return currentUserListInObjectForm;
    }

    private ArrayList<RecipeModel> convertListToArrayList(List<RecipeModel> list){
        ArrayList<RecipeModel> otherUserFavoritesArrayList = new ArrayList<>();
        otherUserFavoritesArrayList.addAll(list);
        return otherUserFavoritesArrayList;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //do not go back
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent goToHomeScreen= new Intent(OtherUsers.this, SearchActivity.class);
                goToHomeScreen.putExtra("Username", loggedInUsername);
                goToHomeScreen.putExtra("userId", userId);
                startActivity(goToHomeScreen);
                break;
            case R.id.nav_logout:
                askIfUserIsSure();
                break;
            case R.id.nav_profile:
                Intent goToProfile = new Intent(OtherUsers.this,UserProfile.class);
                goToProfile.putExtra("Username", loggedInUsername);
                goToProfile.putExtra("userId", userId);
                startActivity(goToProfile);
                break;
            case R.id.nav_favorites:
                Intent goToFavoriteList = new Intent(OtherUsers.this, FavoritesActivity.class);
                goToFavoriteList.putExtra("Username", loggedInUsername);
                goToFavoriteList.putExtra("userId", userId);
                startActivity(goToFavoriteList);
                break;
            case R.id.nav_otherUsers:
                break;
            case R.id.nav_about:
                Intent goToAbout = new Intent(OtherUsers.this, About.class);
                startActivity(goToAbout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(OtherUsers.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent redirectToMainScreen = new Intent(OtherUsers.this,MainActivity.class);
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