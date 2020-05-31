package com.foodes.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username_txt;
    private EditText password_txt;
    private Button login_main_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        //login button & Listener
        Button loginBtn = findViewById(R.id.loginLoginButton);
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
        Intent userIsLoggedIn = new Intent(LoginActivity.this, AppHomeScreen.class);
        startActivity(userIsLoggedIn);
    }

    private void displayError(){
        //create method
    }

}



