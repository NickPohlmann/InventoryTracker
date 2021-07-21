/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Currency;

public class ItemModel {

    //This list will hold all of the items in the inventory tracker
    private ObservableList<Item> list;

    //setter for the array list
    public ItemModel(ObservableList<Item> list) {
        this.list = list;
    }

    //This function will add a new item to the current array list
    public void addItem (Currency value, String serialNumber, String name) {
        //create new item
        Item item = new Item(value, serialNumber, name);
        //add to list
        list.add(item);
    }

    //This function will remove an item from the array list
    public void removeItem (Item item) {
        list.remove(item);
    }

}
