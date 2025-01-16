package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FeedbackItemFragment extends Fragment implements FeedbackItemAdapter.OnButtonClickListener {

    FeedbackItemAdapter feedbackItemAdapter;

    RecyclerView recyclerView;

    View rootView;

    SharedViewModel sharedViewModel;

    ArrayList<Item> pendingValuesList;

    ArrayList<CartItem> cartItemsList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    User currentUser;
    User extractedCurrentUser;

    ArrayList<Item> feedbackItemList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_feedback_item, container,false);


//
//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//
//        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
//
//            if (items != null ) {
//                // Perform actions with the received ArrayList<Item>
//                System.out.println("oooppppppppppkkkkkkkk" + items.toString());
//            } else {
//                // Handle case where ArrayList<Item> is null
//                System.out.println("ooooooooooooookkkkkkkkkkkkkkkk");
//            }
//        });



        ///////////////////






        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

        Toast.makeText(getActivity(), extractedCurrentUser.getUsername() + "  " , Toast.LENGTH_SHORT).show();


        //getting the cartItemList Array
        Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
        feedbackItemList = gson.fromJson(extractedCurrentUser.getFeedbackItemList(), listType);






        ////////////////////////


//        cartItemsList = new ArrayList<>();
//        cartItemsList.add(convertItemToCartItem(new ApparelItem().getFootwearApparelList().get(1)));
//        cartItemsList.add(convertItemToCartItem(new ApparelItem().getFootwearApparelList().get(3)));
//        cartItemsList.add(convertItemToCartItem(new ApparelItem().getFootwearApparelList().get(5)));

//
//        Bundle bundle = this.getArguments();
//
//        if(bundle != null){
//            // handle your code here.
//            pendingValuesList = (ArrayList<Item>) bundle.getSerializable("pendingValuesList");
//            System.out.println("hooooooooooooooo");
//        }
//        else{
//            System.out.println("hoppppppppppppppppooooo");
//            Intent intent = new Intent();
//            pendingValuesList = (ArrayList<Item>) intent.getSerializableExtra("pendingValuesList");
//        }
//
//        if(pendingValuesList!=null){
//            System.out.println("hoolllllllllllllllllloooo" + pendingValuesList.toString());
//            for(int i = 0; i < pendingValuesList.size(); i++){
//                int image = pendingValuesList.get(i).getImage();
//                String title = pendingValuesList.get(i).getTitle();
//                int price = pendingValuesList.get(i).getPrice();
//
//                cartItemsList.add(new CartItem(image, title, price));
//            }
//        }
//        else
//            System.out.println("nnnnnnnnnnnnnnoooooo");
//




        recyclerView = rootView.findViewById(R.id.feedbackRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        feedbackItemAdapter = new FeedbackItemAdapter(feedbackItemList, this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(feedbackItemAdapter);

        return rootView;
    }



    public CartItem convertItemToCartItem(Item item){

        int image = item.getImage();
        String title = item.getTitle();
        int price = item.getPrice();

        return new CartItem(image, title, price);
    }

    @Override
    public void onButtonClick(int pos, Button button, Button button2, Button button3, Button button4, Button button5) {

        feedbackItemList.remove(pos);
        feedbackItemAdapter.notifyDataSetChanged();


        Toast.makeText(getContext(), "Item rating saved!", Toast.LENGTH_SHORT).show();
        button.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
        button2.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
        button3.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
        button4.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
        button5.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));





        ///////////////////////



        Gson gson = new Gson();

        /// the update of feedbackItem as it clicks receiveButton, it adds

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




    }

    @Override
    public void onButtonClick(int pos, Button buttonClicked, Button button, Button button2, Button button3, Button button4, Button button5) {

        String message = null;

        int five = R.drawable.baseline_star_outline_24;
        int four = R.drawable.baseline_star_outline_24;
        int three = R.drawable.baseline_star_outline_24;
        int two = R.drawable.baseline_star_outline_24;
        int one = R.drawable.baseline_star_outline_24;

        if(buttonClicked==button){
            message = "Item is rated 5 star";
            five = R.drawable.baseline_star_24;
            four = R.drawable.baseline_star_24;
            three = R.drawable.baseline_star_24;
            two = R.drawable.baseline_star_24;
            one = R.drawable.baseline_star_24;
        }
        else if(buttonClicked==button2){
            message = "Item is rated 4 star";
            four = R.drawable.baseline_star_24;
            three = R.drawable.baseline_star_24;
            two = R.drawable.baseline_star_24;
            one = R.drawable.baseline_star_24;
        }
        else if(buttonClicked==button3){
            message = "Item is rated 3 star";
            three = R.drawable.baseline_star_24;
            two = R.drawable.baseline_star_24;
            one = R.drawable.baseline_star_24;
        }
        else if(buttonClicked==button4){
            message = "Item is rated 2 star";
            two = R.drawable.baseline_star_24;
            one = R.drawable.baseline_star_24;
        }
        else if(buttonClicked==button5){
            message = "Item is rated 1 star";
            one = R.drawable.baseline_star_24;

        }


        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        button.setBackground(button.getContext().getResources().getDrawable(five));
        button2.setBackground(button.getContext().getResources().getDrawable(four));
        button3.setBackground(button.getContext().getResources().getDrawable(three));
        button4.setBackground(button.getContext().getResources().getDrawable(two));
        button5.setBackground(button.getContext().getResources().getDrawable(one));

    }
}
