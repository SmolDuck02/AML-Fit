package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;

public class FootwearFragment extends Fragment implements View.OnClickListener {

    ApparelItem apparelItem;

    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8, layout9, layout10;

    View rootView;

    ImageView image1, image2, image3, image4, image5, image6;
    TextView timesSold1, timesSold2, timesSold3, timesSold4, timesSold5, timesSold6;
    TextView price1, price2, price3, price4, price5, price6;

    TextView textViewCart1, textViewCart2, textViewCart3, textViewCart4, textViewCart5, textViewCart6;



    ArrayList<Item> selectedApparelItemList, moreLikeThisValuesList, complimentStylesValuesList, pendingValuesList;

    ArrayList<ArrayList<Item>> moreLikeThisEachItemList, complimentStylesEachItemList;


    SharedViewModel sharedViewModel;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    User extractedCurrentUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_footwear, container, false);

        apparelItem = new ApparelItem();



        /////////////////


        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

//        Toast.makeText(getActivity(), extractedCurrentUser.getUsername() + "  " , Toast.LENGTH_SHORT).show();
//



        ///////////



//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
//
//            if (items != null ) {
//                pendingValuesList = items;
//                System.out.println("0");
//            } else {
//                pendingValuesList = new ArrayList<>();
//                System.out.println("-1");
//            }
//        });



        Bundle bundle = this.getArguments();

        if(bundle != null){

            selectedApparelItemList = (ArrayList<Item>) bundle.getSerializable("selectedApparelItemList");
            moreLikeThisEachItemList = (ArrayList<ArrayList<Item>>) bundle.getSerializable("moreLikeThisItemList");
            complimentStylesEachItemList = (ArrayList<ArrayList<Item>>) bundle.getSerializable("complimentaryStylesItemList");
//            linearLayout = (LinearLayout) bundle.getSerializable("searchLayout");
            //pendingValuesList = (ArrayList<Item>) bundle.getSerializable("pendingValuesList");

        }
//
//        moreLikeThisEachItemList = apparelItem.getShoesMoreLikeThisEachItemList();
//        complimentStylesEachItemList = apparelItem.getShoesComplimentStylesEachItemList();

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


        textViewCart1 = rootView.findViewById(R.id.textViewCart1);
        textViewCart1.setOnClickListener(new addToCartItemClickListener());

        textViewCart2 = rootView.findViewById(R.id.textViewCart2);
        textViewCart2.setOnClickListener(new addToCartItemClickListener());

        textViewCart3 = rootView.findViewById(R.id.textViewCart3);
        textViewCart3.setOnClickListener(new addToCartItemClickListener());

        textViewCart4 = rootView.findViewById(R.id.textViewCart4);
        textViewCart4.setOnClickListener(new addToCartItemClickListener());

        textViewCart5 = rootView.findViewById(R.id.textViewCart5);
        textViewCart5.setOnClickListener(new addToCartItemClickListener());

        textViewCart6 = rootView.findViewById(R.id.textViewCart6);
        textViewCart6.setOnClickListener(new addToCartItemClickListener());




        SetImageAndTextOfLayout(layout1, selectedApparelItemList.get(0));
        SetImageAndTextOfLayout(layout2, selectedApparelItemList.get(1));
        SetImageAndTextOfLayout(layout3, selectedApparelItemList.get(2));
        SetImageAndTextOfLayout(layout4, selectedApparelItemList.get(3));
        SetImageAndTextOfLayout(layout5, selectedApparelItemList.get(4));
        SetImageAndTextOfLayout(layout6, selectedApparelItemList.get(5));


        return rootView;


    }



    //going to apparel preview page

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




//        //start
//        sharedViewModel.setItems(pendingValuesList);
//        //end



        Bundle bundle = new Bundle();
        //bundle.putSerializable("pendingValuesList", pendingValuesList);
//        bundle.putSerializable("moreLikeThis", selectedApparelItemList.get(position).getMoreLikeThis());
//        bundle.putSerializable("complimentaryStyles", selectedApparelItemList.get(position).getComplimentaryStyles());
        bundle.putSerializable("clickedApparelItem", selectedApparelItemList.get(position));
        bundle.putInt("position", position);

        Fragment fragment = new ApparelPreviewFragment();
        fragment.setArguments(bundle);



        //changed to getChild
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containerInside, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


//        Intent intent = new Intent(requireActivity().getApplicationContext(), ApparelPreviewActivity.class);
//       // intent.putExtra("pendingValuesList", pendingValuesList);
//        intent.putExtra("moreLikeThis", selectedApparelItemList.get(position).getMoreLikeThis());
//        intent.putExtra("complimentaryStyles", selectedApparelItemList.get(position).getComplimentaryStyles());
//        intent.putExtra("clickedApparelItem", selectedApparelItemList.get(position));
//        intent.putExtra("position", position);
//        startActivity(intent);

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


    private class addToCartItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            int position = 0;

            if(view.getId()==R.id.textViewCart2)
                position = 1;
            else if(view.getId()==R.id.textViewCart3)
                position = 2;
            else if(view.getId()==R.id.textViewCart4)
                position = 3;
            else if(view.getId()==R.id.textViewCart5)
                position = 4;
            else if(view.getId()==R.id.textViewCart6)
                position = 5;


            Toast.makeText(getActivity(), "Added to Cart!", Toast.LENGTH_SHORT).show();








            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> cartItemList = gson.fromJson(extractedCurrentUser.getCartItemList(), listType);




            ArrayList<Item> ite;
            ArrayList<Item> moreLikeThis = new ArrayList<>();
            ArrayList<Item> complimentaryStyles = new ArrayList<>();
//            moreLikeThis.add(new Item("Covered White Top", R.drawable.u8, 4.1, 350, new ArrayList<>(), new ArrayList<>()));
//            moreLikeThis.add(new Item("Covered White Top", R.drawable.u8, 4.1, 350, new ArrayList<>(), new ArrayList<>()));
//
//            complimentaryStyles.add(new Item("Covered White Top", R.drawable.u8, 4.1, 350, new ArrayList<>(), new ArrayList<>()));
//            complimentaryStyles.add(new Item("Covered White Top", R.drawable.u8, 4.1, 350, new ArrayList<>(), new ArrayList<>()));
//
            Item hol = selectedApparelItemList.get(position);

            cartItemList.add(new Item(hol.getTitle(), hol.getImage(), hol.getTimesSold(), hol.getPrice(), moreLikeThis, complimentaryStyles));

            String cartItemListString = gson.toJson(cartItemList);




            //setting the current user cartlistString to new cartListString
            extractedCurrentUser.setCartItemList(cartItemListString);


            //setting the current user to string
            String currentUserString = gson.toJson(extractedCurrentUser);
            editor.putString("currentUser", currentUserString);
            editor.apply();



            for(int i = 0; i < cartItemList.size(); i++){
                System.out.println(cartItemList.get(i).getTitle() + "TTTTTTTTTTTTTT");
            }


            //updating the currentUser in the arraylist of accounts

            //extracting for list of sccounts
            String accountsString = sharedPreferences.getString("accounts", "Default");
            Account accountList = gson.fromJson(accountsString, Account.class);

            ArrayList<User> accountUserList = accountList.getUserList();

            for(int i = 0; i < accountUserList.size(); i++){
                System.out.println(accountUserList.get(i).getUsername() + "!!!!!!!");
            }


            int pos = sharedPreferences.getInt("currentUserPosition", -1);

            System.out.println(pos + " " + accountUserList.get(pos).getUsername() + "yyyyyyyyyyyyyyyyyyyyyyyyyyy");

            accountUserList.set(pos, extractedCurrentUser);


            accountList.setUserList(accountUserList);


            String accountsListString = gson.toJson(accountList);
            editor.putString("accounts", accountsListString);
            editor.apply();








            ///////////////////////////





//
//            sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
//                if (items != null ) {
//                    pendingValuesList = items;
//                    System.out.println("1");
//                } else {
//                    pendingValuesList = new ArrayList<>();
//                    System.out.println("2");
//
//                }
//            });
//
//            pendingValuesList.add(selectedApparelItemList.get(position));
//
//            sharedViewModel.setItems(pendingValuesList);
//
//            System.out.println(pendingValuesList.get(0).getTitle());


        }
    }
}
