package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class MyBagActivity extends AppCompatActivity implements View.OnClickListener {


    Button backButton;
    TextView cartItemNav, trackOrderItemNav, feedbackItemNav;

    ArrayList<Item> pendingValuesList;

    SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

       Intent intent = getIntent();
       pendingValuesList = (ArrayList<Item>) intent.getSerializableExtra("pendingValuesList");

       sharedViewModel.setItems(pendingValuesList);

        if(pendingValuesList==null)
            System.out.println("hllllllllllllllllyyyyyyoo");

        backButton = findViewById(R.id.backButton2);
        backButton.setOnClickListener(new GoBackClickListener());

        cartItemNav = findViewById(R.id.cartItemNav);
        cartItemNav.setOnClickListener(this);

        trackOrderItemNav = findViewById(R.id.trackOrderItemNav);
        trackOrderItemNav.setOnClickListener(this);

        feedbackItemNav = findViewById(R.id.feedbackItemNav);
        feedbackItemNav.setOnClickListener(this);


        sendPendingValuesList(new CartItemFragment());

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CartItemFragment()).commit();
    }


    private void sendPendingValuesList(Fragment selectedFragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pendingValuesList", pendingValuesList);

        selectedFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containerInside, selectedFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {



        Fragment selectedFragment = new CartItemFragment(); //default fragment to show

        if(view.getId()==R.id.trackOrderItemNav){
            Intent intent = new Intent(getApplicationContext(), TrackOrderItemPreviewActivity.class);
            startActivity(intent);
            return;
        }

        else if(view.getId()==R.id.feedbackItemNav){
            selectedFragment = new FeedbackItemFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerInside, selectedFragment).commit();

    }

    private class GoBackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(MyBagActivity.this, MainActivity.class);
            intent.putExtra("pendingValuesList", pendingValuesList);
            startActivity(intent);

        }
    }
}
