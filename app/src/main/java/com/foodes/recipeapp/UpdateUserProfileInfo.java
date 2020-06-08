package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateUserProfileInfo extends AppCompatActivity {

    private TextInputEditText username,email,pass,passConf;
    private String Username,newUsername, newEmail, newPass, newPassConf;
    Button updateBtn;
    UsersDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile_info);
        //getting db instance
        database = UsersDatabase.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //getting username
        Intent UsernameIntent = getIntent();
        Username = UsernameIntent.getStringExtra("Username");

        //getting fields
        username = findViewById(R.id.update_username_txt);
        email = findViewById(R.id.update_email_txt);
        pass = findViewById(R.id.update_pass_txt);
        passConf = findViewById(R.id.update_passConf_txt);
        updateBtn = findViewById(R.id.updateInfo_btn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting values from fields
                newUsername = username.getText().toString();
                newEmail  = email.getText().toString();
                newPass = pass.getText().toString();
                newPassConf = passConf.getText().toString();

                //checking if values are correct
                checkIfPasswordsMatch();
            }
        });
    }

    private void checkIfPasswordsMatch(){

        if (newPass.equals(newPassConf)) {
            checkIfValuesAreCorrect();
        }
    }

    private void checkIfValuesAreCorrect(){
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String name = user.getUsername();
            if (Username.equals(name)) {
                updateUserProfileInfo();
            }
        }
    }

    private void updateUserProfileInfo(){
            User user = new User(newUsername, newEmail, newPass);
            database.getUserDao().update(user);
        }

}
