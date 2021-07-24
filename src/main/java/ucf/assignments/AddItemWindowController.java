/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
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
        //Throw Error message window if serial number already exists
        if (itemModel.isSerialNumberUnique(serialNumber)) {
            //creates the new item with the values from the text boxes
            Item item = new Item(value, serialNumber, name);
            //adds item the list
            itemModel.addItem(item);
            Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
            //closes the stage and returns to the main window
            stage.close();
        } else {
            openSerialNumberErrorMessage();
        }
    }

    //This function will open the serial number error window when called
    private void openSerialNumberErrorMessage() {
        Stage stage = new Stage();
        Parent root = null;
        //Loads new window
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ucf.assignments/SerialNumberErrorMessage.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Error Message Window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    //Takes the itemModel from where it was called
    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
