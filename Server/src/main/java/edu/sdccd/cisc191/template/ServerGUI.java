package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerGUI extends Application {
    private Inventory inventory;
    private ObservableList<Car> carList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Car Inventory Tracker");

        inventory = loadInventoryFromFile();
        if (inventory == null) {
            inventory = new Inventory();
        }
        carList = FXCollections.observableArrayList(inventory.getAllCars());

        ListView<Car> carListView = new ListView<>();
        carListView.setItems(carList);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(15, 35, 15, 25));

        Label makeLabel = new Label("Make:");
        TextField makeTextField = new TextField();
        Label modelLabel = new Label("Model:");
        TextField modelTextField = new TextField();
        Label yearLabel = new Label("Year:");
        TextField yearTextField = new TextField();
        Label colorLabel = new Label("Color:");
        TextField colorTextField = new TextField();
        Label priceLabel = new Label("Price:");
        TextField priceTextField = new TextField();

        Button addButton = new Button("Add Car");
        addButton.setOnAction(e -> {
            String make = makeTextField.getText();
            String model = modelTextField.getText();
            int year = Integer.parseInt(yearTextField.getText());
            String color = colorTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());

            Car car = new Car(make, model, year, color, price);
            inventory.addCar(car);
            carList.add(car);

            makeTextField.clear();
            modelTextField.clear();
            yearTextField.clear();
            colorTextField.clear();
            priceTextField.clear();
        });

        Button removeButton = new Button("Remove Car");
        removeButton.setOnAction(e -> {
            Car selectedCar = carListView.getSelectionModel().getSelectedItem();
            if (selectedCar != null) {
                inventory.removeCar(selectedCar.getMake(), selectedCar.getModel());
                carList.remove(selectedCar);
            }
        });

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(makeLabel, makeTextField, modelLabel, modelTextField,
                yearLabel, yearTextField, colorLabel, colorTextField, priceLabel, priceTextField,
                addButton, removeButton);

        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(carListView, vbox);

        Scene scene = new Scene(hbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Inventory loadInventoryFromFile() {
        // Load the inventory from file (if exists) using I/O Streams

        return null; 
    }

    private void saveInventoryToFile(Inventory inventory) {
        // Save the inventory to file using I/O Streams

    }

    @Override
    public void stop() {
        saveInventoryToFile(inventory);
    }
}
