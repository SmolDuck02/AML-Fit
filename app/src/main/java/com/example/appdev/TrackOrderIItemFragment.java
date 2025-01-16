package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TrackOrderIItemFragment extends Fragment implements TrackOrderItemAdapter.OnButtonClickListener {

    TrackOrderItemAdapter trackOrderItemAdapter;

    RecyclerView recyclerView;

    View rootView;

    SharedViewModel sharedViewModel;

    ArrayList<Item> pendingValuesList;

    ArrayList<Item> ItemsList;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    User currentUser;
    User extractedCurrentUser;


    ArrayList<Item> trackOrderItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_track_order_item, container,false);




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
        trackOrderItemList = gson.fromJson(extractedCurrentUser.getTrackOrderItemList(), listType);






        ////////////////////////




        recyclerView = rootView.findViewById(R.id.feedbackRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        trackOrderItemAdapter = new TrackOrderItemAdapter(trackOrderItemList, this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(trackOrderItemAdapter);

        return rootView;
    }



    public CartItem convertItemToCartItem(Item item){

        int image = item.getImage();
        String title = item.getTitle();
        int price = item.getPrice();

        return new CartItem(image, title, price);
    }


    @Override
    public void onButtonClick(Item item, int pos) {
        Item item1 = item;
        System.out.println("kpok" + item1.getTitle() + " " + item1.getTimesSold() + " " + item1.getPrice());

        Intent intent = new Intent(requireActivity().getApplicationContext(), TrackOrderItemPreviewActivity.class);
        intent.putExtra("clickedApparelItem", item1);
        startActivity(intent);


        editor.putInt("currentClickedItemPosition", pos);
        editor.apply();
    }


    public void notifyChange(){
        trackOrderItemAdapter.notifyDataSetChanged();
    }


}
