package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    private EditText username_txt;
    private EditText password_txt;
    private Button login_main_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Declaring Back Button
        ImageView back_btn = findViewById(R.id.back_button);

        //Listener for back button
        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(Login.this, GeneralScreen.class);
                startActivity(goBack);
            }
        });

        //login button & Listener
        Button loginBtn = findViewById(R.id.login_main_btn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                if (UserIsAuthenticated()){
                    loginUser();
//                }else{
//                    displayError();
//                }
            }
        });
    }

    private boolean UserIsAuthenticated(){
        //create method
        return false;
    }

    private void loginUser(){
        //create method
        Intent userIsLoggedIn = new Intent(Login.this, AppHomeScreen.class);
        startActivity(userIsLoggedIn);
    }

    private void displayError(){
        //create method
    }

}
