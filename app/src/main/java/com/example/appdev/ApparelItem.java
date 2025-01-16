package com.example.appdev;

import java.util.ArrayList;

public class ApparelItem {

    private ArrayList<Item> allApparelList, footwearApparelList, upperwearApparelList, bottomwearApparelList, trendingApparelList, fcopy, ucopy, bcopy;

    private ArrayList<Item> moreLikeThisValuesList, complimentStylesValuesList;

    private ArrayList<ArrayList<Item>> shoesMoreLikeThisEachItemList, shoesComplimentStylesEachItemList;

    private ArrayList<ArrayList<Item>> upperMoreLikeThisEachItemList, upperComplimentStylesEachItemList;

    private ArrayList<ArrayList<Item>> bottomMoreLikeThisEachItemList, bottomComplimentStylesEachItemList;

    private ArrayList<ArrayList<Item>> trendingMoreLikeThisEachItemList, trendingComplimentStylesEachItemList;

    private ArrayList<ArrayList<Item>> allMoreLikeThisEachItemList, allComplimentStylesEachItemList;

    //make morelikeThisList for each and also for complimentary


    public ApparelItem(){

        trendingMoreLikeThisEachItemList = new ArrayList<ArrayList<Item>>();
        trendingComplimentStylesEachItemList = new ArrayList<ArrayList<Item>>();

        allMoreLikeThisEachItemList = new ArrayList<>();
        allComplimentStylesEachItemList = new ArrayList<>();

        ArrayList<Item> moreLikeThis = new ArrayList<>();

        ArrayList<Item> complimentaryStyles = new ArrayList<>();


        //the upperwears
        upperwearApparelList = new ArrayList<>();
        upperwearApparelList.add(new Item("Polo", R.drawable.u11, 3.9, 350, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("SHEIN Men Plaid Hooded Shirt", R.drawable.u2, 4.2, 450, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("ROMWE Guys Letter Graphic Tee", R.drawable.u3, 5.8, 400, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("Men Solid Button Front Jacket", R.drawable.u4, 1.2, 300, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("Men Slogan & Picture Print Thermal Lined Sweatshirt", R.drawable.u5, 4.4, 550, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("Men Button Front Shirt", R.drawable.u6, 7.1, 500, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("Embroidered Top", R.drawable.u9, 2.4, 600, moreLikeThis, complimentaryStyles));
        upperwearApparelList.add(new Item("Covered White Top", R.drawable.u8, 4.1, 350, moreLikeThis, complimentaryStyles));



        //the lowers
        bottomwearApparelList = new ArrayList<>();
        bottomwearApparelList.add(new Item("Jeans", R.drawable.p11, 3.3, 450, moreLikeThis, complimentaryStyles));
        bottomwearApparelList.add(new Item("High Waist Straight Leg Pants", R.drawable.p2, 2.5, 350, moreLikeThis, complimentaryStyles));
        bottomwearApparelList.add(new Item("Flap Pocket Side Drawstring Waist Cargo Pants", R.drawable.p3, 5.2, 600, moreLikeThis, complimentaryStyles));
        bottomwearApparelList.add(new Item("Grunge Punk Zipper Flap Pocket Cargo Pants", R.drawable.p4, 2.2, 350, moreLikeThis, complimentaryStyles));
        bottomwearApparelList.add(new Item("Ruched Waist Flare Leg Pants", R.drawable.p5, 5.6, 450, moreLikeThis, complimentaryStyles));
        bottomwearApparelList.add(new Item("High Waist Slant Pockets Corduroy Pants", R.drawable.p6, 3.4, 300, moreLikeThis, complimentaryStyles));




        // the footwear apparels to be shown
        footwearApparelList = new ArrayList<>();
        footwearApparelList.add(new Item("Sneakers", R.drawable.s11, 4.7, 150, moreLikeThis, complimentaryStyles));
        footwearApparelList.add(new Item("Rhinestone Decor Raw Trim Tweed Point Toe Pyramid Heeled Court Pumps", R.drawable.s2, 7, 200, moreLikeThis, complimentaryStyles));
        footwearApparelList.add(new Item("Two Tone Lace-up Front Skate Shoes", R.drawable.s3, 4, 250, moreLikeThis, complimentaryStyles));
        footwearApparelList.add(new Item("Zipper Back Square Toe Chunky Heeled Classic Boots", R.drawable.s4, 4.1, 300, moreLikeThis, complimentaryStyles));
        footwearApparelList.add(new Item("Women Tie Leg Design Chunky Heeled Strappy Sandals, Elegant Summer Heeled Sandals", R.drawable.s5, 3.7, 350, moreLikeThis, complimentaryStyles));
        footwearApparelList.add(new Item("Round Toe Chunky Heeled Ankle Strap Pumps", R.drawable.s6, 5.7, 400, moreLikeThis, complimentaryStyles));






        //shoes more likeThis
        shoesMoreLikeThisEachItemList = new ArrayList<ArrayList<Item>>();

        //more like this apparel 1
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(2));
     //   shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(0).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 2
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(4));
      //  shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(1).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 3
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(0));
     //   shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(2).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 4
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(5));
      //  shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(3).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 5
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(1));
     //   shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(4).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 6
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(3));
      //  shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(5).setMoreLikeThis(moreLikeThisValuesList);




        //shoes compstyles
        shoesComplimentStylesEachItemList = new ArrayList<ArrayList<Item>>();


        //compliments apparel 1
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(bottomwearApparelList.get(0));
        complimentStylesValuesList.add(upperwearApparelList.get(0));
        complimentStylesValuesList.add(bottomwearApparelList.get(5));
        complimentStylesValuesList.add(upperwearApparelList.get(4));
      //  shoesComplimentStylesEachItemList.add(complimentStylesValuesList);
        footwearApparelList.get(0).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 2
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(bottomwearApparelList.get(2));
        complimentStylesValuesList.add(upperwearApparelList.get(6));
        complimentStylesValuesList.add(bottomwearApparelList.get(1));
        complimentStylesValuesList.add(upperwearApparelList.get(7));
       // shoesComplimentStylesEachItemList.add(complimentStylesValuesList);
        footwearApparelList.get(1).setComplimentaryStyles(complimentStylesValuesList);

        //compliments apparel 3
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(bottomwearApparelList.get(3));
        complimentStylesValuesList.add(upperwearApparelList.get(1));
        complimentStylesValuesList.add(bottomwearApparelList.get(4));
        complimentStylesValuesList.add(upperwearApparelList.get(5));
      //  shoesComplimentStylesEachItemList.add(complimentStylesValuesList);
        footwearApparelList.get(2).setComplimentaryStyles(complimentStylesValuesList);

        //compliments apparel 4
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(bottomwearApparelList.get(2));
        complimentStylesValuesList.add(upperwearApparelList.get(6));
        complimentStylesValuesList.add(bottomwearApparelList.get(1));
        complimentStylesValuesList.add(upperwearApparelList.get(7));
       // shoesComplimentStylesEachItemList.add(complimentStylesValuesList);
        footwearApparelList.get(3).setComplimentaryStyles(complimentStylesValuesList);

        //compliments apparel 5
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(bottomwearApparelList.get(0));
        complimentStylesValuesList.add(upperwearApparelList.get(7));
        complimentStylesValuesList.add(bottomwearApparelList.get(5));
        complimentStylesValuesList.add(upperwearApparelList.get(6));
       // shoesComplimentStylesEachItemList.add(complimentStylesValuesList);
        footwearApparelList.get(4).setComplimentaryStyles(complimentStylesValuesList);

        //compliments apparel 6
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(bottomwearApparelList.get(3));
        complimentStylesValuesList.add(upperwearApparelList.get(1));
        complimentStylesValuesList.add(bottomwearApparelList.get(4));
        complimentStylesValuesList.add(upperwearApparelList.get(4));
       // shoesComplimentStylesEachItemList.add(complimentStylesValuesList);
        footwearApparelList.get(5).setComplimentaryStyles(complimentStylesValuesList);







        //upperr


        //upper more likeThis
        upperMoreLikeThisEachItemList = new ArrayList<ArrayList<Item>>();




        //more like this apparel 1
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(footwearApparelList.get(2));
        //   shoesMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        footwearApparelList.get(0).setMoreLikeThis(moreLikeThisValuesList);





        //more like this apparel 1
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(2));
        moreLikeThisValuesList.add(upperwearApparelList.get(5));
        moreLikeThisValuesList.add(upperwearApparelList.get(3));
       // upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(0).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 2
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(3));
        moreLikeThisValuesList.add(upperwearApparelList.get(0));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(1).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 3
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(0));
        moreLikeThisValuesList.add(upperwearApparelList.get(4));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(2).setMoreLikeThis(moreLikeThisValuesList);

        //more like this apparel 4
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(5));
        moreLikeThisValuesList.add(upperwearApparelList.get(0));
        moreLikeThisValuesList.add(upperwearApparelList.get(1));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(3).setMoreLikeThis(moreLikeThisValuesList);



        //more like this apparel 5
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(1));
        moreLikeThisValuesList.add(upperwearApparelList.get(2));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(4).setMoreLikeThis(moreLikeThisValuesList);



        //more like this apparel 6
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(3));
        moreLikeThisValuesList.add(upperwearApparelList.get(1));
        moreLikeThisValuesList.add(upperwearApparelList.get(0));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(5).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 7
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(3));
        moreLikeThisValuesList.add(upperwearApparelList.get(1));
        moreLikeThisValuesList.add(upperwearApparelList.get(0));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(6).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 8
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(upperwearApparelList.get(3));
        moreLikeThisValuesList.add(upperwearApparelList.get(1));
        moreLikeThisValuesList.add(upperwearApparelList.get(0));
//        upperMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        upperwearApparelList.get(7).setMoreLikeThis(moreLikeThisValuesList);




        //upper compstyles
        upperComplimentStylesEachItemList = new ArrayList<ArrayList<Item>>();



        //compliments apparel 1
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(2));
        complimentStylesValuesList.add(bottomwearApparelList.get(0));
        complimentStylesValuesList.add(footwearApparelList.get(0));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(0).setComplimentaryStyles(complimentStylesValuesList);

        //compliments apparel 2
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(bottomwearApparelList.get(2));
        complimentStylesValuesList.add(footwearApparelList.get(0));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(1).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 3
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(bottomwearApparelList.get(0));
        complimentStylesValuesList.add(footwearApparelList.get(2));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(2).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 4
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(2));
        complimentStylesValuesList.add(bottomwearApparelList.get(0));
        complimentStylesValuesList.add(footwearApparelList.get(0));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(3).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 5
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(bottomwearApparelList.get(0));
        complimentStylesValuesList.add(footwearApparelList.get(2));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(4).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 6
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(1));
        complimentStylesValuesList.add(bottomwearApparelList.get(2));
        complimentStylesValuesList.add(footwearApparelList.get(2));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(5).setComplimentaryStyles(complimentStylesValuesList);




        //compliments apparel 7
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(1));
        complimentStylesValuesList.add(bottomwearApparelList.get(2));
        complimentStylesValuesList.add(footwearApparelList.get(5));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(6).setComplimentaryStyles(complimentStylesValuesList);



        //compliments apparel 8
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(1));
        complimentStylesValuesList.add(bottomwearApparelList.get(2));
        complimentStylesValuesList.add(footwearApparelList.get(5));
//        upperComplimentStylesEachItemList.add(complimentStylesValuesList);
        upperwearApparelList.get(7).setComplimentaryStyles(complimentStylesValuesList);








        //bottom


        //bottom more likeThis
        bottomMoreLikeThisEachItemList = new ArrayList<ArrayList<Item>>();




        //more like this apparel 1
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(bottomwearApparelList.get(1));
        moreLikeThisValuesList.add(bottomwearApparelList.get(2));
        moreLikeThisValuesList.add(bottomwearApparelList.get(3));
//        bottomMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        bottomwearApparelList.get(0).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 2
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(bottomwearApparelList.get(2));
        moreLikeThisValuesList.add(bottomwearApparelList.get(0));
        moreLikeThisValuesList.add(bottomwearApparelList.get(5));
//        bottomMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        bottomwearApparelList.get(1).setMoreLikeThis(moreLikeThisValuesList);



        //more like this apparel 3
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(bottomwearApparelList.get(0));
        moreLikeThisValuesList.add(bottomwearApparelList.get(1));
        moreLikeThisValuesList.add(bottomwearApparelList.get(5));
//        bottomMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        bottomwearApparelList.get(2).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 4
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(bottomwearApparelList.get(5));
        moreLikeThisValuesList.add(bottomwearApparelList.get(4));
        moreLikeThisValuesList.add(bottomwearApparelList.get(2));
//        bottomMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        bottomwearApparelList.get(3).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 5
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(bottomwearApparelList.get(1));
        moreLikeThisValuesList.add(bottomwearApparelList.get(3));
        moreLikeThisValuesList.add(bottomwearApparelList.get(5));
//        bottomMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        bottomwearApparelList.get(4).setMoreLikeThis(moreLikeThisValuesList);


        //more like this apparel 6
        moreLikeThisValuesList = new ArrayList<Item>();
        moreLikeThisValuesList.add(bottomwearApparelList.get(2));
        moreLikeThisValuesList.add(bottomwearApparelList.get(1));
        moreLikeThisValuesList.add(bottomwearApparelList.get(0));
//        bottomMoreLikeThisEachItemList.add(moreLikeThisValuesList);
        bottomwearApparelList.get(5).setMoreLikeThis(moreLikeThisValuesList);




        //bottom compstyles
        bottomComplimentStylesEachItemList = new ArrayList<ArrayList<Item>>();



        //compliments apparel 1
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(upperwearApparelList.get(0));
        complimentStylesValuesList.add(footwearApparelList.get(2));
        complimentStylesValuesList.add(upperwearApparelList.get(1));
//        bottomComplimentStylesEachItemList.add(complimentStylesValuesList);
        bottomwearApparelList.get(0).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 2
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(1));
        complimentStylesValuesList.add(upperwearApparelList.get(6));
        complimentStylesValuesList.add(footwearApparelList.get(4));
        complimentStylesValuesList.add(upperwearApparelList.get(7));
//        bottomComplimentStylesEachItemList.add(complimentStylesValuesList);
        bottomwearApparelList.get(1).setComplimentaryStyles(complimentStylesValuesList);



        //compliments apparel 3
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(upperwearApparelList.get(1));
        complimentStylesValuesList.add(footwearApparelList.get(5));
        complimentStylesValuesList.add(upperwearApparelList.get(4));
//        bottomComplimentStylesEachItemList.add(complimentStylesValuesList);
        bottomwearApparelList.get(2).setComplimentaryStyles(complimentStylesValuesList);



        //compliments apparel 4
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(5));
        complimentStylesValuesList.add(upperwearApparelList.get(2));
        complimentStylesValuesList.add(footwearApparelList.get(2));
        complimentStylesValuesList.add(upperwearApparelList.get(4));
//        bottomComplimentStylesEachItemList.add(complimentStylesValuesList);
        bottomwearApparelList.get(3).setComplimentaryStyles(complimentStylesValuesList);


        //compliments apparel 5
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(3));
        complimentStylesValuesList.add(upperwearApparelList.get(7));
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(upperwearApparelList.get(3));
//        bottomComplimentStylesEachItemList.add(complimentStylesValuesList);
        bottomwearApparelList.get(4).setComplimentaryStyles(complimentStylesValuesList);



        //compliments apparel 6
        complimentStylesValuesList = new ArrayList<Item>();
        complimentStylesValuesList.add(footwearApparelList.get(0));
        complimentStylesValuesList.add(upperwearApparelList.get(2));
        complimentStylesValuesList.add(footwearApparelList.get(2));
        complimentStylesValuesList.add(upperwearApparelList.get(3));
//        bottomComplimentStylesEachItemList.add(complimentStylesValuesList);
        bottomwearApparelList.get(5).setComplimentaryStyles(complimentStylesValuesList);








        // the trends to be shown
        trendingApparelList = new ArrayList<>();


        getTrendingList(trendingApparelList, footwearApparelList, trendingMoreLikeThisEachItemList, trendingComplimentStylesEachItemList);
        getTrendingList(trendingApparelList, upperwearApparelList, trendingMoreLikeThisEachItemList, trendingComplimentStylesEachItemList);
        getTrendingList(trendingApparelList, bottomwearApparelList,  trendingMoreLikeThisEachItemList, trendingComplimentStylesEachItemList);


        allApparelList = new ArrayList<>();

        getAllList(allApparelList, upperwearApparelList,  bottomwearApparelList, footwearApparelList);




    }

    private void getAllList(ArrayList<Item> t, ArrayList<Item> upperList, ArrayList<Item> bottomList, ArrayList<Item> footwearList) {
        int size = Math.max(Math.max(upperList.size(), bottomList.size()), footwearList.size());

        allMoreLikeThisEachItemList = new ArrayList<>();
        allComplimentStylesEachItemList = new ArrayList<>();

        for(int i = 0; i < size; i++){


            if(i < upperList.size()) {
                allApparelList.add(upperList.get(i));

//                allMoreLikeThisEachItemList.add(upperList.get(i).getMoreLikeThis());
//                allComplimentStylesEachItemList.add(upperList.get(i).getComplimentaryStyles());
            }
            if(i < bottomList.size()) {
                allApparelList.add(bottomList.get(i));
//                allMoreLikeThisEachItemList.add(bottomList.get(i).getMoreLikeThis());
//                allComplimentStylesEachItemList.add(bottomList.get(i).getComplimentaryStyles());
            }

            if(i < footwearList.size()) {
                allApparelList.add(footwearList.get(i));
//                allMoreLikeThisEachItemList.add(footwearList.get(i).getMoreLikeThis());
//                allComplimentStylesEachItemList.add(footwearList.get(i).getComplimentaryStyles());
            }

        }
    }


    private void getTrendingList(ArrayList<Item> t, ArrayList<Item> apparelList, ArrayList<ArrayList<Item>> m, ArrayList<ArrayList<Item>> c ) {



        boolean match = false;
        for(int j = 0; j < 2; j++){

            int position = 0;
            double max = apparelList.get(0).getTimesSold();
            for(int i = 1; i < apparelList.size(); i++){
                if(apparelList.get(i).getTimesSold() > max){
                    for(int l = 0; l < trendingApparelList.size(); l++){
                        if(apparelList.get(i).getTimesSold()==trendingApparelList.get(l).getTimesSold())
                            match = true;
                    }

                    if(match)
                        continue;

                    position = i;
                    max = apparelList.get(i).getTimesSold();
                }

            }
            t.add(apparelList.get(position));

//            trendingMoreLikeThisEachItemList.add(apparelList.get(position).getMoreLikeThis());
//            trendingComplimentStylesEachItemList.add(apparelList.get(position).getMoreLikeThis());
//
//



        }
    }


    public void setAllApparelList(ArrayList<Item> allApparelList) {
        this.allApparelList = allApparelList;
    }

    public ArrayList<Item> getAllApparelList() {
        return this.allApparelList;
    }


    public void setFootwearApparelList(ArrayList<Item> footwearApparelList) {
        this.footwearApparelList = footwearApparelList;
    }

    public ArrayList<Item> getFootwearApparelList() {
        return this.footwearApparelList;
    }

    // Setter and Getter for moreLikeThisValuesList
    public void setMoreLikeThisValuesList(ArrayList<Item> moreLikeThisValuesList) {
        this.moreLikeThisValuesList = moreLikeThisValuesList;
    }

    public ArrayList<Item> getMoreLikeThisValuesList() {
        return this.moreLikeThisValuesList;
    }

    // Setter and Getter for complimentStylesValuesList
    public void setComplimentStylesValuesList(ArrayList<Item> complimentStylesValuesList) {
        this.complimentStylesValuesList = complimentStylesValuesList;
    }

    public ArrayList<Item> getComplimentStylesValuesList() {
        return this.complimentStylesValuesList;
    }

    // Setter and Getter for moreLikeThisEachItemList
    public void setShoesMoreLikeThisEachItemList(ArrayList<ArrayList<Item>> shoesMoreLikeThisEachItemList) {
        this.shoesMoreLikeThisEachItemList = shoesMoreLikeThisEachItemList;
    }

    public ArrayList<ArrayList<Item>> getShoesMoreLikeThisEachItemList() {
        return this.shoesMoreLikeThisEachItemList;
    }

    // Setter and Getter for complimentStylesEachItemList
    public void setShoesComplimentStylesEachItemList(ArrayList<ArrayList<Item>> shoesComplimentStylesEachItemList) {
        this.shoesComplimentStylesEachItemList = shoesComplimentStylesEachItemList;
    }

    public ArrayList<ArrayList<Item>> getShoesComplimentStylesEachItemList() {
        return this.shoesComplimentStylesEachItemList;
    }

    public void setUpperwearApparelList(ArrayList<Item> upperwearApparelList) {
        this.upperwearApparelList = upperwearApparelList;
    }

    public ArrayList<Item> getUpperwearApparelList() {
        return this.upperwearApparelList;
    }

    // Setter and Getter for bottomwearApparelList
    public void setBottomwearApparelList(ArrayList<Item> bottomwearApparelList) {
        this.bottomwearApparelList = bottomwearApparelList;
    }

    public ArrayList<Item> getBottomwearApparelList() {
        return this.bottomwearApparelList;
    }

    // Setter and Getter for trendingApparelList
    public void setTrendingApparelList(ArrayList<Item> trendingApparelList) {
        this.trendingApparelList = trendingApparelList;
    }

    public ArrayList<Item> getTrendingApparelList() {
        return this.trendingApparelList;
    }


    public ArrayList<ArrayList<Item>> getUpperMoreLikeThisEachItemList() {
        return this.upperMoreLikeThisEachItemList;
    }

    public void setUpperMoreLikeThisEachItemList(ArrayList<ArrayList<Item>> upperMoreLikeThisEachItemList) {
        this.upperMoreLikeThisEachItemList = upperMoreLikeThisEachItemList;
    }

    public ArrayList<ArrayList<Item>> getUpperComplimentStylesEachItemList() {
        return this.upperComplimentStylesEachItemList;
    }

    public void setUpperComplimentStylesEachItemList(ArrayList<ArrayList<Item>> upperComplimentStylesEachItemList) {
        this.upperComplimentStylesEachItemList = upperComplimentStylesEachItemList;
    }

    public ArrayList<ArrayList<Item>> getBottomMoreLikeThisEachItemList() {
        return this.bottomMoreLikeThisEachItemList;
    }

    public void setBottomMoreLikeThisEachItemList(ArrayList<ArrayList<Item>> bottomMoreLikeThisEachItemList) {
        this.bottomMoreLikeThisEachItemList = bottomMoreLikeThisEachItemList;
    }

    public ArrayList<ArrayList<Item>> getBottomComplimentStylesEachItemList() {
        return this.bottomComplimentStylesEachItemList;
    }

    public void setBottomComplimentStylesEachItemList(ArrayList<ArrayList<Item>> bottomComplimentStylesEachItemList) {
        this.bottomComplimentStylesEachItemList = bottomComplimentStylesEachItemList;
    }

    public ArrayList<ArrayList<Item>> getTrendingMoreLikeThisEachItemList() {
        return this.trendingMoreLikeThisEachItemList;
    }

    public void setTrendingMoreLikeThisEachItemList(ArrayList<ArrayList<Item>> trendingMoreLikeThisEachItemList) {
        this.trendingMoreLikeThisEachItemList = trendingMoreLikeThisEachItemList;
    }

    public ArrayList<ArrayList<Item>> getTrendingComplimentStylesEachItemList() {
        return this.trendingComplimentStylesEachItemList;
    }

    public void setTrendingComplimentStylesEachItemList(ArrayList<ArrayList<Item>> trendingComplimentStylesEachItemList) {
        this.trendingComplimentStylesEachItemList = trendingComplimentStylesEachItemList;
    }

    public ArrayList<ArrayList<Item>> getAllMoreLikeThisEachItemList() {
        return this.allMoreLikeThisEachItemList;
    }

    public void setAllMoreLikeThisEachItemList(ArrayList<ArrayList<Item>> allMoreLikeThisEachItemList) {
        this.allMoreLikeThisEachItemList = allMoreLikeThisEachItemList;
    }

    public ArrayList<ArrayList<Item>> getAllComplimentStylesEachItemList() {
        return this.allComplimentStylesEachItemList;
    }

    public void setAllComplimentStylesEachItemList(ArrayList<ArrayList<Item>> allComplimentStylesEachItemList) {
        this.allComplimentStylesEachItemList = allComplimentStylesEachItemList;
    }



}
