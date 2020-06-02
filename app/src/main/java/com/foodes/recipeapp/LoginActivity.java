package com.foodes.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDao;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText getUsername, getEmail, getPassword;
    private String username, email,  password;
    String checkUsername, checkPassword;
    private Button loginBtn;
    UsersDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getting db instance
        database = UsersDatabase.getInstance(this);
        database.getUserDao().getAll();
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //getting access on editTxts & Buttons
        getUsername = findViewById(R.id.loginUsernameTest);
        getPassword = findViewById(R.id.loginPasswordTest);
        loginBtn = findViewById(R.id.loginLoginButton);


        //login btn listener
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername = getUsername.getText().toString();
                checkPassword = getPassword.getText().toString();

//                if (username.equals("admin") && password.equals("1234")) {
//                    loginUser();
//                } else {
//                    displayError();
//                }
                authenticateUser();
            }
        });
    }

    private void authenticateUser() {
       if (checkUsername.equals(username) && checkPassword.equals(password)){
           loginUser();
       }
    }

    private void loginUser() {
        //create method
        Intent userIsLoggedIn = new Intent(LoginActivity.this, AppHomeScreen.class);
        //pass username to AppHomeScreen so we can greet the user in home screen
        userIsLoggedIn.putExtra("Username", username);
        startActivity(userIsLoggedIn);
    }

    private void displayError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter valid credentials";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}



