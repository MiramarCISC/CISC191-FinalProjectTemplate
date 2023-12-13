package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class GroceryStoreApp extends Application {
    private GroceryStore2D store2D;
    private TextField searchField;  // Search bar
    private ListView<String> searchResults;  // Display search results
    private Map<String, Map<String, Double>> aislePrices; // Prices for each aisle

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        store2D = new GroceryStore2D(3, 3); // Initial size of 3x3
        initializePrices();

        primaryStage.setTitle("Grocery Store App");
        GridPane grid = createGrid();
        addComponents(grid);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializePrices() {
        aislePrices = new HashMap<>();

        // Initialize prices for each aisle
        Map<String, Double> dairyPrices = new HashMap<>();
        dairyPrices.put("Milk", 6.99);
        dairyPrices.put("Cheese", 5.99);
        dairyPrices.put("Yogurt", 4.49);
        aislePrices.put("Dairy", dairyPrices);

        Map<String, Double> snacksPrices = new HashMap<>();
        snacksPrices.put("Chips", 3.99);
        snacksPrices.put("Cookies", 5.69);
        snacksPrices.put("Nuts", 5.99);
        aislePrices.put("Snacks", snacksPrices);

        Map<String, Double> bakeryPrices = new HashMap<>();
        bakeryPrices.put("Bread", 4.99);
        bakeryPrices.put("Muffins", 5.99);
        bakeryPrices.put("Croissant", 5.49);
        aislePrices.put("Bakery", bakeryPrices);

        Map<String, Double> producePrices = new HashMap<>();
        producePrices.put("Apples", 1.20);
        producePrices.put("Bananas", 0.25);
        producePrices.put("Grapes", 2.99);
        aislePrices.put("Produce", producePrices);
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Create the search components and add them to the grid
        searchField = new TextField();
        searchField.setPromptText("Search items by name");
        grid.add(searchField, 0, 0);

        searchResults = new ListView<>();
        searchResults.setPrefHeight(150);
        grid.add(searchResults, 0, 1);

        return grid;
    }

    private void addComponents(GridPane grid) {
        ObservableList<String> aisleOptions = FXCollections.observableArrayList(
                "Dairy", "Snacks", "Bakery", "Produce"
        );

        // Aisle dropdown menu
        ComboBox<String> aisleDropdown = new ComboBox<>(aisleOptions);
        aisleDropdown.setPromptText("Select Aisle");
        grid.add(aisleDropdown, 0, 2);

        // Options ListView
        ListView<String> optionsListView = new ListView<>();
        grid.add(optionsListView, 0, 3);

        // Price label
        Label priceLabel = new Label("Price: ");
        grid.add(priceLabel, 0, 4);

        // Event handler for aisle selection
        aisleDropdown.setOnAction(event -> {
            String selectedAisle = aisleDropdown.getValue();
            updateOptionsAndPrice(selectedAisle, optionsListView, priceLabel);
        });
    }

    private void updateOptionsAndPrice(String aisle, ListView<String> optionsListView, Label priceLabel) {
        Map<String, Double> prices = aislePrices.getOrDefault(aisle, new HashMap<>());
        ObservableList<String> options = FXCollections.observableArrayList(prices.keySet());
        optionsListView.setItems(options);
        priceLabel.setText("Price: "); // Reset price label

        // Event handler for option selection
        optionsListView.setOnMouseClicked(event -> {
            String selectedOption = optionsListView.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                double price = prices.getOrDefault(selectedOption, 0.0);
                priceLabel.setText("Price: $" + price);
            }
        });
    }
}
