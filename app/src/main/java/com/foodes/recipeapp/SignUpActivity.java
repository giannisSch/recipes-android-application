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

public class SignUpActivity extends AppCompatActivity {

    //Buttons
    private EditText reg_username;
    private EditText reg_password;
    private EditText reg_email;
    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        ImageButton backButton = findViewById(R.id.signUpBackImageButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.super.onBackPressed();
            }
        });

        //Declaring Back Button
        ImageView back_btn = findViewById(R.id.signUpBackImageButton);

        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        });

        //Declaring the Username, Email & Password EditText
        reg_username = findViewById(R.id.signUpUsernameTextField);
        reg_email = findViewById(R.id.signUpPasswordTextField);
        reg_password = findViewById(R.id.signUpEmailTextField);

        //find a way to register a user
        // database

        //Create Account Btn
        Button createAccBtn = findViewById(R.id.signUpCreateAccountButton);
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //trying to authenticate what the user has just entered
//                if (UserIsAuthenticated()){
                register();
//                } else {
//                    displayErrorMessage();
//                }
            }
        });
    }

    //Authenticate method
    private boolean UserIsAuthenticated(){
        //create method
        return false;
    }

    //Register method
    private void register(){
        //create method
        Intent regUser = new Intent(SignUpActivity.this, AccountCreated.class);
        startActivity(regUser);
    }
    //Notifies the user that there is an error on what he has just entered
    private void displayErrorMessage(){
        //create method
    }

}
