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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText getUsername, getEmail, getPassword;
    private String username, email, password;
    String checkUsername, checkPassword;
    private Button loginBtn, loginForgotPasswordButton;
    UsersDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getting db instance
        database = UsersDatabase.getInstance(this);
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //getting access on editTxts & Buttons
        getUsername = findViewById(R.id.loginUsernameTest);
        getPassword = findViewById(R.id.loginPasswordTest);
        loginBtn = findViewById(R.id.loginLoginButton);
        loginForgotPasswordButton = findViewById(R.id.loginForgotPasswordButton);

        //login btn listener
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername = getUsername.getText().toString();
                checkPassword = getPassword.getText().toString();

                //trying to authenticate the user
                if (authenticateUser()) {
                    loginUser();
                } else {
                    displayError();
                }
            }
        });

        //forgot password listenter
        loginForgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword();
            }
        });
    }

    private boolean authenticateUser() {
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String name = user.getUsername();
            String code = user.getPassword();
            if (checkUsername.equals(name) && checkPassword.equals(code)) {
                return true;
            }
        }
        return false;
    }

    private void loginUser() {
        //create method
        Intent userIsLoggedIn = new Intent(LoginActivity.this, SearchActivity.class);
        //pass username to AppHomeScreen so we can greet the user in home screen
        userIsLoggedIn.putExtra("Username", checkUsername);
        startActivity(userIsLoggedIn);
    }

    private void ForgotPassword() {
        Intent forgotPass = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(forgotPass);
    }

    private void successSnackbar() {
        Toast snackbar = FancyToast.makeText(this, "Success", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false);
        snackbar.show();
    }

    private void errorSnackbar() {
        Toast snackbar = FancyToast.makeText(this, "Please enter valid credentials", FancyToast.LENGTH_LONG, FancyToast.ERROR, false);
        snackbar.show();
    }

    private void displayError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter valid credentials";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}



