package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

    private void checkIfMailIsCorrect(){
        List<User> users = database.getUserDao().getAll();
        for (User user : users) {
            String email = user.getEmail();
            if (email.equals(submittedEmail)){
                getUserInfo();
            }
        }
    }

    private void getUserInfo(){
        try {
            List<User> users = database.getUserDao().getAll();
            for (User user : users) {
                String email = user.getEmail();
                if (email.equals(submittedEmail)){
                    user.setPassword(NewPassword);
                    database.getUserDao().update(user);
                    moveToLoginScreen();
                }
            }
        } catch (Exception e) {
            //
        }
    }



    private void moveToLoginScreen(){
        Intent move = new Intent(ForgotPasswordStepTwo.this, LoginActivity.class);
        startActivity(move);
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
