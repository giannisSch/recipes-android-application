package com.foodes.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.foodes.recipeapp.database.UsersDb.User;
import com.google.android.material.textview.MaterialTextView;

public class SearchActivity extends AppCompatActivity {

    private EditText basicIngredient;
    private Button searchBtn;
    private MaterialTextView greeting;
    int userId;
//    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //getting access on textview
        greeting = findViewById(R.id.user_greeting_txt);

        //getting user's id
        userId = getIntent().getIntExtra("UserId",0);
   //     currentUser = getIntent().getParcelableExtra("User");

        //getting data from login activity
        Intent GreetLoggedInUser = getIntent();
        String username = GreetLoggedInUser.getStringExtra("Username");
        greeting.setText("Hello" + " " + username + "!");

        basicIngredient = (EditText) findViewById(R.id.basicIngredientEditText);
        searchBtn = (Button) findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, RecipesMenuActivity.class);
                intent.putExtra("ingredient", basicIngredient.getText().toString().toLowerCase().trim());
                intent.putExtra("UserId",userId);
           //     intent.putExtra("User", (Parcelable) currentUser);
                startActivity(intent);

            }
        });

    }
}
