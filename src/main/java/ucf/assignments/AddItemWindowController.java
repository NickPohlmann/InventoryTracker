/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.math.BigDecimal;

public class AddItemWindowController {
    private ItemModel itemModel;


    @FXML
    private TextField addItemValueTextBox;

    @FXML
    private TextField addItemSerialNumberTextBox;

    @FXML
    private TextField addItemNameTextBox;

    @FXML
    private Button addNewItemButton;

    @FXML
    void addNewItemButtonClicked(ActionEvent event) {
        //Changes the String from the text box to a double and then changes it to a BigDecimal and assigns it to value
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(addItemValueTextBox.getText()));
        //Assigns the String from the text box to serialNumber
        String serialNumber = addItemSerialNumberTextBox.getText();
        //Assigns the String from the text box to name
        String name = addItemNameTextBox.getText();
        //creates the new item with the values from the text boxes
        Item item = new Item(value, serialNumber, name);
        //adds item the list
        itemModel.addItem(item);
        Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        //closes the stage and returns to the main window
        stage.close();
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
