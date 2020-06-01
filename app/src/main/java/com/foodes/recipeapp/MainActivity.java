package com.foodes.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        Button login = findViewById(R.id.mainLoginButton);
        Button createAnAccount = findViewById(R.id.mainCreateAnAccountButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginActivity(); //goes to login activity
            }
        });
        createAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterActivity(); //goes to register activity
            }
        });
    }

    private void gotoLoginActivity(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void gotoRegisterActivity(){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

}