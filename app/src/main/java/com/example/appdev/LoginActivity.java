package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton, signupButton;

    ArrayList<User> userList;

    Account accountsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);





        String string = serializeObjectToString(new Item("1", 3, 2.0, 4, new ArrayList<>(), new ArrayList<>()));
        System.out.println(string + "lo");

        Item ite = (Item) deserializeObjectToString(string);
        System.out.println(ite.getTitle() +  ite.getTimesSold() + "lo");



        /////////////////



        //declarationn
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayList<Item> cartList = new ArrayList<>();
        ArrayList<Item> orderList = new ArrayList<>();
        ArrayList<Item> feedbackList = new ArrayList<>();

        Gson gson = new Gson();
        String cartListString = gson.toJson(cartList);
        String trackOrderListString = gson.toJson(orderList);
        String feedbackListString = gson.toJson(feedbackList);


        ArrayList<User> newUser = new ArrayList<>();
        newUser.add(new User("jam", "09150000000", "cebu", "gmail", "kol", "kol", cartListString, trackOrderListString, feedbackListString));


        //extract the previous
        String extractedAccountListStringBeg = sharedPreferences.getString("accounts", "Default");
        Account extractedAccountListBeg = gson.fromJson(extractedAccountListStringBeg, Account.class);

        //System.out.println(extractedAccountListBeg.getUserList().size() + "gggggggggggggggg" + extractedAccountListBeg.getUserList().get(0).getCartItemList());


        if(extractedAccountListBeg==null)
            accountsList = new Account(newUser);
        else
            accountsList = extractedAccountListBeg;



        //inserting
        String accountsListString;
        accountsListString = gson.toJson(accountsList);

        editor.putString("accounts", accountsListString);
        editor.apply();


        //making the default value
        String jsonUserListString = gson.toJson(accountsList);


        //extract for user matching
        String extractedAccountListString = sharedPreferences.getString("accounts", "Default");
        Account extractedAccountList = gson.fromJson(extractedAccountListString, Account.class);




        ///////////////////////



        ImageView imageViewShowHidePwd = findViewById(R.id.hide_password);

        imageViewShowHidePwd.setImageResource(R.drawable.hide_password);
        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordEditText.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.hide_password);
                } else {
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.showpassword);
                }

                // Move the cursor to the end of the text after changing the transformation method
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });



        ////////////////////////////




        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String message = "No User Found";
                int match = 0;

                ArrayList<User> users = extractedAccountList.getUserList();

                for(int i=0; i < users.size(); i++){
                    User user = users.get(i);

                    System.out.println(users.get(i).getCartItemList() + "ppppppppppppppp");
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                        message = "Login Successful!";
                        match = 1;


                        //inserting to the sharedPref
                        String currentUserString;
                        currentUserString = gson.toJson(user);

                        editor.putString("currentUser", currentUserString);
                        editor.putInt("currentUserPosition", i);
                        editor.apply();



                    }
                    else if(user.getUsername().equals(username) && !user.getPassword().equals(password)){
                        message = "Incorrect Password";
                    }
                }

                if(match==1){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();







            }
        });




        // Set click listener for the signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Signup Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }






    ///////////////////////////////




    public String serializeObjectToString(Serializable userList) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(userList);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            byte[] serializedObjectBytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(serializedObjectBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object deserializeObjectToString(String serializedString) {
        try {
            byte[] serializedObjectBytes = Base64.decode(serializedString, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObjectBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}