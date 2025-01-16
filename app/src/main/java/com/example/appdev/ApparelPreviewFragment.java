package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class ApparelPreviewFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{


    Button backButton, buyButton, addToCartButton;
    Spinner sizeSpinner, colorSpinner;
    int priceValue;

    TextView apparelName, apparelPrice;
    ImageView apparelImage;

    ArrayList<Integer> MLTLayoutsList, CSLayoutsList;

    ArrayList<ArrayList<Item>> moreLikeThisList, complimentaryStylesList;

    ArrayList<Item> moreLikeThisValuesForItemSelected, complimentaryStylesForItemSelected, pendingValuesList;

    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8;

    Item clickedItem;

    TextView textViewCart1, textViewCart2, textViewCart3, textViewCart4, textViewCart5, textViewCart6, textViewCart7, textViewCart8;

    View rootView;

    SharedViewModel sharedViewModel;



    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    User extractedCurrentUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_apparel_preview, container, false);

//        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
//
//        pendingValuesList = new ArrayList<>();
//        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
//            if (items != null ) {
//                pendingValuesList = items;
//            } else {
//                pendingValuesList = new ArrayList<>();
//            }
//        });



//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);



        /////////////////


        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

//        Toast.makeText(getActivity(), extractedCurrentUser.getUsername() + "  " , Toast.LENGTH_SHORT).show();




        ///////////









        apparelName =  rootView.findViewById(R.id.apparelNameTextView);
        apparelImage = rootView.findViewById(R.id.apparelImageView);

        layout1 = rootView.findViewById(R.id.layout1);
        layout1.setOnClickListener(new GoToApparelPreviewClickListener());

        layout2 = rootView.findViewById(R.id.layout2);
        layout2.setOnClickListener(new GoToApparelPreviewClickListener());

        layout3 = rootView.findViewById(R.id.layout3);
        layout3.setOnClickListener(new GoToApparelPreviewClickListener());

        layout4 = rootView.findViewById(R.id.layout4);
        layout4.setOnClickListener(new GoToApparelPreviewClickListener());

        layout5 = rootView.findViewById(R.id.layout5);
        layout5.setOnClickListener(new GoToApparelPreviewClickListener());

        layout6 = rootView.findViewById(R.id.layout6);
        layout6.setOnClickListener(new GoToApparelPreviewClickListener());

        layout7 = rootView.findViewById(R.id.layout7);
        layout7.setOnClickListener(new GoToApparelPreviewClickListener());

        layout8 = rootView.findViewById(R.id.layout8);
        layout8.setOnClickListener(new GoToApparelPreviewClickListener());


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

        textViewCart7 = rootView.findViewById(R.id.textViewCart7);
        textViewCart7.setOnClickListener(new addToCartItemClickListener());

        textViewCart8 = rootView.findViewById(R.id.textViewCart8);
        textViewCart8.setOnClickListener(new addToCartItemClickListener());





        Bundle bundle = this.getArguments();

        if(bundle != null){

            clickedItem = (Item) bundle.getSerializable("clickedApparelItem");


        }



//        Intent intent = getIntent();
//        clickedItem = (Item) intent.getSerializableExtra("clickedApparelItem");
//        int position = intent.getIntExtra("position", 0);



        int clickedApparelImage = clickedItem.getImage();
        String title = clickedItem.getTitle();
        priceValue = clickedItem.getPrice();


        moreLikeThisValuesForItemSelected = clickedItem.getMoreLikeThis();
        complimentaryStylesForItemSelected = clickedItem.getComplimentaryStyles();


//        moreLikeThisList = (ArrayList<ArrayList<Item>>) intent.getSerializableExtra("moreLikeThis");
//        complimentaryStylesList = (ArrayList<ArrayList<Item>>) intent.getSerializableExtra("complimentaryStyles");

        //get first the clickedApparel for getting the position in the moreLikeThisList
        //tas mao nani ang e for loop
//        moreLikeThisValuesForItemSelected =  moreLikeThisList.get(position);
//        complimentaryStylesForItemSelected = complimentaryStylesList.get(position);

        //for the moreLikeThis row
        MLTLayoutsList = new ArrayList<>();
        MLTLayoutsList.add(R.id.layout1);
        MLTLayoutsList.add(R.id.layout2);
        MLTLayoutsList.add(R.id.layout3);
        MLTLayoutsList.add(R.id.layout4);



        //ang layoutChildren based nalang to hide the remaning unused layouts
        for(int values = 0; values < MLTLayoutsList.size(); values++){

            if(values < moreLikeThisValuesForItemSelected.size()){
                SetImageAndTextOfLayout(MLTLayoutsList.get(values), moreLikeThisValuesForItemSelected.get(values));
            }

            else{
                LinearLayout layout = rootView.findViewById(MLTLayoutsList.get(values));
                layout.setVisibility(View.GONE);
            }


        }



        //for the complimentaryStyles row
        CSLayoutsList = new ArrayList<>();
        CSLayoutsList.add(R.id.layout5);
        CSLayoutsList.add(R.id.layout6);
        CSLayoutsList.add(R.id.layout7);
        CSLayoutsList.add(R.id.layout8);



        //ang layoutChildren based nalang to hide the remaning unused layouts
        for(int values = 0; values < CSLayoutsList.size(); values++){

            if(values < complimentaryStylesForItemSelected.size()){
                SetImageAndTextOfLayout(CSLayoutsList.get(values), complimentaryStylesForItemSelected.get(values));
            }

            else{
                LinearLayout layout = rootView.findViewById(CSLayoutsList.get(values));
                layout.setVisibility(View.GONE);
            }


        }




        sizeSpinner = rootView.findViewById(R.id.size);
        colorSpinner = rootView.findViewById(R.id.colors);
        apparelPrice = rootView.findViewById(R.id.priceTextViewe);


        apparelImage.setImageResource(clickedApparelImage);
        apparelName.setText(title);
        apparelPrice.setText("$" + priceValue);










        backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new GoBackClickListener());

        buyButton = rootView.findViewById(R.id.placeOrderButton);
        buyButton.setOnClickListener(this);

        addToCartButton = rootView.findViewById(R.id.btnAddtoCart);
        addToCartButton.setOnClickListener(this);




        ArrayAdapter<CharSequence> sizesAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.size, android.R.layout.simple_spinner_item);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizesAdapter);
        sizeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> colorsAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.colors, android.R.layout.simple_spinner_item);
        colorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorsAdapter);
        colorSpinner.setOnItemSelectedListener(this);



        return rootView;
    }





    private class addToCartItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {






            int position = 0;
            int inMore = 1;

            if(view.getId()==R.id.textViewCart2){
                position = 1;
                inMore = 1;
            }
            else if(view.getId()==R.id.textViewCart3){
                position = 2;
                inMore = 1;
            }
            else if(view.getId()==R.id.textViewCart4){
                position = 3;
                inMore = 1;
            }
            else if(view.getId()==R.id.textViewCart5){
                position = 0;
                inMore = 0;
            }
            else if(view.getId()==R.id.textViewCart6){
                position = 1;
                inMore = 0;
            }
            else if(view.getId()==R.id.textViewCart7){
                position = 2;
                inMore = 0;
            }
            else if(view.getId()==R.id.textViewCart8){
                position = 3;
                inMore = 0;
            }




            Toast.makeText(getActivity(), "Added to Cart!", Toast.LENGTH_SHORT).show();



            Item selectedItem;

            selectedItem = complimentaryStylesForItemSelected.get(position);

            if(inMore==1)
                selectedItem = moreLikeThisValuesForItemSelected.get(position);







            //////////////////////



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
            Item hol = selectedItem;

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
//            pendingValuesList.add(selectedItem);
//
//            sharedViewModel.setItems(pendingValuesList);
//
//            System.out.println(pendingValuesList.get(0).getTitle());


        }
    }






    public void SetImageAndTextOfLayout(int layoutId, Item item){

        //initializing the layout
        LinearLayout layout = rootView.findViewById(layoutId);



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



    private class GoToApparelPreviewClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int position = 0;
            int inMore = 1;

            if(view.getId()==R.id.layout2){
                position = 1;
                inMore = 1;
            }
            else if(view.getId()==R.id.layout3){
                position = 2;
                inMore = 1;
            }
            else if(view.getId()==R.id.layout4){
                position = 3;
                inMore = 1;
            }
            else if(view.getId()==R.id.layout5){
                position = 0;
                inMore = 0;
            }
            else if(view.getId()==R.id.layout6){
                position = 1;
                inMore = 0;
            }
            else if(view.getId()==R.id.layout7){
                position = 2;
                inMore = 0;
            }
            else if(view.getId()==R.id.layout8){
                position = 3;
                inMore = 0;
            }



            Item selectedItem;

            selectedItem = complimentaryStylesForItemSelected.get(position);

            if(inMore==1)
                selectedItem = moreLikeThisValuesForItemSelected.get(position);



            Bundle bundle = new Bundle();
            //bundle.putSerializable("pendingValuesList", pendingValuesList);
//        bundle.putSerializable("moreLikeThis", selectedApparelItemList.get(position).getMoreLikeThis());
//        bundle.putSerializable("complimentaryStyles", selectedApparelItemList.get(position).getComplimentaryStyles());
            bundle.putSerializable("clickedApparelItem", selectedItem);
            bundle.putInt("position", position);

            Fragment fragment = new ApparelPreviewFragment();
            fragment.setArguments(bundle);


            //changed to getChild
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_containerInside, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();







//            Intent intent = new Intent(requireActivity().getApplicationContext(), ApparelPreviewFragment.class)
//            intent.putExtra("clickedApparelItem", selectedItem);
//
//            intent.putExtra("moreLikeThis", selectedItem.getMoreLikeThis());
//            intent.putExtra("complimentaryStyles", selectedItem.getComplimentaryStyles());
//
//            intent.putExtra("position", position);
////            intent.putExtra("moreLikeThis", moreLikeThisList);
//
////            intent.putExtra("pendingValuesList", pendingValuesList);
//
//            startActivity(intent);







        }
    }



    @Override
    public void onClick(View view) {
        Intent intent1;


        System.out.println(clickedItem.getTitle()+"============");

//
//        pendingValuesList.add(clickedItem);
//        System.out.println(pendingValuesList.get(0).getTitle());
//        sharedViewModel.setItems(pendingValuesList);
//
//
//
//
//        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
//            if (items != null ) {
//                pendingValuesList = items;
//                System.out.println("1");
//            } else {
//                pendingValuesList = new ArrayList<>();
//                System.out.println("2");
//
//            }
//        });
//
//        Item itemTwin = new Item(clickedItem.getTitle(), clickedItem.getImage(), clickedItem.getTimesSold(), calculatedPrice, clickedItem.getMoreLikeThis(), clickedItem.getComplimentaryStyles());
//       // clickedItem.setPrice(calculatedPrice);
//        pendingValuesList.add(itemTwin);
//
//        sharedViewModel.setItems(pendingValuesList);

        //System.out.println(pendingValuesList.get(0).getTitle());





        //////////////////////



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
        Item hol = clickedItem;

        cartItemList.add(new Item(hol.getTitle(), hol.getImage(), hol.getTimesSold(), calculatedPrice, moreLikeThis, complimentaryStyles));

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








        if(view.getId()==R.id.btnAddtoCart){
            Toast.makeText(getActivity(), "Added to cart!", Toast.LENGTH_SHORT).show();

        }

        else if(view.getId()==R.id.placeOrderButton){
            Toast.makeText(getActivity(), "Buy Now Clicked", Toast.LENGTH_SHORT).show();
//            intent1 = new Intent(getActivity(), MyBagActivity.class);
//            intent1.putExtra("pendingValuesList", pendingValuesList);
//            startActivity(intent1);


            MainActivity.goToCartFragment();


        }


    }


    private class GoBackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            requireActivity().onBackPressed();
        }
    }












    int calculatedPrice = 0;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedSize = sizeSpinner.getSelectedItem().toString();
        String selectedColor = colorSpinner.getSelectedItem().toString();

        // Update the price based on the selected size and color
        calculatedPrice = calculatePrice(selectedSize, selectedColor);
        apparelPrice.setText("$" + calculatedPrice);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private int calculatePrice(String size, String color) {
        int sizePrice = clickedItem.getPrice();
        int colorPrice = 0;

        // Calculate size price
        if (size.equals("Medium")) {
            sizePrice = 190 + priceValue;
        } else if (size.equals("Large")) {
            sizePrice = 200 + priceValue;
        } else if (size.equals("XL")) {
            sizePrice = 250 + priceValue;
        }

        // Calculate color price
        //no position 0 kay mao ang default?
//        if (color.equals(colorSpinner.getItemAtPosition(1))) {
//            colorPrice = 60;
//        } else if (color.equals(colorSpinner.getItemAtPosition(2))) {
//            colorPrice = 80;
//        }

        return sizePrice + colorPrice;
    }

}
