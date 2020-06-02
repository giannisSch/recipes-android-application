package com.foodes.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText getUsername, getPassword;
    private String username, password;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        //getting access on editTxts & Buttons
        getUsername = findViewById(R.id.loginUsernameTest);
        getPassword = findViewById(R.id.loginPasswordTest);
        loginBtn = findViewById(R.id.loginLoginButton);

        //getting values from EditTexts


        //login btn listener
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                username = getUsername.getText().toString();
                password = getPassword.getText().toString();

                if (username.equals("admin") && password.equals("1234")){
                    loginUser();
                } else {
                    displayError();
                }
            }
        });
    }

    private void loginUser(){
        //create method
        Intent userIsLoggedIn = new Intent(LoginActivity.this, RecipesMenuActivity.class);
        startActivity(userIsLoggedIn);
    }

    private void displayError(){
        Context context = getApplicationContext();
        CharSequence text = "Please enter valid credentials";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}



