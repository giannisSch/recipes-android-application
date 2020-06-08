package com.foodes.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
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

import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDao;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText  getUsername, getEmail, getPassword, getPasswordConfirm;
    private String username, email,password,passwordConfirm;
    private Button createAccountBtn;
    UsersDatabase database;
    List<Favorites> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //create instance db
        database = UsersDatabase.getInstance(this);
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //locating txtFields
        getUsername = findViewById(R.id.signUpUsernameTxt);
        getEmail = findViewById(R.id.signUpEmailTxt);
        getPassword = findViewById(R.id.signUpPasswordTxt);
        getPasswordConfirm = findViewById(R.id.signUpPasswordConfTxt);
        //register btn
        createAccountBtn = findViewById(R.id.signUpCreateAccountButton);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesToString();
                checkIfPasswordsMatch();
            }
        });
        favorites = new ArrayList<>();
    }

    private void checkIfPasswordsMatch(){
        if (password.equals(passwordConfirm)) {
            checkIfUsernameExists();
        } else {
            showErrorToast();
        }
    }

    private void getValuesToString(){
        //getting values from EditTexts
        username = getUsername.getText().toString();
        email = getEmail.getText().toString();
        password = getPassword.getText().toString();
        passwordConfirm = getPasswordConfirm.getText().toString();
    }

    //Register method
    private void register(){
        //creates new user in db & moves to new activity
        try{
            User user = new User(username, password, email);  //creates new user with username,password,email parameters
            database.getUserDao().insert(user); // **VM
        } catch (Exception e) {
            //douleiesMeFoodes
        }

        showToast(); //informs the user that his account has been created
        Intent regUser = new Intent(SignUpActivity.this, AccountCreated.class);
        startActivity(regUser);
    }

    private void checkIfUsernameExists() {
        boolean thisUserExists = false;
        List<User> users = database.getUserDao().getAll();
        if (users.size() == 0) {
            register();
        } else {
            for (User user : users) {
                String checkName = user.getUsername();
                if (username.equals(checkName)) {
                    thisUserExists = true;
                    break;
                }
            }
            if(thisUserExists){
                userExists();
            }else{
                register();
            }
        }
    }

    private void userExists(){
        Context context = getApplicationContext();
        CharSequence text = "This username exists";
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

    private void showToast(){
        Context context = getApplicationContext();
        CharSequence text = "Your account has been created!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showErrorToast(){
        Context context = getApplicationContext();
        CharSequence text = "Please enter valid credentials";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
