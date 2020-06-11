package com.foodes.recipeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.room.Database;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    TextView username, email, username_info, email_info, totalFavorites;
    private String getUsername;
    private String favoriteCounter;
    private int userId;
    Button btn;
    UsersDatabase database;
    private static final int RESULT_LOAD_IMAGE = 1;
    private int STORAGE_PERMISSION_CODE = 2;
    private int WRITE_PERMISSION_CODE = 3;
    ImageView userImg;


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
        userImg = findViewById(R.id.profile_photo);

        //getting username
        Intent UsernameIntent = getIntent();
        getUsername = UsernameIntent.getStringExtra("Username");

        userId = getIntent().getIntExtra("userId",0);

        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            if (user.getId() == userId) {
                username.setText(user.getUsername());
                email.setText(user.getEmail());
                username_info.setText(user.getUsername());
                email_info.setText(user.getEmail());
                totalFavorites.setText(user.getFavorite().size()+"");
            }
        }

        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UserProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(UserProfile.this, "You have already granted this permission!", Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }
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
    private void pickAnImg(){
        Intent uploadUserPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(uploadUserPhoto, RESULT_LOAD_IMAGE);
    }

    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){

            new AlertDialog.Builder(this)
            .setTitle("Permission needed")
            .setMessage("This permission is needed so you can upload your own photo to the profile. Do you want to grand permission?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(UserProfile.this, new String []  {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss(); //closes dialogue
                }
            })
            .create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String []  {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickAnImg();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
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
