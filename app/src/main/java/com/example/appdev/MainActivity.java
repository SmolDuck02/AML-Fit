package com.example.appdev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.*;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationBarView.OnItemSelectedListener, Serializable{


    ApparelItem apparelItem;

    ItemAdapter itemAdapter;

    RecyclerView recyclerView;
    SearchView searchView;

    ArrayList<Item> apparelList, pendingValuesList;

    static LinearLayout linearLayout;

    TextView allFilter, upperWearFilter, bottomWearFilter, footWearFilter;


    static BottomNavigationView bottomNavigationView;


    boolean start = false;




    SharedViewModel sharedViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        apparelItem = new ApparelItem();


        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        final ArrayList<Item>[] s = new ArrayList[]{new ArrayList<>()};

        sharedViewModel.getItems().observe(this, new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                if (items != null ) {
                    //System.out.println(items.get(0) + "kkkkkkkkkkssss");
                    pendingValuesList = items;
                    s[0] = items;
                } else {
                    pendingValuesList = new ArrayList<>();
                }
            }

        });


        //init
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //extract
        String myValue = sharedPreferences.getString("myKey", "Default Value");
        System.out.println(myValue + " 000000000000");






        allFilter = findViewById(R.id.textViewAll);
        allFilter.setOnClickListener(new OpenFragmentFromFilter());
        upperWearFilter = findViewById(R.id.textViewShirt);
        upperWearFilter.setOnClickListener(new OpenFragmentFromFilter());
        bottomWearFilter = findViewById(R.id.textViewPants);
        bottomWearFilter.setOnClickListener(new OpenFragmentFromFilter());
        footWearFilter = findViewById(R.id.textViewShoes);
        footWearFilter.setOnClickListener(new OpenFragmentFromFilter());


        linearLayout = findViewById(R.id.linearLayoutHeader);
        linearLayout.setVisibility(View.VISIBLE);


        apparelList = apparelItem.getAllApparelList();


        recyclerView = findViewById(R.id.recyclerViewContainer);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(apparelList);
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {



                Bundle bundle = new Bundle();
                bundle.putSerializable("clickedApparelItem", itemAdapter.itemList.get(position));
                bundle.putInt("position", position);

                Fragment fragment = new ApparelPreviewFragment();
                fragment.setArguments(bundle);



                //changed to getChild
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_containerInside, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                recyclerView.setVisibility(View.GONE);


//                Intent intent = new Intent(getApplicationContext(), ApparelPreviewActivity.class);
//                intent.putExtra("clickedApparelItem", itemAdapter.itemList.get(position));
//                intent.putExtra("position", position);
//
//
//                intent.putExtra("pendingValuesList", pendingValuesList);
//
//
//                intent.putExtra("moreLikeThis", apparelItem.getAllMoreLikeThisEachItemList());
//                intent.putExtra("complimentaryStyles", apparelItem.getAllComplimentStylesEachItemList());
//
//                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);




        searchView = findViewById(R.id.searchViewBar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(this);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnItemSelectedListener(this);


        //vchange to home
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FootwearFragment()).commit();
        sendPendingValuesList(new FootwearFragment(), apparelItem.getAllApparelList(), apparelItem.getAllMoreLikeThisEachItemList(), apparelItem.getAllComplimentStylesEachItemList());

        sharedViewModel.setItems(pendingValuesList);
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(newText.equals(""))
            recyclerView.setVisibility(View.GONE);
        else {
            recyclerView.setVisibility(View.VISIBLE); //showing the recycler view
        }

        filterList(newText);

        return false;
    }



    private void filterList(String newText) {



        ArrayList<Item> filteredList = new ArrayList<>();
        for(Item item : apparelList){
            if(item.getTitle().contains(newText.toLowerCase(Locale.ROOT)) || item.getTitle().contains(newText.toUpperCase(Locale.ROOT))){
                filteredList.add(item);
            }
        }


        if(filteredList.isEmpty())
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        else{
            itemAdapter.setFilteredList(filteredList);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //change to home
        Fragment selectedFragment = new FootwearFragment();


        ArrayList<Item> selectedApparelItemList = new ArrayList<>();
        ArrayList<ArrayList<Item>> moreLikeThisItemList = new ArrayList<>();
        ArrayList<ArrayList<Item>> complimentaryStylesItemList = new ArrayList<>();


        if(item.getItemId()==R.id.navigation_home) {
            selectedApparelItemList = apparelItem.getAllApparelList();
            moreLikeThisItemList = apparelItem.getAllMoreLikeThisEachItemList();
            complimentaryStylesItemList = apparelItem.getAllComplimentStylesEachItemList();
            linearLayout.setVisibility(View.VISIBLE);
        }
        else if(item.getItemId() == R.id.navigation_trends) {
            selectedApparelItemList =apparelItem.getTrendingApparelList();
            moreLikeThisItemList = apparelItem.getTrendingMoreLikeThisEachItemList();
            complimentaryStylesItemList = apparelItem.getTrendingComplimentStylesEachItemList();
            linearLayout.setVisibility(View.VISIBLE);
        }
        else if(item.getItemId() == R.id.navigation_cart){
//            Intent intent = new Intent(getApplicationContext(), MyBagActivity.class);
//            intent.putExtra("pendingValuesList", pendingValuesList);
//            startActivity(intent);
//            return true;

            selectedFragment = new MyBagFragment();
            linearLayout.setVisibility(View.INVISIBLE);

        }
        else if(item.getItemId() == R.id.navigation_account){
            linearLayout.setVisibility(View.INVISIBLE);
            selectedFragment = new AccountFragment();

        }


        sendPendingValuesList(selectedFragment, selectedApparelItemList, moreLikeThisItemList, complimentaryStylesItemList);

        return true;

    }

    private class OpenFragmentFromFilter implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            ArrayList<Item> selectedApparelItemList = apparelItem.getAllApparelList();
            ArrayList<ArrayList<Item>> moreLikeThisItemList = new ArrayList<>();
            ArrayList<ArrayList<Item>> complimentaryStylesItemList = new ArrayList<>();

            Fragment selectedFragment = new FootwearFragment();

            if(view.getId()==R.id.textViewShirt){
                selectedApparelItemList = apparelItem.getUpperwearApparelList();
                moreLikeThisItemList = apparelItem.getUpperMoreLikeThisEachItemList();
                complimentaryStylesItemList = apparelItem.getUpperComplimentStylesEachItemList();
            }

            else if(view.getId()==R.id.textViewPants){
                selectedApparelItemList = apparelItem.getBottomwearApparelList();
                moreLikeThisItemList = apparelItem.getBottomMoreLikeThisEachItemList();
                complimentaryStylesItemList = apparelItem.getBottomComplimentStylesEachItemList();
            }

            else if(view.getId()==R.id.textViewShoes){
                selectedApparelItemList = apparelItem.getFootwearApparelList();
                moreLikeThisItemList = new ArrayList<>();
                complimentaryStylesItemList = new ArrayList<>();
            }



            sendPendingValuesList(selectedFragment, selectedApparelItemList, moreLikeThisItemList, complimentaryStylesItemList);


        }
    }

    private void sendPendingValuesList(Fragment selectedFragment, ArrayList<Item> selectedApparelItemList, ArrayList<ArrayList<Item>> moreLikeThisItemList, ArrayList<ArrayList<Item>> complimentaryStylesItemList) {
        Bundle bundle = new Bundle();
        //bundle.putSerializable("pendingValuesList", pendingValuesList);
        bundle.putSerializable("selectedApparelItemList", selectedApparelItemList);
        bundle.putSerializable("moreLikeThisItemList", moreLikeThisItemList);
        bundle.putSerializable("complimentaryStylesItemList", complimentaryStylesItemList);

        Fragment fragment = selectedFragment;
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containerInside, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void showSearchHeader(){
        linearLayout.setVisibility(View.VISIBLE);
    }

    public static void goToCartFragment(){
        bottomNavigationView.setSelectedItemId(R.id.navigation_cart);
    }

}