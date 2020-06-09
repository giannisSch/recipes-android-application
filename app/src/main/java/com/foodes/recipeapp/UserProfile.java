package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.room.Database;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView username, email, username_info, email_info, totalFavorites;
    private String getUsername;
    private int userId;
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
        totalFavorites =(TextView) findViewById(R.id.card_fav_counter);

        //getting username
        Intent UsernameIntent = getIntent();
        getUsername = UsernameIntent.getStringExtra("Username");

        userId = getIntent().getIntExtra("userId",0);

        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String user_username = user.getUsername();
            if (getUsername.equals(user_username)) {
                username.setText(user.getUsername());
                email.setText(user.getEmail());
                username_info.setText(user.getUsername());
                email_info.setText(user.getEmail());
                totalFavorites.setText(user.getFavorite().size()+"");
            }
        }

        btn = findViewById(R.id.profile_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //msg box
                askIfUserIsSure();
            }
        });
    }
    
    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(UserProfile.this);
        builder.setTitle("Update user info");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoUpdateUserInfo();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //close drawer
            }
        });
        builder.show();
    }

    private void gotoUpdateUserInfo(){
        Intent goToUpdateUserInfo = new Intent(UserProfile.this, UpdateUserProfileInfo.class);
        goToUpdateUserInfo.putExtra("Username", getUsername);
        startActivity(goToUpdateUserInfo);
    }

}
