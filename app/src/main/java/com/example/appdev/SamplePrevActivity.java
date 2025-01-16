package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SamplePrevActivity extends AppCompatActivity implements View.OnClickListener {


    TextView apparelName;
    ImageView apparelImage;

    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_prev);

        apparelName =  findViewById(R.id.messageTextView);
        apparelImage = findViewById(R.id.imageView5);



        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout1.setOnClickListener(this);

        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout2.setOnClickListener(this);

        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout3.setOnClickListener(this);

        layout4 = (LinearLayout) findViewById(R.id.layout4);
        layout4.setOnClickListener(this);










        Intent intent = getIntent();
        int clickedApparelImage = intent.getIntExtra("image", R.drawable.__icon__home__inactive);
        int position = intent.getIntExtra("position", 0);
        String name = intent.getStringExtra("clickedApparel");

        ArrayList<ArrayList<Item>> moreLikeThisList = (ArrayList<ArrayList<Item>>) intent.getSerializableExtra("moreLikeThis");


        //get first the clickedApparel for getting the position in the morelikethislist
        //tas mao nani ang e for loop
        ArrayList<Item> moreLikeThisValuesForItemSelected =  moreLikeThisList.get(position);


        ArrayList<ArrayList<Integer>> layoutChildrenList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> childrenList = new ArrayList<>();


        childrenList.add(R.id.textView1);
        childrenList.add(R.id.textView11);
        childrenList.add(R.id.imageView1);
        layoutChildrenList.add(childrenList);


        childrenList = new ArrayList<>();
        childrenList.add(R.id.textView2);
        childrenList.add(R.id.textView22);
        childrenList.add(R.id.imageView2);
        layoutChildrenList.add(childrenList);

        childrenList = new ArrayList<>();
        childrenList.add(R.id.textView3);
        childrenList.add(R.id.textView33);
        childrenList.add(R.id.imageView3);
        layoutChildrenList.add(childrenList);

        childrenList = new ArrayList<>();
        childrenList.add(R.id.textView4);
        childrenList.add(R.id.textView44);
        childrenList.add(R.id.imageView4);
        layoutChildrenList.add(childrenList);


        //must make sure list size is equal to no. of layout in xml
        int size = Math.min(moreLikeThisValuesForItemSelected.size(), layoutChildrenList.size());

        for(int values = 0; values < size; values++){

//            TextView soldText =  findViewById(R.id.textView1);
//            TextView priceText =  findViewById(R.id.textView11);
//            ImageView imgView = findViewById(R.id.imageView1);

            TextView soldText =  findViewById(layoutChildrenList.get(values).get(0));
            TextView priceText =  findViewById(layoutChildrenList.get(values).get(1));
            ImageView imgView = findViewById(layoutChildrenList.get(values).get(2));


            Item item = moreLikeThisValuesForItemSelected.get(values);
            imgView.setImageResource(item.getImage());
            double sold = item.getTimesSold();
            soldText.setText(sold + "k sold");
            String newPriceText = "$" + item.getPrice();
            priceText.setText(newPriceText);

        }

//        Item item = new Item("", R.drawable.s10, "10k sold", 10);
//        imgView.setImageResource(item.getImage());
//        soldText.setText(item.getSold());
//        priceText.setText("$ " + item.getPrice());


        apparelImage.setImageResource(clickedApparelImage);
        apparelName.setText(name);
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

        ArrayList<Item> fromMoreLikeThisList = new ArrayList<>();
//        fromMoreLikeThisList.add(new Item("", R.drawable.s1, 0, 0));
//        fromMoreLikeThisList.add(new Item("", R.drawable.s2, 0, 0));
//        fromMoreLikeThisList.add(new Item("", R.drawable.s3, 0, 0));
//        fromMoreLikeThisList.add(new Item("", R.drawable.s4, 0, 0));


        Intent intent = new Intent(getApplicationContext(), SamplePrevActivity.class);
        //intent.putExtra("moreLikeThis", moreLikeThisValuesList);
        intent.putExtra("clickedApparel", fromMoreLikeThisList.get(position).getTitle());
        intent.putExtra("image", fromMoreLikeThisList.get(position).getImage());
        startActivity(intent);
    }
}