package com.example.appdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemviewRecyclerViewFragment extends Fragment {

    View rootView;

    RecyclerView recyclerView;
    RecyclerView.Adapter itemAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Item> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_itemview_recyclerview, container, false);

        //refer to rootView so that it wont be null
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(itemList);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);


        return rootView;


    }
}
