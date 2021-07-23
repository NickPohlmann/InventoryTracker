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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Currency;

public class MainWindowController {
    private ItemModel itemModel;

    @FXML
    private Button addItemButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button editItemButton;


    @FXML
    private MenuItem saveAsClicked;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, Currency> value;

    @FXML
    private TableColumn<Item, String> serialNumber;

    @FXML
    private TableColumn<Item, String> name;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
        itemModel = new ItemModel();
    }

    @FXML
    void removeItemClicked(ActionEvent event) {
        Item item = table.getSelectionModel().getSelectedItem();
        itemModel.removeItem(item);
    }

    @FXML
    public void addItemClicked(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ucf.assignments/AddItemWindow.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        AddItemWindowController addItemWindowController = loader.getController();
        addItemWindowController.setItemModel(itemModel);
        stage.show();
    }

    @FXML
    void editItemClicked(ActionEvent event) {

    }

}
