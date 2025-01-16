package com.example.appdev;

import java.util.ArrayList;

public class User {
    private String name;
    private String number;
    private String location;
    private String email;
    private String username;
    private String password;

    String cartItemList;
    String trackOrderItemList;
    String feedbackItemList;


    public User(String name, String number, String location, String email, String username, String password, String cartItemList, String trackOrderItemList, String feedbackItemList) {
        this.name = name;
        this.number = number;
        this.location = location;
        this.email = email;
        this.username = username;
        this.password = password;

        this.cartItemList = cartItemList;
        this.trackOrderItemList = trackOrderItemList;
        this.feedbackItemList = feedbackItemList;

    }


    public String getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(String cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getTrackOrderItemList() {
        return trackOrderItemList;
    }

    public void setTrackOrderItemList(String trackOrderItemList) {
        this.trackOrderItemList = trackOrderItemList;
    }

    public String getFeedbackItemList() {
        return feedbackItemList;
    }

    public void setFeedbackItemList(String feedbackItemList) {
        this.feedbackItemList = feedbackItemList;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
