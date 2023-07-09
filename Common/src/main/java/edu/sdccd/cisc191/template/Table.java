package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
Javafx table of all capital cities and select popular cities of the United States listed as city = value
and state = value key value pairs
Allows user to delete any city
Selects random city and returns object of city and state
 */

public class Table extends Application {
    private TableView tableView;
    private static Object location;

    // Launch application when main is called
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {

        this.tableView = new TableView();

        final Label label = new Label("Locations");

        // set first column label and key
        TableColumn<Map, String> firstNameColumn = new TableColumn<>("City");
        firstNameColumn.setCellValueFactory(new MapValueFactory<>("city"));

        // set second column label and key
        TableColumn<Map, String> lastNameColumn = new TableColumn<>("State");
        lastNameColumn.setCellValueFactory(new MapValueFactory<>("state"));

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

        // new ObservableList filled with hashmaps of key value pairs of all state capitals and popular cities
        ObservableList<Map<String, Object>> items =
                FXCollections.observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("city", "San Diego");
        item1.put("state" , "California");

        Map<String, Object> item2 = new HashMap<>();
        item2.put("city", "New York City");
        item2.put("state" , "New York");

        Map<String, Object> item3 = new HashMap<>();
        item3.put("city", "Chicago");
        item3.put("state" , "Illinois");

        Map<String, Object> item4 = new HashMap<>();
        item4.put("city", "Houston");
        item4.put("state" , "Texas");

        Map<String, Object> item5 = new HashMap<>();
        item5.put("city", "Phoenix");
        item5.put("state" , "Arizona");

        Map<String, Object> item6 = new HashMap<>();
        item6.put("city", "Montgomery");
        item6.put("state" , "Alabama");

        Map<String, Object> item7 = new HashMap<>();
        item7.put("city", "Juneau");
        item7.put("state" , "Alaska");

        Map<String, Object> item8 = new HashMap<>();
        item8.put("city", "Little Rock");
        item8.put("state" , "Arkansas");

        Map<String, Object> item9 = new HashMap<>();
        item9.put("city", "Sacramento");
        item9.put("state" , "California");

        Map<String, Object> item10 = new HashMap<>();
        item10.put("city", "Denver");
        item10.put("state" , "Colorado");

        Map<String, Object> item11 = new HashMap<>();
        item11.put("city", "Hartford");
        item11.put("state" , "Connecticut");

        Map<String, Object> item12 = new HashMap<>();
        item12.put("city", "Dover");
        item12.put("state" , "Delaware");

        Map<String, Object> item13 = new HashMap<>();
        item13.put("city", "Tallahassee");
        item13.put("state" , "Florida");

        Map<String, Object> item14 = new HashMap<>();
        item14.put("city", "Atlanta");
        item14.put("state" , "Georgia");

        Map<String, Object> item15 = new HashMap<>();
        item15.put("city", "Honolulu");
        item15.put("state" , "Hawaii");

        Map<String, Object> item16 = new HashMap<>();
        item16.put("city", "Boise");
        item16.put("state" , "Idaho");

        Map<String, Object> item17 = new HashMap<>();
        item17.put("city", "Springfield");
        item17.put("state" , "Illinois");

        Map<String, Object> item18 = new HashMap<>();
        item18.put("city", "Indianapolis");
        item18.put("state" , "Indiana");

        Map<String, Object> item19 = new HashMap<>();
        item19.put("city", "Des Moines");
        item19.put("state" , "Iowa");

        Map<String, Object> item20 = new HashMap<>();
        item20.put("city", "Topeka");
        item20.put("state" , "Kansas");

        Map<String, Object> item21 = new HashMap<>();
        item21.put("city", "Frankfort");
        item21.put("state" , "Kentucky");

        Map<String, Object> item22 = new HashMap<>();
        item22.put("city", "Baton Rouge");
        item22.put("state" , "Louisiana");

        Map<String, Object> item23 = new HashMap<>();
        item23.put("city", "Augusta");
        item23.put("state" , "Maine");

        Map<String, Object> item24 = new HashMap<>();
        item24.put("city", "Annapolis");
        item24.put("state" , "Maryland");

        Map<String, Object> item25 = new HashMap<>();
        item25.put("city", "Boston");
        item25.put("state" , "Massachusetts");

        Map<String, Object> item26 = new HashMap<>();
        item26.put("city", "Lansing");
        item26.put("state" , "Michigan");

        Map<String, Object> item27 = new HashMap<>();
        item27.put("city", "Saint Paul");
        item27.put("state" , "Minnesota");

        Map<String, Object> item28 = new HashMap<>();
        item28.put("city", "Jackson");
        item28.put("state" , "Mississippi");

        Map<String, Object> item29 = new HashMap<>();
        item29.put("city", "Jefferson City");
        item29.put("state" , "Missouri");

        Map<String, Object> item30 = new HashMap<>();
        item30.put("city", "Helena");
        item30.put("state" , "Montana");

        Map<String, Object> item31 = new HashMap<>();
        item31.put("city", "Lincoln");
        item31.put("state" , "Nebraska");

        Map<String, Object> item32 = new HashMap<>();
        item32.put("city", "Carson City");
        item32.put("state" , "Nevada");

        Map<String, Object> item33 = new HashMap<>();
        item33.put("city", "Las Vegas");
        item33.put("state" , "Nevada");

        Map<String, Object> item34 = new HashMap<>();
        item34.put("city", "Concord");
        item34.put("state" , "New Hampshire");

        Map<String, Object> item35 = new HashMap<>();
        item35.put("city", "Trenton");
        item35.put("state" , "New Jersey");

        Map<String, Object> item36 = new HashMap<>();
        item36.put("city", "Santa Fe");
        item36.put("state" , "New Mexico");

        Map<String, Object> item37 = new HashMap<>();
        item37.put("city", "Albany");
        item37.put("state" , "New York");

        Map<String, Object> item38 = new HashMap<>();
        item38.put("city", "Raleigh");
        item38.put("state" , "North Carolina");

        Map<String, Object> item39 = new HashMap<>();
        item39.put("city", "Bismarck");
        item39.put("state" , "North Dakota");

        Map<String, Object> item40 = new HashMap<>();
        item40.put("city", "Columbus");
        item40.put("state" , "Ohio");

        Map<String, Object> item41 = new HashMap<>();
        item41.put("city", "Oklahoma City");
        item41.put("state" , "Oklahoma");

        Map<String, Object> item42 = new HashMap<>();
        item42.put("city", "Salem");
        item42.put("state" , "Oregon");

        Map<String, Object> item43 = new HashMap<>();
        item43.put("city", "Harrisburg");
        item43.put("state" , "Pennsylvania");

        Map<String, Object> item44 = new HashMap<>();
        item44.put("city", "Providence");
        item44.put("state" , "Rhode Island");

        Map<String, Object> item45 = new HashMap<>();
        item45.put("city", "Columbia");
        item45.put("state" , "South Carolina");

        Map<String, Object> item46 = new HashMap<>();
        item46.put("city", "Pierre");
        item46.put("state" , "South Dakota");

        Map<String, Object> item47 = new HashMap<>();
        item47.put("city", "Nashville");
        item47.put("state" , "Tennessee");

        Map<String, Object> item48 = new HashMap<>();
        item48.put("city", "Austin");
        item48.put("state" , "Texas");

        Map<String, Object> item49 = new HashMap<>();
        item49.put("city", "Salt Lake City");
        item49.put("state" , "Utah");

        Map<String, Object> item50 = new HashMap<>();
        item50.put("city", "Montpelier");
        item50.put("state" , "Vermont");

        Map<String, Object> item51 = new HashMap<>();
        item51.put("city", "Richmond");
        item51.put("state" , "Virginia");

        Map<String, Object> item52 = new HashMap<>();
        item52.put("city", "Olympia");
        item52.put("state" , "Washington");

        Map<String, Object> item53 = new HashMap<>();
        item53.put("city", "Charleston");
        item53.put("state" , "West Virginia");

        Map<String, Object> item54 = new HashMap<>();
        item54.put("city", "Madison");
        item54.put("state" , "Wisconsin");

        Map<String, Object> item55 = new HashMap<>();
        item55.put("city", "Cheyenne");
        item55.put("state" , "Wyoming");

        Map<String, Object> item56 = new HashMap<>();
        item56.put("city", "Detroit");
        item56.put("state" , "Michigan");

        Map<String, Object> item57 = new HashMap<>();
        item57.put("city", "Los Angeles");
        item57.put("state" , "California");

        Map<String, Object> item58 = new HashMap<>();
        item58.put("city", "Miami");
        item58.put("state" , "Florida");

        Map<String, Object> item59 = new HashMap<>();
        item59.put("city", "Albuquerque");
        item59.put("state" , "New Mexico");

        Map<String, Object> item60 = new HashMap<>();
        item59.put("city", "Seatlle");
        item59.put("state" , "Washington");

        items.addAll(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13,
                item14, item15, item16, item17, item18, item19, item20, item21, item22, item23, item24, item25,
                item26, item27, item28, item29, item30, item31, item32, item33, item34, item35, item36, item37,
                item38, item39, item40, item41, item42, item43, item44, item45, item46, item47, item48, item49,
                item50, item51, item52, item53, item54, item55, item56, item57, item58, item59, item60);

        tableView.getItems().addAll(items);


        // delete button that removes selected row of data
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem()));

        // button that generates a random location from list and then closes application
        final Button showLocation = new Button("Get Random Location");
        showLocation.setOnAction(e -> {
            generateLocation();
            primaryStage.close();
        });


        // Set placement of visuals
        VBox vbox = new VBox();

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 0));
        vbox.getChildren().addAll(label, tableView, deleteButton, showLocation);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Cities in United States");
        primaryStage.show();
    }

    // Generate a random number of the table size to get a random row of data and set value to variable location
    public void generateLocation() {
        Random rand = new Random();
        int random = rand.nextInt((tableView.getItems().size()));
        tableView.requestFocus();
        tableView.getSelectionModel().select(random);
        tableView.getFocusModel().focus(random);
        location = tableView.getItems().get(random);
    }

    // get method for client class
    public static Object getLocation() {
        return location;
    }
}