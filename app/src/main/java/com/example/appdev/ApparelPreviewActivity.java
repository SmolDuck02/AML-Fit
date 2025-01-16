package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;


public class ApparelPreviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{


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


    SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apparel_preview);


        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        pendingValuesList = new ArrayList<>();
        sharedViewModel.getItems().observe(this, items -> {
            if (items != null ) {
                pendingValuesList = items;
            } else {
                pendingValuesList = new ArrayList<>();
            }
        });

        apparelName =  findViewById(R.id.apparelNameTextView);
        apparelImage = findViewById(R.id.apparelImageView);

        layout1 = findViewById(R.id.layout1);
        layout1.setOnClickListener(new GoToApparelPreviewClickListener());

        layout2 = findViewById(R.id.layout2);
        layout2.setOnClickListener(new GoToApparelPreviewClickListener());

        layout3 = findViewById(R.id.layout3);
        layout3.setOnClickListener(new GoToApparelPreviewClickListener());

        layout4 = findViewById(R.id.layout4);
        layout4.setOnClickListener(new GoToApparelPreviewClickListener());

        layout5 = findViewById(R.id.layout5);
        layout5.setOnClickListener(new GoToApparelPreviewClickListener());

        layout6 = findViewById(R.id.layout6);
        layout6.setOnClickListener(new GoToApparelPreviewClickListener());

        layout7 = findViewById(R.id.layout7);
        layout7.setOnClickListener(new GoToApparelPreviewClickListener());

        layout8 = findViewById(R.id.layout8);
        layout8.setOnClickListener(new GoToApparelPreviewClickListener());



        Intent intent = getIntent();
        clickedItem = (Item) intent.getSerializableExtra("clickedApparelItem");
        int position = intent.getIntExtra("position", 0);
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
                LinearLayout layout = findViewById(MLTLayoutsList.get(values));
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
                LinearLayout layout = findViewById(CSLayoutsList.get(values));
                layout.setVisibility(View.GONE);
            }


        }




        sizeSpinner = findViewById(R.id.size);
        colorSpinner = findViewById(R.id.colors);
        apparelPrice = findViewById(R.id.priceTextViewe);


        apparelImage.setImageResource(clickedApparelImage);
        apparelName.setText(title);
        apparelPrice.setText("$" + priceValue);










        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new GoBackClickListener());

        buyButton = findViewById(R.id.placeOrderButton);
        buyButton.setOnClickListener(this);

        addToCartButton = findViewById(R.id.btnAddtoCart);
        addToCartButton.setOnClickListener(this);




        ArrayAdapter<CharSequence> sizesAdapter = ArrayAdapter.createFromResource(this, R.array.size, android.R.layout.simple_spinner_item);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizesAdapter);
        sizeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> colorsAdapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        colorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorsAdapter);
        colorSpinner.setOnItemSelectedListener(this);



        sharedViewModel.setItems(pendingValuesList);

    }




    public void SetImageAndTextOfLayout(int layoutId, Item item){

        //initializing the layout
        LinearLayout layout = findViewById(layoutId);



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

            Intent intent = new Intent(getApplicationContext(), ApparelPreviewActivity.class);

            selectedItem = complimentaryStylesForItemSelected.get(position);

            if(inMore==1)
                selectedItem = moreLikeThisValuesForItemSelected.get(position);



            intent.putExtra("clickedApparelItem", selectedItem);

            intent.putExtra("moreLikeThis", selectedItem.getMoreLikeThis());
            intent.putExtra("complimentaryStyles", selectedItem.getComplimentaryStyles());

            intent.putExtra("position", position);
//            intent.putExtra("moreLikeThis", moreLikeThisList);

//            intent.putExtra("pendingValuesList", pendingValuesList);

            startActivity(intent);


            sharedViewModel.setItems(pendingValuesList);



        }
    }



    @Override
    public void onClick(View view) {
        Intent intent1;


        System.out.println(clickedItem.getTitle()+"============");


        pendingValuesList.add(clickedItem);

        System.out.println(pendingValuesList.get(0).getTitle());


        sharedViewModel.setItems(pendingValuesList);


        if(view.getId()==R.id.btnAddtoCart){
            Toast.makeText(ApparelPreviewActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();

        }

        else if(view.getId()==R.id.placeOrderButton){
            Toast.makeText(ApparelPreviewActivity.this, "Buy Now Clicked", Toast.LENGTH_SHORT).show();
            intent1 = new Intent(this, MyBagActivity.class);
            intent1.putExtra("pendingValuesList", pendingValuesList);
            startActivity(intent1);
        }


    }


    private class GoBackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onBackPressed();
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
        int sizePrice = priceValue;
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
        if (color.equals(colorSpinner.getItemAtPosition(0))) {
            colorPrice = 50;
        } else if (color.equals(colorSpinner.getItemAtPosition(1))) {
            colorPrice = 60;
        } else if (color.equals(colorSpinner.getItemAtPosition(2))) {
            colorPrice = 80;
        }

        return sizePrice + colorPrice;
    }

}
