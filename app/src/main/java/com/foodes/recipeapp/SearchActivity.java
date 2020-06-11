package com.foodes.recipeapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.suggestions.Suggestion;
import com.foodes.recipeapp.recyclerviews.suggestions.SuggestionCustomAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String loggedInUsername;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private MaterialTextView greeting;
    Button searchBtn;
    private EditText basicIngredient;
    private SuggestionCustomAdapter suggestionAdapter;
    private List<Object> suggestionList;
    int userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        basicIngredient = (EditText)findViewById(R.id.basicIngredientEditText);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent getUsername = getIntent();
        loggedInUsername = getUsername.getStringExtra("Username");

        greeting = findViewById(R.id.user_greeting_txt);

        //getting data from login activity
        Intent GreetLoggedInUser = getIntent();
        String username = GreetLoggedInUser.getStringExtra("Username");
        greeting.setText("Hello" + " " + username + "!");
        userId = getIntent().getIntExtra("userId",0);
        //drawer selection switch
        navigationView.setNavigationItemSelectedListener(this);

        searchBtn = findViewById(R.id.drawerSearchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, RecipesMenuActivity.class);
                intent.putExtra("ingredient", basicIngredient.getText().toString().toLowerCase().trim());
                intent.putExtra("userId",userId);
                intent.putExtra("Username", loggedInUsername);
                startActivity(intent);
            }
        });

        toggle.syncState();

    //recyclerView
    suggestionList = new ArrayList<Object>();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchActivityRecyclerView);
    suggestionAdapter = new SuggestionCustomAdapter(new ItemClickListener() {
        @Override
        public void onItemClick(Object item) {
            Intent intent = new Intent(SearchActivity.this, RecipesMenuActivity.class);
            if (item instanceof Suggestion) {
                intent.putExtra("ingredient", ((Suggestion) item).getParameter());
            }
            intent.putExtra("userId",userId);
            intent.putExtra("Username", loggedInUsername);
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
        recyclerView.setAdapter(suggestionAdapter);
        addSuggestionsToList();
        suggestionAdapter.submitList(suggestionList);
    }

    private void addSuggestionsToList() {
        suggestionList.add(new Suggestion("Beef", R.drawable.beef));
        suggestionList.add(new Suggestion("Burger", R.drawable.burger));
        suggestionList.add(new Suggestion("Chicken", R.drawable.chicken));
        suggestionList.add(new Suggestion("Eggs", R.drawable.eggs));
        suggestionList.add(new Suggestion("Pasta", R.drawable.pasta));
        suggestionList.add(new Suggestion("Rice", R.drawable.riceplate));
        suggestionList.add(new Suggestion("Steak", R.drawable.streak));


    }

    //swipe right or back button functionality
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
                Toast.makeText(this, "Yor're already in Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                askIfUserIsSure();
                break;
            case R.id.nav_profile:
                Intent goToProfile = new Intent(SearchActivity.this,UserProfile.class);
                goToProfile.putExtra("Username", loggedInUsername);
                goToProfile.putExtra("userId", userId);
                startActivity(goToProfile);
                break;
            case R.id.nav_favorites:
                Intent goToFavoriteList = new Intent(SearchActivity.this, FavoritesActivity.class);
                goToFavoriteList.putExtra("Username", loggedInUsername);
                goToFavoriteList.putExtra("userId", userId);
                startActivity(goToFavoriteList);
                break;
            case R.id.nav_otherUsers:
                Intent goToOtherUsers = new Intent(SearchActivity.this, OtherUsers.class);
                goToOtherUsers.putExtra("Username", loggedInUsername);
                goToOtherUsers.putExtra("userId", userId);
                startActivity(goToOtherUsers);
                break;
            case R.id.nav_about:
                Intent goToAbout = new Intent(SearchActivity.this, About.class);
                startActivity(goToAbout);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(SearchActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure that you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent redirectToMainScreen = new Intent(SearchActivity.this,MainActivity.class);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

}
