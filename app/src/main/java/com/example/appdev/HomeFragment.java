package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8, layout9, layout10;

    View rootView;


    ImageView image1, image2, image3, image4, image5, image6;
    TextView timesSold1, timesSold2, timesSold3, timesSold4, timesSold5, timesSold6;
    TextView price1, price2, price3, price4, price5, price6;

    ArrayList<Item> allApparelList;

    ArrayList<Item> moreLikeThisValuesList, complimentStylesValuesList, pendingValuesList;

    ArrayList<ArrayList<Item>> moreLikeThisEachItemList, complimentStylesEachItemList;


    SharedViewModel sharedViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.

            pendingValuesList = (ArrayList<Item>) bundle.getSerializable("pendingValuesList");

        }

        image1 = rootView.findViewById(R.id.imageView1);
        image2 = rootView.findViewById(R.id.imageView2);
        image3 = rootView.findViewById(R.id.imageView3);
        image4 = rootView.findViewById(R.id.imageView4);
        image5 = rootView.findViewById(R.id.imageView5);
        image6 = rootView.findViewById(R.id.imageView6);


        timesSold1 = rootView.findViewById(R.id.textView1);
        timesSold2 = rootView.findViewById(R.id.textView2);
        timesSold3 = rootView.findViewById(R.id.textView3);
        timesSold4 = rootView.findViewById(R.id.textView4);
        timesSold5 = rootView.findViewById(R.id.textView5);
        timesSold6 = rootView.findViewById(R.id.textView6);

        price1 = rootView.findViewById(R.id.textView11);
        price2 = rootView.findViewById(R.id.textView21);
        price3 = rootView.findViewById(R.id.textView31);
        price4 = rootView.findViewById(R.id.textView41);
        price5 = rootView.findViewById(R.id.textView51);
        price6 = rootView.findViewById(R.id.textView61);

        ApparelItem apparelItem = new ApparelItem();

        allApparelList = apparelItem.getAllApparelList();


        moreLikeThisEachItemList = apparelItem.getAllMoreLikeThisEachItemList();
        complimentStylesEachItemList = apparelItem.getAllComplimentStylesEachItemList();








        //refer to rootView so that it wont be null
        layout1 = (LinearLayout) rootView.findViewById(R.id.layout1);
        layout1.setOnClickListener(this);

        layout2 = (LinearLayout) rootView.findViewById(R.id.layout2);
        layout2.setOnClickListener(this);

        layout3 = (LinearLayout) rootView.findViewById(R.id.layout3);
        layout3.setOnClickListener(this);

        layout4 = (LinearLayout) rootView.findViewById(R.id.layout4);
        layout4.setOnClickListener(this);

        layout5 = (LinearLayout) rootView.findViewById(R.id.layout5);
        layout5.setOnClickListener(this);

        layout6 = (LinearLayout) rootView.findViewById(R.id.layout6);
        layout6.setOnClickListener(this);



        SetImageAndTextOfLayout(layout1, allApparelList.get(0));
        SetImageAndTextOfLayout(layout2, allApparelList.get(1));
        SetImageAndTextOfLayout(layout3, allApparelList.get(2));
        SetImageAndTextOfLayout(layout4, allApparelList.get(3));
        SetImageAndTextOfLayout(layout5, allApparelList.get(4));
        SetImageAndTextOfLayout(layout6, allApparelList.get(5));

        return rootView;


    }

    @Override
    public void onClick(View view) {

        int position = 0;

        if(view.getId()==R.id.layout2)
            position = 1;
        else if(view.getId()==R.id.layout3)
            position = 2;
        else if(view.getId()==R.id.layout4)
            position = 3;
        else if(view.getId()==R.id.layout5)
            position = 4;
        else if(view.getId()==R.id.layout6)
            position = 5;




        Intent intent = new Intent(requireActivity().getApplicationContext(), ApparelPreviewActivity.class);
        intent.putExtra("moreLikeThis", moreLikeThisEachItemList);
        intent.putExtra("complimentaryStyles", complimentStylesEachItemList);
        intent.putExtra("pendingValuesList", pendingValuesList);
        intent.putExtra("clickedApparelItem", allApparelList.get(position));
//        intent.putExtra("title", allApparelList.get(position).getTitle());
//        intent.putExtra("price", allApparelList.get(position).getPrice());
//        intent.putExtra("image", allApparelList.get(position).getImage());
        intent.putExtra("position", position);
        startActivity(intent);





    }


    public void SetImageAndTextOfLayout(LinearLayout layout, Item item){


        //getting the imageView inside layout1
        LinearLayout upperLayout = (LinearLayout) layout.getChildAt(0);
        View imageView = upperLayout.getChildAt(0);
        ImageView image = (ImageView) imageView;
        image.setImageResource(item.getImage());
        //image.setImageResource(R.drawable.s2);


        //getting the price and times sold inside layout1
        LinearLayout lowerLayout = (LinearLayout) layout.getChildAt(1);
        lowerLayout = (LinearLayout) lowerLayout.getChildAt(1);   // index 0 is the space component
        View soldView = lowerLayout.getChildAt(0);
        View priceView = lowerLayout.getChildAt(1);
        TextView soldText = (TextView) soldView;
        TextView priceText = (TextView) priceView;

        double sold = item.getTimesSold();
        soldText.setText(sold + "k sold");

        int priceValue = item.getPrice();
        priceText.setText("$" +   priceValue);

    }

}

