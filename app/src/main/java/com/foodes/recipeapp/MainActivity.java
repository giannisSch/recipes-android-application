package com.foodes.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Button loginButton = findViewById(R.id.mainLoginButton);
        Button createAnAccountButton = findViewById(R.id.mainCreateAnAccountButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(LoginActivity.class);
            }
        });
        createAnAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(SignUpActivity.class);
            }
        });
    }
    private void gotoActivity(Class activityName){
        Intent intent = new Intent(MainActivity.this, activityName);
        startActivity(intent);
    }

}
