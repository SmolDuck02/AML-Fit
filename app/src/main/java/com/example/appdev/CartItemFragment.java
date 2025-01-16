package com.example.appdev;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartItemFragment extends Fragment implements View.OnClickListener, CartItemAdapter.OnItemClickListener, CartItemAdapter.OnButtonClickListener {


    CartItemAdapter cartItemAdapter;

    RecyclerView recyclerView;

    View rootView;

    TextView totalPriceTextView;

    Button placeOrder;

    ArrayList<Item> pendingValuesList;

    SharedViewModel sharedViewModel;


    int totalPrice;

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }


    ArrayList<Item> originalItemsList;
    ArrayList<ModifiedItem> ItemsList, selectedItemList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    User currentUser;
    User extractedCurrentUser;



    int total = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart_item, container,false);

        totalPriceTextView = rootView.findViewById(R.id.priceTextViewe);


        //////////////////



        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

//        Toast.makeText(getActivity(), extractedCurrentUser.getUsername() + "  " , Toast.LENGTH_SHORT).show();


        //getting the cartItemList Array
        Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
        ArrayList<Item> cartItemList = gson.fromJson(extractedCurrentUser.getCartItemList(), listType);





        originalItemsList = cartItemList;
        selectedItemList = new ArrayList<>();
        ItemsList = new ArrayList<>();



        for(int i = 0; i < originalItemsList.size(); i++){
            int price = originalItemsList.get(i).getPrice();
            Item item = originalItemsList.get(i);
            ItemsList.add(new ModifiedItem(item.getTitle(), item.getImage(), item.getTimesSold(), item.getPrice(), item.getMoreLikeThis(), item.getComplimentaryStyles(), false, 1));
        }




        ////////////////////////





//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//        sharedViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
//            if (items != null ) {
//                total = 0;
//                //System.out.println(items.get(0).getTitle() + "llllllll");
//                for(int i = 0; i < items.size(); i++){
////                    int image = items.get(i).getImage();
////                    String title = items.get(i).getTitle();
//                    int price = items.get(i).getPrice();
//                    originalItemsList = items;
//                    total += price;
//                    System.out.println(total);
//                    Item item = items.get(i);
//                    ItemsList.add(new ModifiedItem(item.getTitle(), item.getImage(), item.getTimesSold(), item.getPrice(), item.getMoreLikeThis(), item.getComplimentaryStyles(), false, 1));
//                }
//
////                String priceText = "$" + total;
////                totalPriceTextView.setText(priceText);
//            }
//        });








        //sample values for cartItemRecyclerView
//        cartItemsList.add(new CartItem(R.drawable.s1, "jameel", 10));
//        cartItemsList.add(new CartItem(R.drawable.s1, "j", 20));
//        cartItemsList.add(new CartItem(R.drawable.s1, "l", 30));
//

        placeOrder = rootView.findViewById(R.id.placeOrderButton);
        placeOrder.setOnClickListener(new PlaceOrderClickListener());

        recyclerView = rootView.findViewById(R.id.cartItemRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        cartItemAdapter = new CartItemAdapter(totalPriceTextView, ItemsList, this, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cartItemAdapter);



        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.placeOrderButton){
            Intent intent = new Intent(requireActivity().getApplicationContext(), SuccessfulActivity.class);
            startActivity(intent);
            return;
        }

    }


    @Override
    public void onItemClick(ModifiedItem modifiedItem) {

        Item item = new Item(modifiedItem.getTitle(), modifiedItem.getImage(), modifiedItem.getTimesSold(), modifiedItem.getPrice(), modifiedItem.getMoreLikeThis(), modifiedItem.getComplimentaryStyles());

        Bundle bundle = new Bundle();
        bundle.putSerializable("clickedApparelItem", item);

        Fragment fragment = new ApparelPreviewFragment();
        fragment.setArguments(bundle);


        //changed to getChild
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containerInside, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        MainActivity.showSearchHeader();


    }



    @Override
    public void onButtonClick(ModifiedItem item, TextView priceView, TextView quantity, String method, int pos) {

        int itemNewCurrentPrice = 0;

        if(method.equals("add")){

            item.setQuantity(item.getQuantity() + 1);

            cartItemAdapter.notifyDataSetChanged();

//            String newQuantityText = "" + (Integer.parseInt(quantity.getText().toString()) + 1);
//            quantity.setText(newQuantityText);
//
//
//            itemNewCurrentPrice = item.getPrice() + originalItemsList.get(pos).getPrice();
//            item.setPrice(itemNewCurrentPrice);
//            priceView.setText("$" + itemNewCurrentPrice);

        }

        else if(method.equals("subtract")){
            //if (Integer.parseInt(quantity.getText().toString()) > 1)
            if(item.getQuantity() > 1){

                item.setQuantity(item.getQuantity() - 1);
                cartItemAdapter.notifyDataSetChanged();


//                String newQuantityText = "" + (Integer.parseInt(quantity.getText().toString()) - 1);
//                quantity.setText(newQuantityText);
//
//
//                itemNewCurrentPrice = item.getPrice() - originalItemsList.get(pos).getPrice();
//                item.setPrice(itemNewCurrentPrice);
//                priceView.setText("$" + itemNewCurrentPrice);
            }

        }

        int totalFinal = 0;
        for(int i = 0; i < ItemsList.size(); i++) {
            totalFinal += ItemsList.get(i).getPrice();
        }



//        totalPriceTextView.setText("$" + totalFinal);
    }

    @Override
    public void onButtonClick(int pos, String method) {
        if(method.equals("remove")){
            selectedItemList.remove(ItemsList.get(pos));
            originalItemsList.remove(pos);
            ItemsList.remove(pos);
            cartItemAdapter.notifyItemRemoved(pos);
            cartItemAdapter.notifyDataSetChanged();

        }

        else if(method.equals("selected"))
            selectedItemList.add(ItemsList.get(pos));
        else if(method.equals("deselected"))
            selectedItemList.remove(ItemsList.get(pos));
        else if(method.equals("replace")){
            int posToReplace = selectedItemList.indexOf(ItemsList.get(pos));
            selectedItemList.set(posToReplace, ItemsList.get(pos));
        }


        int totalFinal = 0;
        for(int i = 0; i < selectedItemList.size(); i++) {
            totalFinal += (selectedItemList.get(i).getQuantity() * selectedItemList.get(i).getPrice());
        }

        totalPriceTextView.setText("$" +   String.format("%.2f", (float) totalFinal));





        //////////////////////////////






        Gson gson = new Gson();
        String cartItemListString = gson.toJson(ItemsList);


        //setting the current user cartlistString to new cartListString
        extractedCurrentUser.setCartItemList(cartItemListString);


        //setting the current user to string
        String currentUserString = gson.toJson(extractedCurrentUser);
        editor.putString("currentUser", currentUserString);
        editor.apply();



        for(int i = 0; i < ItemsList.size(); i++){
            System.out.println(ItemsList.get(i).getTitle() + "TTTTTTTTTTT");
        }


        //updating the currentUser in the arraylist of accounts

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


    private class PlaceOrderClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if(selectedItemList.size()>0){
                for(int i = 0; i < selectedItemList.size(); i++) {
                    int pos = ItemsList.indexOf(selectedItemList.get(i));
                    originalItemsList.remove(pos);
                    ItemsList.remove(pos);

                    cartItemAdapter.notifyItemRemoved(pos);
                    cartItemAdapter.notifyDataSetChanged();
                }

                totalPriceTextView.setText("$0.00");
            }

            else
                Toast.makeText(requireContext(), "No item selected!", Toast.LENGTH_SHORT).show();



            /////////////////////////////





            Gson gson = new Gson();


            //// the update of cartItem as it places order it removes

            //get the values of cartItem first
            //getting the cartItemlist Array
            String cartItemListString = gson.toJson(ItemsList);


            //setting the current user cartlistString to new cartListString
            extractedCurrentUser.setCartItemList(cartItemListString);











            //// the update of trackOrder as it places order it adds

            //get the values of trackOrder first
            //getting the trackOrderlist Array
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> trackOrderItemList = gson.fromJson(extractedCurrentUser.getTrackOrderItemList(), listType);

            ArrayList<Item> moreLikeThis = new ArrayList<>();
            ArrayList<Item> complimentaryStyles = new ArrayList<>();

            for(int i=0; i < selectedItemList.size(); i++){
                ModifiedItem mod = selectedItemList.get(i);
                Item newItem = new Item(mod.getTitle(), mod.getImage(), mod.getQuantity(), mod.getPrice(),  moreLikeThis, complimentaryStyles);
                trackOrderItemList.add(newItem);
            }

            String trackOrderItemListString = gson.toJson(trackOrderItemList);


            //setting the current user cartlistString to new cartListString
            extractedCurrentUser.setTrackOrderItemList(trackOrderItemListString);


            //setting the current user to string
            String currentUserString = gson.toJson(extractedCurrentUser);
            editor.putString("currentUser", currentUserString);
            editor.apply();






            for(int i = 0; i < selectedItemList.size(); i++){
                System.out.println(selectedItemList.get(i).getTitle() + "SSSSTTTTTTTTTTT");
            }





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


            if(selectedItemList.size()>0){
                Intent intent = new Intent(requireActivity(), PaymentGateway.class);
                startActivity(intent);
            }




        }
    }
}
