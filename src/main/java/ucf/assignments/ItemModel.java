/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Scanner;

public class ItemModel {

    //This list will hold all of the items in the inventory tracker
    private ObservableList<Item> list;

    //constructor for the list
    public ItemModel() {
        this.list = FXCollections.observableArrayList();
    }

    //getter for the list
    public ObservableList<Item> getList () {
        return this.list;
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

    //This imports a file that was previously created by the program
    public void importItemsFromFile(File file) {
        Scanner in = null;
        try {
            in = new Scanner(file);
            //Scan file till no more lines
            while (in.hasNextLine()) {
                String data = in.nextLine();
                //the data is separated by tabs
                String[] splitData = data.split("\t");
                //Turns splitData[0] from str into a BigDecimal
                BigDecimal value = BigDecimal.valueOf(Double.parseDouble(splitData[0]));
                //creates the item
                Item item = new Item(value, splitData[1], splitData[2]);
                //Use the addItem function to add the item that was just created
                addItem(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This function will create a file to export
    public void generateExportFile(File file) {

        //Break file into multiple str by splitting on "."
        String[] fileNameSplit = file.getName().split("\\.");
        //Get the last str which will be the .txt, .html, .json
        String fileExtensionSplit = fileNameSplit[fileNameSplit.length - 1];
        //If statement to call the right function to create the right type of file
        if (fileExtensionSplit.equals("txt")) {
            createTextFile(file);
        } else if (fileExtensionSplit.equals("html")) {
            createHTMLFile(file);
        }
    }

    private void createHTMLFile(File file) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write("<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<title>Inventory Tracker</title>" +
                    "</head>" +
                    "<table style='width:100%'>");
            for (Item item : list) {
                bw.write("<tr>"+
                        "<th>" + item.getValue() + "</th>" +
                        "<th>" + item.getSerialNumber() + "</th>" +
                        "<th>" + item.getName() + "</th>" +
                        "</tr>"
                );
            }
            bw.write("</table>"+
                    "</html>");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTextFile(File file) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            for (Item item : list) {
                fw.write(item.getValue() + "\t" + item.getSerialNumber() + "\t" + item.getName() + "\n");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
