package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ForgotPasswordStepTwo extends AppCompatActivity {

    TextInputEditText newPass, ConfirmNewPass;
    String fPass, fPassConfirm, submittedEmail, NewPassword, UserUsername, UserPassword;
    Button Submit;
    UsersDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_step_two);
        //getting db instance
        database = UsersDatabase.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);

            Intent getSubmittedEmail = getIntent();
            submittedEmail = getSubmittedEmail.getStringExtra("Email");

            newPass = findViewById(R.id.newPassword);
            ConfirmNewPass = findViewById(R.id.newPasswordConf);


        Submit = findViewById(R.id.forgotPass_UpdateLoginInfoBtn);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fPass = newPass.getText().toString();
                fPassConfirm = ConfirmNewPass.getText().toString();

                checkIfPasswordsMatch();
            }
        });
    }

    private void checkIfPasswordsMatch(){

        if (fPass.equals(fPassConfirm)) {
            NewPassword = fPass;
            askIfUserIsSure();
        }
    }

    private void askIfUserIsSure(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ForgotPasswordStepTwo.this);
        builder.setTitle("Reset password");
        builder.setMessage("Are you sure that you want to reset the password of your account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkIfMailIsCorrect();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        builder.show();
    }

    private boolean checkIfMailIsCorrect(){
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String email = user.getEmail();
            if (email.equals(submittedEmail)){
                getUserInfo();
            }
        }
        return false;
    }

    private void getUserInfo(){
        try {
            List<User> users = database.getUserDao().getAll();
            for (User user : users) {
                String email = user.getEmail();
                if (email.equals(submittedEmail)){
                    UserUsername = user.getUsername();
                    UserPassword = user.getPassword();
                    updateUserInfo();
                }
            }
        } catch (Exception e) {
            //
        }
    }

    private void updateUserInfo(){
        User user = new User(UserUsername, submittedEmail, NewPassword);
        database.getUserDao().update(user);
        Toast.makeText(this, "Your password has been changed successfully", Toast.LENGTH_SHORT).show();
        moveToLoginScreen();
    }

    private void moveToLoginScreen(){
        Intent move = new Intent(ForgotPasswordStepTwo.this, LoginActivity.class);
        startActivity(move);
    }

}
