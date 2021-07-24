/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.converter.BigDecimalStringConverter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private ItemModel itemModel;

    @FXML
    private Button addItemButton;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button editItemButton;

    @FXML
    private MenuItem saveAs;

    @FXML
    private MenuItem importList;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, BigDecimal> value;

    @FXML
    private TableColumn<Item, String> serialNumber;

    @FXML
    private TableColumn<Item, String> name;

    private Stage stage;

    //Sets stage for Inventory Tracker
    public void setStage(Stage stage){
        this.stage=stage;
    }

    //Removes item form list
    @FXML
    void removeItemClicked(ActionEvent event) {
        //This assigns item as the selected one in the list
        Item item = table.getSelectionModel().getSelectedItem();
        //This removes the selected item
        itemModel.removeItem(item);
    }

    //Opens AddItemWindow
    @FXML
    public void addItemClicked(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        //Loads new window
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
        //Passes AddItemWindowController the current itemModel
        addItemWindowController.setItemModel(itemModel);
        stage.show();
    }

    //This will export the list into a .txt, .html, or .json file
    @FXML
    void saveAsClicked(ActionEvent event) {
        FileChooser exportfileChooser = new FileChooser();
        exportfileChooser.setTitle("Export File");
        File file = exportfileChooser.showSaveDialog(stage);
        if (file != null) {
            String fileName = exportfileChooser.getInitialFileName();
            itemModel.generateExportFile(file);
        }
    }

    @FXML
    void importListClicked(ActionEvent event) {
        FileChooser importfileChooser = new FileChooser();
        importfileChooser.setTitle("Select File");
        File file = importfileChooser.showOpenDialog(stage);
        if (file != null) {
            itemModel.importItemsFromFile(file);
            itemModel = new ItemModel();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemModel = new ItemModel();
        //Tells the table to show current itemModel
        table.setItems(itemModel.getList());
        //Makes the table editable
        table.setEditable(true);

        value.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        value.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, BigDecimal>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, BigDecimal> t) {
                ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());
            }
        });

        serialNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
                editSerialNumber(t);
            }
        });
//        serialNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<Item, String> t) {
//                editSerialNumber(t.getTableView().getItems().get(t.getTablePosition().getRow())));
//                ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).editSerialNumber(t.getNewValue());
//            }
//        });

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
                ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });

        value.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));
        serialNumber.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }

    //This sets the edited value from the table to the item
    public void editValue(TableColumn.CellEditEvent<Item, BigDecimal> todoItemStringCellEditEvent) {
        Item item = table.getSelectionModel().getSelectedItem();
        item.setValue(todoItemStringCellEditEvent.getNewValue());
    }

    //This sets the edited serial number from the table to the item
    public void editSerialNumber(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        Item item = table.getSelectionModel().getSelectedItem();
        if (itemModel.isSerialNumberUnique(item.getSerialNumber())){
            item.setSerialNumber(itemStringCellEditEvent.getNewValue());
        } else {
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
            itemStringCellEditEvent.getOldValue();
        }
    }

    //This sets the edited name from the table to the item
    public void editName(TableColumn.CellEditEvent<Item, String> todoItemStringCellEditEvent) {
        Item item = table.getSelectionModel().getSelectedItem();
        item.setName(todoItemStringCellEditEvent.getNewValue());
    }

    public void editItemClicked(ActionEvent actionEvent) {
    }


}
