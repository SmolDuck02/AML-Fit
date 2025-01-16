package com.example.appdev;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Item>> itemsLiveData = new MutableLiveData<>();

    public void setItems(ArrayList<Item> items) {
        itemsLiveData.setValue(items);
    }

    public LiveData<ArrayList<Item>> getItems() {
        return itemsLiveData;
    }
}