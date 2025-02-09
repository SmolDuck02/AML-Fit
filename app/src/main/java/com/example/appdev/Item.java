package com.example.appdev;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private String title;
    private double timesSold;
    private int price;
    private int image;

    ArrayList<Item> moreLikeThis;

    ArrayList<Item> complimentaryStyles;



    public Item(String title, int image, double sold, int price, ArrayList<Item> moreLikeThis, ArrayList<Item> complimentaryStyles){
        this.title = title;
        this.image = image;
        this.timesSold = sold;
        this.price = price;
        this.moreLikeThis = moreLikeThis;
        this.complimentaryStyles = complimentaryStyles;
    }


    public ArrayList<Item> getMoreLikeThis() {
        return moreLikeThis;
    }

    public void setMoreLikeThis(ArrayList<Item> moreLikeThis) {
        this.moreLikeThis = moreLikeThis;
    }

    public ArrayList<Item> getComplimentaryStyles() {
        return complimentaryStyles;
    }

    public void setComplimentaryStyles(ArrayList<Item> complimentaryStyles) {
        this.complimentaryStyles = complimentaryStyles;
    }


    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void getImage(int image){
        this.image = image;
    }

    public int getImage(){
        return this.image;
    }

    public double getTimesSold() {
        return this.timesSold;
    }

    public void setTimesSold(double timesSold) {
        this.timesSold = timesSold;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
