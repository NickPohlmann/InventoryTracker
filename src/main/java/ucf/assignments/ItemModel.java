/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Currency;

public class ItemModel {

    //This list will hold all of the items in the inventory tracker
    private ObservableList<Item> list;

    //constructor for the array list
    public ItemModel() {
        this.list = FXCollections.observableArrayList();
    }

    //This function will add a new item to the current array list
    public Boolean addItem(Item item) {
        if(isSerialNumberUnique(item.getSerialNumber())) {
            list.add(item);
            return true;
        }
        return false;
    }

    //This function will remove an item from the array list
    public void removeItem(Item item) {
        list.remove(item);
    }

    //This function will take in a serial number and check if it already exists
    public Boolean isSerialNumberUnique(String serialNumber) {
        //Loop through list to check each serial number
        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            if (item.getSerialNumber().equals(serialNumber)) {
                return false;
            }
        }
        return true;
    }

    //This function will take in a string and search the list for any exact names or serial numbers (case sensitive)
    public ObservableList<Item> searchList(String searchString) {
        ObservableList<Item> searchedList = FXCollections.observableArrayList();
        //Loop through list to check for exact match
        for (Item item : list) {
            if (item.getSerialNumber().equals(searchString) || item.getName().equals(searchString)) {
                searchedList.add(item);
            }
        }
        return searchedList;
    }

}
