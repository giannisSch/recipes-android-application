package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ForgotPassword extends AppCompatActivity {

    private TextInputEditText getEmail;
    private String checkEmail;
    Button submitBtn;
    UsersDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //getting db instance
        database = UsersDatabase.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        getEmail = findViewById(R.id.forgotPass_emailField);

        submitBtn = findViewById(R.id.forgotPass_submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmail = getEmail.getText().toString();
                if (emailIsCorrect()){
                    proceedToChangePasswordScreen();
                } else {
                    displayError();
                }
            }
        });

    }

    private boolean emailIsCorrect() {
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String email = user.getEmail();
            if (checkEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    private void proceedToChangePasswordScreen(){
        Intent proceedToStepTwo = new Intent(ForgotPassword.this, ForgotPasswordStepTwo.class);
        proceedToStepTwo.putExtra("Email", checkEmail);
        startActivity(proceedToStepTwo);
    }

    private void displayError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter valid email";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
