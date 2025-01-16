package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class TrackOrderItemPreviewActivity extends AppCompatActivity {


    ImageView image;

    TextView titleTextView;
    TextView amountTextView;
    Button doneButton, receiveButton;

    Item clickedItem;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    User currentUser;
    User extractedCurrentUser;

    ArrayList<Item> trackOrderItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order_item_preview);

        image = findViewById(R.id.cartItemImageView);
        titleTextView = findViewById(R.id.itemNameTextView);
        amountTextView = findViewById(R.id.amountTextView);

        receiveButton = findViewById(R.id.receiveButton);
        receiveButton.setOnClickListener(new ReceiveClickListener());

        doneButton = findViewById(R.id.btnDone);
        doneButton.setOnClickListener(new GoBackClickListener());

        Intent intent = getIntent();
        clickedItem = (Item) intent.getSerializableExtra("clickedApparelItem");


        image.setImageResource(clickedItem.getImage());
        titleTextView.setText(clickedItem.getTitle());
        amountTextView.setText("Amount: $" + (clickedItem.getPrice() * clickedItem.getTimesSold()));




        ///////////////////



        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

        Toast.makeText(this, extractedCurrentUser.getUsername() + "  " , Toast.LENGTH_SHORT).show();


        //getting the cartItemList Array
        Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
        trackOrderItemList = gson.fromJson(extractedCurrentUser.getTrackOrderItemList(), listType);





        ////////////////////////


    }


    private class GoBackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    }

    private class ReceiveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Gson gson = new Gson();
            /// the update of trackOrder as it places order it adds

            //get the values of trackOrder first
            //getting the trackOrderlist Array
//            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
//            trackOrderItemList = gson.fromJson(extractedCurrentUser.getTrackOrderItemList(), listType);

            int pos = sharedPreferences.getInt("currentClickedItemPosition", -1);
            trackOrderItemList.remove(pos);


            String trackOrderItemListString = gson.toJson(trackOrderItemList);


            //setting the current user cartlistString to new cartListString
            extractedCurrentUser.setTrackOrderItemList(trackOrderItemListString);




            /// the update of feedbackItem as it clicks receiveButton, it adds
            //get the values of trackOrder first
            //getting the trackOrderlist Array
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> feedbackItemList = gson.fromJson(extractedCurrentUser.getFeedbackItemList(), listType);


            feedbackItemList.add(clickedItem);

            String feedbackItemListString = gson.toJson(feedbackItemList);


            //setting the current user cartlistString to new cartListString
            extractedCurrentUser.setFeedbackItemList(feedbackItemListString);



            //setting the current user to string
            String currentUserString = gson.toJson(extractedCurrentUser);
            editor.putString("currentUser", currentUserString);
            editor.apply();







            //////updating the currentUser in the arraylist of accounts

            //extracting for list of sccounts
            String accountsString = sharedPreferences.getString("accounts", "Default");
            Account accountList = gson.fromJson(accountsString, Account.class);

            ArrayList<User> accountUserList = accountList.getUserList();

            for(int i = 0; i < accountUserList.size(); i++){
                System.out.println(accountUserList.get(i).getUsername() + "!!!!!!!");
            }


            int userPos = sharedPreferences.getInt("currentUserPosition", -1);

            System.out.println(userPos + " " + accountUserList.get(userPos).getUsername() + "yyyyyyyyyyyyyyyyyyyyyyyyyyy");

            accountUserList.set(userPos, extractedCurrentUser);


            accountList.setUserList(accountUserList);


            String accountsListString = gson.toJson(accountList);
            editor.putString("accounts", accountsListString);
            editor.apply();




            ///////////////

            onBackPressed();





        }
    }
}