package com.foodes.recipeapp;

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

    private TextInputLayout  GetUsername, GetEmail, GetPassword, GetPasswordConfirm;
    private Button createAccountBtn;
    private UsersDatabase db;
    private UsersDao Usersdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        GetUsername = findViewById(R.id.signUpUsernameTextField);
        GetEmail = findViewById(R.id.signUpEmailTextField);
        GetPassword = findViewById(R.id.signUpPasswordTextField);
        GetPasswordConfirm = findViewById(R.id.signUpPasswordConfigTextField);
        createAccountBtn = findViewById(R.id.signUpCreateAccountButton);

        String username = GetUsername.getEditText().getText().toString().trim();
        String email = GetEmail.getEditText().getText().toString().trim();
        String password = GetPassword.getEditText().getText().toString().trim();
        String passwordConfirm = GetPasswordConfirm.getEditText().getText().toString().trim();

        //does not allow null text
        if (username == null && email == null && password == null && passwordConfirm == null){
            createAccountBtn.setEnabled(false);
            //change to blue color
        }else{
            createAccountBtn.setEnabled(true);
        }

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createAccountBtn.isEnabled()){
                    register();
                }
            }
        });

    }

    //Register method
    private void register(){
        Intent regUser = new Intent(SignUpActivity.this, AccountCreated.class);
        startActivity(regUser);
    }
}
