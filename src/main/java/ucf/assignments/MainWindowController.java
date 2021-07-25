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
    private Button searchItemButton;

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
        stage.setTitle("Add Item Window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)actionEvent.getSource()).getScene().getWindow() );
        AddItemWindowController addItemWindowController = loader.getController();
        //Passes AddItemWindowController the current itemModel
        addItemWindowController.setItemModel(itemModel);
        stage.show();
    }

    //This will open SearchWindow when the search button is clicked
    @FXML
    void searchItemClicked(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        //Loads new window
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ucf.assignments/SearchWindow.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        //sets title of window to Search Window
        stage.setTitle("Search Window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow() );
        SearchWindowController searchWindowController = loader.getController();
        //Passes SearchWindowController the current itemModel
        searchWindowController.setItemModel(itemModel);
        stage.show();
    }

    //This will export the list into a .txt, .html, or .json file
    @FXML
    void saveAsClicked(ActionEvent event) {
        FileChooser exportfileChooser = new FileChooser();
        exportfileChooser.setTitle("Export File");
        File file = exportfileChooser.showSaveDialog(stage);
        if (file != null) {
            itemModel.generateExportFile(file);
        }
    }

    //This will import any prior created InventoryList
    @FXML
    void importListClicked(ActionEvent event) {
        FileChooser importfileChooser = new FileChooser();
        importfileChooser.setTitle("Select File");
        File file = importfileChooser.showOpenDialog(stage);
        //if the file exists, open and load it
        if (file != null) {
            itemModel.importItemsFromFile(file);
        }
    }

    //This will set the table and allow the table view to be editable
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemModel = new ItemModel();
        //Tells the table to show current itemModel
        table.setItems(itemModel.getList());
        //Makes the table editable
        table.setEditable(true);

        //This will allow the value to be editable
        value.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        value.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, BigDecimal>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, BigDecimal> t) {
                ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());
            }
        });

        //This will allow the serial number to be editable
        serialNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
                editSerialNumber(t);

            }
        });

        //This will allow the name to be editable
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {
                ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });

        //This sets the value, serial number and name to the proper columns
        value.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("value"));
        serialNumber.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }

    //This checks that the serial number when edited is unique
    public void editSerialNumber(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        Item item = table.getSelectionModel().getSelectedItem();
        //if the serial number is unique set it as the value
        if (itemModel.isSerialNumberUnique(item.getSerialNumber())){
            item.setSerialNumber(itemStringCellEditEvent.getNewValue());
        //else, open the serial number error message window
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

            //refresh cell to show saved (old) value
            itemStringCellEditEvent.getTableView().getColumns().get(itemStringCellEditEvent.getTablePosition().getRow()).setVisible(false);
            itemStringCellEditEvent.getTableView().getColumns().get(itemStringCellEditEvent.getTablePosition().getRow()).setVisible(true);
        }
    }
}
