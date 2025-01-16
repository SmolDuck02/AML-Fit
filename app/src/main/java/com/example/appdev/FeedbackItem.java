package com.example.appdev;

public class FeedbackItem {

    int image;
    String itemName;
    int itemPrice;

    public FeedbackItem(int image, String itemName, int itemPrice){
        this.image = image;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }


    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }


}
