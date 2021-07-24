/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class SearchWindowController {
    private ItemModel itemModel;

    @FXML
    private TextField searchTextBox;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Item> TableView;

    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    @FXML
    private TableColumn<Item, String> serialNumberColumn;

    @FXML
    private TableColumn<Item, String> nameColumn;

    //This function will take in the text for the text box and put any that matches the list into the table
    @FXML
    void searchButtonClicked(ActionEvent event) {
        ObservableList<Item> searchedList =  FXCollections.observableArrayList();
        //This will get the string from the text box
        String searchedString = searchTextBox.getText();
        //calls the function searchList from ItemModel and pass in the text box
        searchedList = itemModel.searchList(searchedString);
        //Tells the table to show current itemModel
        TableView.setItems(searchedList);
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }

    //This function will set the itemModel
    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
