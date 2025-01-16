package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {
    Button signUpButtonSignup;
    EditText nameEditText, emailEditText, numberEditText, locationEditText, usernameEditTextSignup, passwordEditText1, passwordEditText2;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\\.[a-z]+";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    User extractedCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        /////////////////


        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for list of sccounts
        String accountsString = sharedPreferences.getString("accounts", "Default");
        Account accountList = gson.fromJson(accountsString, Account.class);

        ArrayList<User> accountUserList = accountList.getUserList();




        ////////////////



        signUpButtonSignup = findViewById(R.id.signUpButton);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        numberEditText = findViewById(R.id.numberEditText);
        locationEditText = findViewById(R.id.LocationEditText);
        usernameEditTextSignup = findViewById(R.id.usernameEditTextSignup);
        passwordEditText1 = findViewById(R.id.passwordEditText1);
        passwordEditText2 = findViewById(R.id.passwordEditText2);





        signUpButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName = nameEditText.getText().toString().trim();
                String txtEmail = emailEditText.getText().toString().trim();
                String txtNumber = numberEditText.getText().toString().trim();
                String txtLocation = locationEditText.getText().toString().trim();
                String txtUsername = usernameEditTextSignup.getText().toString().trim();
                String txtPassword = passwordEditText1.getText().toString().trim();
                String txtConfirmPassword = passwordEditText2.getText().toString().trim();

                if (TextUtils.isEmpty(txtName)) {
                    nameEditText.setError("Full Name is required");
                    nameEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(txtEmail)) {
                    emailEditText.setError("Email is required");
                    emailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
                    emailEditText.setError("Enter a valid Email Address");
                    emailEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(txtNumber)) {
                    numberEditText.setError("Mobile number is required");
                    numberEditText.requestFocus();
                    return;
                }

                if (txtNumber.length() != 11) {
                    numberEditText.setError("Mobile number should be 11 digits");
                    numberEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(txtLocation)) {
                    locationEditText.setError("Location is required");
                    locationEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(txtUsername)) {
                    usernameEditTextSignup.setError("Username is required");
                    usernameEditTextSignup.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(txtPassword)) {
                    passwordEditText1.setError("Password is required");
                    passwordEditText1.requestFocus();
                    return;
                }

                if (txtPassword.length() < 6) {
                    passwordEditText1.setError("Password should be at least 6 characters");
                    passwordEditText1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(txtConfirmPassword)) {
                    passwordEditText2.setError("Password confirmation is required");
                    passwordEditText2.requestFocus();
                    return;
                }

                if (!txtPassword.equals(txtConfirmPassword)) {
                    passwordEditText2.setError("Passwords do not match");
                    passwordEditText2.requestFocus();
                    passwordEditText1.setText("");
                    return;
                }


//                signUpUser(txtEmail, txtPassword);


                ////////////////////



                ArrayList<Item> cartList = new ArrayList<>();
                ArrayList<Item> orderList = new ArrayList<>();
                ArrayList<Item> feedbackList = new ArrayList<>();

                Gson gson = new Gson();
                String cartListString = gson.toJson(cartList);
                String trackOrderListString = gson.toJson(orderList);
                String feedbackListString = gson.toJson(feedbackList);


                accountUserList.add(new User(txtName, txtNumber, txtLocation, txtEmail, txtUsername, txtPassword,  cartListString, trackOrderListString, feedbackListString));

                accountList.setUserList(accountUserList);

                String accountsListString = gson.toJson(accountList);
                editor.putString("accounts", accountsListString);
                editor.apply();



                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signUpUser(String email, String password) {

    }
}
