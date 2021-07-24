/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;

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

    @FXML
    void searchButtonClicked(ActionEvent event) {
        ItemModel searchedItemModel = new ItemModel();
        //This will get the string from the text box
        String searchedString = searchTextBox.getText();
        for (Item item : itemModel.getList()) {
            //if the string in the search box is a name or serial number the add it to the new item model
            if(item.getName().equals(searchedString) || item.getSerialNumber().equals(searchedString)) {
                searchedItemModel.addItem(item);
            }
        }
        //Tells the table to show current itemModel
        TableView.setItems(searchedItemModel.getList());

        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }

    //This function will set the itemModel
    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
