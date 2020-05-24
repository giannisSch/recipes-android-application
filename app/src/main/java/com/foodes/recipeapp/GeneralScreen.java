package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneralScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_screen);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Screen Buttons ( Login & Create Account)
        Button loginButton = findViewById(R.id.login_btn);
        Button registerButton = findViewById(R.id.create_acc_btn);

        //Click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginScreen();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterScreen();
            }
        });

    }

    //Login & Register methods to change screens according to user preference
    private void goToLoginScreen() {
        Intent login = new Intent(GeneralScreen.this,Login.class);
        startActivity(login);
    }

    private void goToRegisterScreen(){
        Intent register = new Intent(GeneralScreen.this,Register.class);
        startActivity(register);
    }
}
