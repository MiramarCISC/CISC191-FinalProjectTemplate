package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InventoryTable extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        showTable();
    }

    private void showTable() {
        Scene scene = new Scene(new BorderPane());
        final Label label = new Label("Inventory of Holds (Mesa Rim Mira Mesa)");

        // Data the TableView displays
        ObservableList<Inventory> tableData = FXCollections.observableArrayList();

        // Create the table
        TableView<Inventory> table = new TableView<>();
        table.setItems(tableData);

        // columns for table
        TableColumn<Inventory, String> holdType = new TableColumn<Inventory, String>("Hold type");
        holdType.setMinWidth(100);
        holdType.setCellValueFactory(new PropertyValueFactory<Inventory, String>("holdType"));

        TableColumn<Inventory, String> manufacture = new TableColumn<>("Manufacture");
        manufacture.setMinWidth(100);
        manufacture.setCellValueFactory(new PropertyValueFactory<Inventory, String>("manufacture"));

        TableColumn<Inventory, String> mountingOption = new TableColumn<>("Mounting Option");
        mountingOption.setMinWidth(200);
        mountingOption.setCellValueFactory(new PropertyValueFactory<Inventory, String>("mountingOption"));

        TableColumn<Inventory, String> texture = new TableColumn<Inventory, String>("Texture");
        texture.setMinWidth(200);
        texture.setCellValueFactory(new PropertyValueFactory<Inventory, String>("texture"));

        TableColumn<Inventory, String> difficulty = new TableColumn<>("Difficulty");
        difficulty.setMinWidth(200);
        difficulty.setCellValueFactory(new PropertyValueFactory<Inventory, String>("difficulty"));

        TableColumn<Inventory, String> color = new TableColumn<Inventory, String>("Color");
        color.setMinWidth(200);
        color.setCellValueFactory(new PropertyValueFactory<Inventory, String>("color"));

        TableColumn<Inventory, String> size = new TableColumn<>("Size");
        size.setMinWidth(200);
        size.setCellValueFactory(new PropertyValueFactory<Inventory, String>("size"));

        table.getColumns().addAll(holdType, manufacture, mountingOption, texture, difficulty, color, size);

        // TextField for inventory input
        TextField addHoldType = new TextField();
        addHoldType.setPromptText("Hold Type");
        addHoldType.setMaxWidth(holdType.getPrefWidth());

        TextField addManufacture = new TextField();
        addManufacture.setMaxWidth(manufacture.getPrefWidth());
        addManufacture.setPromptText("Manufacture");

        TextField addMountingOption = new TextField();
        addMountingOption.setMaxWidth(mountingOption.getPrefWidth());
        addMountingOption.setPromptText("Mounting Option");

        TextField addTexture = new TextField();
        addTexture.setMaxWidth(texture.getPrefWidth());
        addTexture.setPromptText("Texture");

        TextField addDifficulty = new TextField();
        addDifficulty.setMaxWidth(difficulty.getPrefWidth());
        addDifficulty.setPromptText("Difficulty");

        TextField addColor = new TextField();
        addColor.setMaxWidth(color.getPrefWidth());
        addColor.setPromptText("Color");

        TextField addSize = new TextField();
        addSize.setMaxWidth(texture.getPrefWidth());
        addSize.setPromptText("Size");

        // Button to add a new hold
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {

            Inventory newInventory = new Inventory(addHoldType.getText(), addManufacture.getText(),
                    addMountingOption.getText(), addTexture.getText(), addDifficulty.getText(),
                    addColor.getText(), addSize.getText());

            // Add the new element to the table data
            // clears the textfield for new entry
            tableData.add(newInventory);
            addHoldType.clear();
            addManufacture.clear();
            addMountingOption.clear();
            addTexture.clear();
            addDifficulty.clear();
            addColor.clear();
            addSize.clear();
        });

        // Button to remove a hold
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            Inventory selectedItem = table.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                // Remove the item from the list of the displayed holds
                tableData.remove(selectedItem);
            }
        });

        HBox textFieldInput = new HBox();
        textFieldInput.getChildren().addAll(addHoldType, addManufacture, addMountingOption, addTexture,
                addDifficulty, addColor, addSize, addButton, removeButton);
        textFieldInput.setSpacing(3);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, textFieldInput);

        BorderPane root = (BorderPane) scene.getRoot();
        root.setCenter(vbox);

        Stage stage = new Stage();

        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10, 0, 0, 10));

        // Save button
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #3f6ab6; -fx-text-fill: white");

        // Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #3f6ab6; -fx-text-fill: white");
        cancelButton.setOnAction(e -> stage.close());

        hbox.getChildren().addAll(saveButton, cancelButton);

        // create scene, stage, set title, and show
        root.setBottom(hbox);
        stage.setScene(scene);
        stage.show();
    }
}


