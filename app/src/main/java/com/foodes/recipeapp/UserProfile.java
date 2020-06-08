package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView username, email, username_info, email_info;
    private String favoriteCounter, getUsername;
    Button btn;
    UsersDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //getting db instance
        database = UsersDatabase.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //get data with intent to display username & mail next to profile photo
        username = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);
        username_info = findViewById(R.id.user_txtview);
        email_info = findViewById(R.id.mail_txtview);

        //getting username
        Intent UsernameIntent = getIntent();
        getUsername = UsernameIntent.getStringExtra("Username");

        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String user_username = user.getUsername();
            if (getUsername.equals(user_username)) {
                String loggedIn_username = user.getUsername();
                String loggedIn_email = user.getEmail();
                username.setText(loggedIn_username);
                email.setText(loggedIn_email);
                username_info.setText(loggedIn_username);
                email_info.setText(loggedIn_email);
            }
        }

        btn = findViewById(R.id.profile_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToUpdateUserInfo = new Intent(UserProfile.this, UpdateUserProfileInfo.class);
                goToUpdateUserInfo.putExtra("Username", getUsername);
                startActivity(goToUpdateUserInfo);
            }
        });
    }

}
