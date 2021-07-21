/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainWindowController {
    @FXML
    private MenuItem saveAsClicked;

    @FXML
    private Button addItemClicked;

    @FXML
    private Button removeItemClicked;

    @FXML
    private Button editItemClicked;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }
}
