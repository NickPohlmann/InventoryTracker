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
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(addItemValueTextBox.getText()));
        String serialNumber = addItemSerialNumberTextBox.getText();
        String name = addItemNameTextBox.getText();
        Item item = new Item(value, serialNumber, name);
        itemModel.addItem(item);
        Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        stage.close();
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
