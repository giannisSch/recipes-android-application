package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Register extends AppCompatActivity {

    private EditText reg_username;
    private EditText reg_password;
    private EditText reg_email;
    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Declaring Back Button
        ImageView back_btn = findViewById(R.id.back_button);

        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(Register.this, GeneralScreen.class);
                startActivity(goBack);
            }
        });

        //Declaring the Username, Email & Password EditText
        reg_username = findViewById(R.id.reg_username_txt);
        reg_email = findViewById(R.id.reg_email_txt);
        reg_password = findViewById(R.id.reg_password_txt);

        //Create Account Btn

        Button createAccBtn = findViewById(R.id.reg_main_btn);
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
        Intent regUser = new Intent(Register.this, AccountCreated.class);
        startActivity(regUser);
    }
    //Notifies the user that there is an error on what he has just entered
    private void displayErrorMessage(){
    //create method
    }

}
