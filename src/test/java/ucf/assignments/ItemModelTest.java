/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemModelTest {

    //This will test the addItem function and getList function
    @Test
    void testAddItemAndGetList() {
        //Create itemModel which will use a loop to add the items
        ItemModel itemModel = new ItemModel();
        for (int i = 0; i < 8; i++) {
            BigDecimal value = BigDecimal.valueOf(19.87);
            String serialNumber = String.format("asdfghjkl%d",i);
            String name = "test";
            Item item = new Item(value, serialNumber, name);
            itemModel.addItem(item);
        }
        //Manually create a list to compare itemModel.getList too
        ObservableList<Item> expectedList = FXCollections.observableArrayList();
        for (int i = 0; i < 8; i++) {
            BigDecimal value = BigDecimal.valueOf(19.87);
            String serialNumber = String.format("asdfghjkl%d",i);
            String name = "test";
            Item item = new Item(value, serialNumber, name);
            expectedList.add(item);
        }
        //Turn the manually created list into a string so it can be compared
        String expected = "";
        for (int i = 0; i < 8; i++) {
            double value = expectedList.get(i).getValue().doubleValue();
            expected += String.format("%.2f\t%s\t%s\n",value,expectedList.get(i).getSerialNumber(), expectedList.get(i).getName());
        }
        //Turn itemModel.getList into a string so it can be compared
        String actual = "";
        for (int i = 0; i < 8; i++) {
            double value = itemModel.getList().get(i).getValue().doubleValue();
            actual += String.format("%.2f\t%s\t%s\n",value,itemModel.getList().get(i).getSerialNumber(), itemModel.getList().get(i).getName());
        }
        assertEquals(expected, actual);
    }

    //This will test removeItem
    @Test
    void testRemoveItem() {
        //Create itemModel which will use a loop to add the items
        ItemModel itemModel = new ItemModel();
        for (int i = 0; i < 8; i++) {
            BigDecimal value = BigDecimal.valueOf(19.87);
            String serialNumber = String.format("asdfghjkl%d",i);
            String name = "test";
            Item item = new Item(value, serialNumber, name);
            itemModel.addItem(item);
        }
        //Manually decrease the size of the list by one
        int expected = itemModel.getList().size() - 1;
        //Call removeItem
        itemModel.removeItem(itemModel.getList().get(3));
        //Set actual to the size of the list after the item has been removed
        int actual = itemModel.getList().size();
        assertEquals(expected, actual);
    }
}
