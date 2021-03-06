package com.foodes.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText getUsername, getEmail, getPassword;
    private String username, email, password;
    String checkUsername, checkPassword;
    private Button loginBtn, loginForgotPasswordButton;
    UsersDatabase database;
    private TextInputLayout usernameLayout, passwordLayout;
    private int userId;

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

        usernameLayout = findViewById(R.id.loginUsernameTextField);
        passwordLayout = findViewById(R.id.loginPasswordTextField);

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

        //forgot password listener
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
                userId = user.getId();
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
        userIsLoggedIn.putExtra("Password", checkPassword);
        userIsLoggedIn.putExtra("userId", userId);
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

        //Vibrate Phone
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

}



