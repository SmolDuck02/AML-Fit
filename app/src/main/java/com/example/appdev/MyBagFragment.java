package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class MyBagFragment extends Fragment implements View.OnClickListener {


    Button backButton;
    TextView cartItemNav, trackOrderItemNav, feedbackItemNav;

    ArrayList<Item> pendingValuesList;

    SharedViewModel sharedViewModel;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_my_bag, container, false);


        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

//       Intent intent = getIntent();
//       pendingValuesList = (ArrayList<Item>) intent.getSerializableExtra("pendingValuesList");


//       sharedViewModel.setItems(pendingValuesList);

        pendingValuesList = new ArrayList<>();
        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            if (items != null ) {
                pendingValuesList = items;
            } else {
                pendingValuesList = new ArrayList<>();
            }
        });


        if(pendingValuesList==null)
            System.out.println("hllllllllllllllllyyyyyyoo");

//        backButton = rootView.findViewById(R.id.backButton2);
//        backButton.setOnClickListener(new GoBackClickListener());

        cartItemNav = rootView.findViewById(R.id.cartItemNav);
        cartItemNav.setOnClickListener(this);

        trackOrderItemNav = rootView.findViewById(R.id.trackOrderItemNav);
        trackOrderItemNav.setOnClickListener(this);

        feedbackItemNav = rootView.findViewById(R.id.feedbackItemNav);
        feedbackItemNav.setOnClickListener(this);


        sendPendingValuesList(new CartItemFragment());


        return rootView;
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CartItemFragment()).commit();
    }


    private void sendPendingValuesList(Fragment selectedFragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pendingValuesList", pendingValuesList);

        selectedFragment.setArguments(bundle);


        //changed to getChild
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containerInside, selectedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {



        Fragment selectedFragment = new CartItemFragment(); //default fragment to show

        if(view.getId()==R.id.trackOrderItemNav){
            selectedFragment = new TrackOrderIItemFragment();
        }

        else if(view.getId()==R.id.feedbackItemNav){
            selectedFragment = new FeedbackItemFragment();
        }

        getChildFragmentManager().beginTransaction().replace(R.id.fragment_containerInside, selectedFragment).commit();

    }

    private class GoBackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

//            Intent intent = new Intent(MyBagFragment.this, MainActivity.class);
//            intent.putExtra("pendingValuesList", pendingValuesList);
//            startActivity(intent);

        }
    }
}
