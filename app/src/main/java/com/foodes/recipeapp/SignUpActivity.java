package com.foodes.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDao;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText  getUsername, getEmail, getPassword, getPasswordConfirm;
    private String username, email,password,passwordConfirm;
    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //locating txtFields
        getUsername = findViewById(R.id.signUpUsernameTxt);
        getEmail = findViewById(R.id.signUpEmailTxt);
        getPassword = findViewById(R.id.signUpPasswordTxt);
        getPasswordConfirm = findViewById(R.id.signUpPasswordConfTxt);
        createAccountBtn = findViewById(R.id.signUpCreateAccountButton);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting values from EditTexts
                username = getUsername.getText().toString();
                email = getEmail.getText().toString();
                password = getPassword.getText().toString();
                passwordConfirm = getPasswordConfirm.getText().toString();
                //Checks if passwords are the same | If yes the user proceeds
                checkIfPasswordsMatch();
            }
        });
    }

    private void checkIfPasswordsMatch(){
        if (password.equals(passwordConfirm)) {
            register();
        } else {
            showToast();
        }
    }

    //Register method
    private void register(){
        Intent regUser = new Intent(SignUpActivity.this, AccountCreated.class);
        startActivity(regUser);
    }

    private void showToast(){
        Context context = getApplicationContext();
        CharSequence text = "Please enter valid credentials";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
