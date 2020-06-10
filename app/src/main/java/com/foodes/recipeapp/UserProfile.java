package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.room.Database;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView username, email, username_info, email_info;
    private static final int RESULT_LOAD_IMAGE = 1;
    private String favoriteCounter, getUsername;
    Button btn;
    ImageView userImg;
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
        userImg = findViewById(R.id.profile_photo);

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

        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadUserPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(uploadUserPhoto, RESULT_LOAD_IMAGE);
                saveUserImg();
            }
        });

        btn = findViewById(R.id.profile_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //msg box
                askIfUserIsSure();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            userImg.setImageURI(selectedImage);
        }
    }

    private void saveUserImg(){
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String user_username = user.getUsername();
            String user_email = user.getEmail();
            String user_pass = user.getPassword();

            if (getUsername.equals(user_username)) {
                Bitmap bitmap = ((BitmapDrawable) userImg.getDrawable()).getBitmap();
                User newUser = new User(user_username, user_email, user_pass, bitmap);
                database.getUserDao().update(newUser);
            }
        }
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
