package com.example.appdev;

import java.util.ArrayList;

public class Account {

    ArrayList<User> userList;


    public Account(ArrayList<User> userList){
        this.userList = userList;

    }


    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

}
