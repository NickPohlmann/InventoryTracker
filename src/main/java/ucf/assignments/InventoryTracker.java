/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Nicholas Pohlmann
 */

package ucf.assignments;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryTracker extends Application{

    //Basic start up
    public static void main(String[] args) {
        launch(args);
    }

    //This will open the primary stage
    @Override
    public void start(Stage primaryStage) {
        try {
            //opens MainWindow
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ucf.assignments/MainWindow.fxml"));
            Parent root = loader.load();
            MainWindowController controller = loader.getController();
            controller.setStage(primaryStage);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            //Set title of main window to Inventory Tracker
            primaryStage.setTitle("Inventory Tracker");
            //This will actually put the Main Window to the screen
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
