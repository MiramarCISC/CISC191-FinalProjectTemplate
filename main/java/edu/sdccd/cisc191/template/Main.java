package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Scene that launches main
 * GUI contains a menubar to access Mesa Rim Hold Inventory
 */

public class Main extends Application {

    // launch the application
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage)  {
        // set up BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #3f6ab6");

        // create VBox for top
        VBox topContainer = new VBox();

        // create menu bar
        MenuBar menuBar = new MenuBar();

        // create toolbar
        ToolBar toolBar = new ToolBar();

        // add menu to vbox top container
        topContainer.getChildren().add(menuBar);
        topContainer.getChildren().add(toolBar);

        // add vbox to top border of BorderPane object
        borderPane.setTop(topContainer);

        // add items to the main menu
        Menu file = new Menu("File");
        Menu settings = new Menu("Settings");

        // create and add 'File' sub menu options
        MenuItem overview = new MenuItem("Overview");
        overview.setOnAction(e -> {
            Overview overView = new Overview();
            overView.start(stage);
        });

        MenuItem inventory = new MenuItem("Open Inventory");
        inventory.setOnAction(e -> {
            InventoryTable inventoryTable = new InventoryTable();
            inventoryTable.start(stage);
        });

        MenuItem print = new MenuItem("Print");


        // sub menu items added to file menu
        file.getItems().addAll(overview, inventory, print);

        // create and add 'Settings' sub menu options
        // exit closes application
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Platform.exit());

        settings.getItems().addAll(exit);

        menuBar.getMenus().addAll(file, settings);

        // create an Image object.
        Image routesettingImg = new Image("file:///Users/debbienguyen/Desktop/routesetting.png");

        // create an ImageView object.
        ImageView imageView = new ImageView(routesettingImg);

        // set the size of the image to 300x300.
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);

        // preserve the Image's Aspect Ratio.
        imageView.setPreserveRatio(true);

        // add imageView to center of BorderPane
        borderPane.setCenter(imageView);

        // create a Scene with the BorderPane as its root node.
        Scene scene = new Scene(borderPane, 500, 500);
        stage.setTitle("Mesa Rim Climbing Holds Management");
        stage.setScene(scene);
        stage.show();
    }
}

