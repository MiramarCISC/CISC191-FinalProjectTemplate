package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.io.*;
import java.net.ServerSocket; // ServerSocket, Socket
import java.net.Server
import java.util.ArrayList*; //ArrayList, HashMap, Scanner
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Server extends Application 
{
    private HashMap<String,User> userMap;
    private ArrayList<User> userList;
    private int[][] twoDimArray;
    private Scanner scanner;
    // Constructor and initialization of variables.
    public Server() 
    {
        userMap = new HashMap<>();
        userList = new ArrayList<>();
        twoDimArray = new int[10][10];
        scanner = new Scanner(System.in);
    }
    // Print console menu options. Module 1.
    public void printConsoleMenu() 
    {
        System.out.println("CONSOLE MENU");
        System.out.println("1. GET VALUE AT INDEX");
        System.out.println("2. SET VALUE AT INDEX");
        System.out.println("3. FIND INDEX OF VALUE");
        System.out.println("4. PRINT ALL VALUES");
        System.out.println("5. DELETE VALUE AT INDEX");
        System.out.println("6. EXPAND ARRAY");
        System.out.println("7. SHRINK ARRAY");
        System.out.println("0. EXIT");
    }
    // Handle user input for console menu options.
    public void handleConsoleInput(int choice) 
    {
        switch (choice) {
            case 1:
                getValueAtIndex();
                break;
            case 2:
                setValueAtIndex();
                break;
            case 3:
                findIndexOfValue();
                break;
            case 4:
                printAllValues();
                break;
            case 5:
                deleteValueAtIndex();
                break;
            case 6:
                expandArray();
                break;
            case 7:
                shrinkArray();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("INVALID OPTION. PLEASE TRY AGAIN.");
                break;
        }
    }
    private void getValueAtIndex() {
        System.out.print("Enter Row Index: ");
        int row = scanner.nextInt();
        System.out.print("Enter Column Index: ");
        int col = scanner.nextInt();
        if (isValidIndex(row, col)) {
            int value = twoDimArray[row][col];
            System.out.println("Value At Index (" + row + ", " + col + "): " + value);
        } else {
            System.out.println("Invalid Input. Please Try Again.");
    }
    }
    private void setValueAtIndex() { // TODO CASE 2 }
    }

    private void findIndexOfValue() { // TODO CASE 3 }
    }

    private void printAllValues() { // TODO CASE 4 }
    }

    private void deleteValueAtIndex() 
    {
        System.out.print("ENTER ROW INDEX: ");
        int row = scanner.nextInt();
        System.out.print("ENTER COLUMN INDEX: ");
        int col = scanner.nextInt();
        if (isValidIndex(row, col)) 
        {
            twoDimArray[row][col] = 0;
            System.out.println("VALUE DELETED AT INDEX (" + row + ", " + col + ").");
        } 
        else 
        {
            System.out.println("INVALID INPUT. PLEASE TRY AGAIN.");
        }
    }
    private void expandArray() 
    {
        int[][] newArray = new int[twoDimArray.length + 1][twoDimArray[0].length + 1];
        for (int row = 0; row < twoDimArray.length; row++) 
        {
            for (int col = 0; col < twoDimArray[row].length; col++) 
            {
                newArray[row][col] = twoDimArray[row][col];
            }
        }
        twoDimArray = newArray;
        System.out.println("ARRAY EXPANDED");
    }
    private void shrinkArray() 
    {
        if (twoDimArray.length > 1 && twoDimArray[0].length > 1) 
        {
            int[][] newArray = new int[twoDimArray.length - 1][twoDimArray[0].length - 1];
            for (int row = 0; row < newArray.length; row++) 
            {
                for (int col = 0; col < newArray[row].length; col++) 
                {
                    newArray[row][col] = twoDimArray[row][col];
                }
            }
            twoDimArray = newArray;
            System.out.println("ARRAY SHRUNK");
        } 
        else 
        {
            System.out.println("ARRAY CANNOT BE FURTHER SHRUNK");
        }
    }
    // Check if given row and column indices are valid.
    private boolean isValidIndex(int row, int col) 
    {
        return row >= 0 && row < twoDimArray.length && col >= 0 && col < twoDimArray[row].length;
    }

    public static void main(String[] args) 
    {
        /*
         * Without GUI nor networking implementations.
         * Server server = new Server();
        server.printConsoleMenu();
        while (true)
        {
            System.out.print("Enter your choice: ");
            int choice = server.scanner.nextInt();
            server.handleConsoleInput(choice);
        }
         */
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Index & Array Console Menu");
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(30));
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #D3BDA2");

        Label titleLabel = new Label("CONSOLE MENU");
        titleLabel.setStyle("-fx-font: 100px Impact; -fx-font-size: 100px; -fx-font-weight: bold;");
        vbox.getChildren().add(titleLabel);
        vbox.setAlignment(Pos.TOP_CENTER);

        Button getValueBtn = new Button("Get Value At Index");
        getValueBtn.setOnAction(e -> getValueAtIndexGUI());
        vbox.getChildren().add(getValueBtn);
        getValueBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                        "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Button setValueBtn = new Button("Set Value At Index");
        setValueBtn.setOnAction(e -> setValueAtIndexGUI());
        vbox.getChildren().add(setValueBtn);
        setValueBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Button findIndexBtn = new Button("Find Value At Index");
        findIndexBtn.setOnAction(e -> findIndexOfValueGUI());
        vbox.getChildren().add(findIndexBtn);
        findIndexBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Button printAllBtn = new Button("Print All Values");
        printAllBtn.setOnAction(e -> printAllValuesGUI());
        vbox.getChildren().add(printAllBtn);
        printAllBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Button deleteValueBtn = new Button("Delete Value At Index");
        deleteValueBtn.setOnAction(e -> deleteValueAtIndexGUI());
        vbox.getChildren().add(deleteValueBtn);
        deleteValueBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Button expandArrayBtn = new Button("Expand Array");
        expandArrayBtn.setOnAction(e -> expandArrayGUI());
        vbox.getChildren().add(expandArrayBtn);
        expandArrayBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Button shrinkArrayBtn = new Button("Shrink Array");
        shrinkArrayBtn.setOnAction(e -> shrinkArrayGUI());
        vbox.getChildren().add(shrinkArrayBtn);
        shrinkArrayBtn.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: #7EA3AC; -fx-text-fill: black;-fx-font: 30px Impact;" );

        Scene scene = new Scene(vbox, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
    private void getValueAtIndexGUI() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Get Value");
        dialog.setHeaderText("Enter Indexes");
        dialog.setContentText("Row: ");
        Optional<String> rowResult = dialog.showAndWait();
        if (rowResult.isPresent()) {
            dialog.setContentText("Column: ");
            Optional<String> colResult = dialog.showAndWait();
            if (colResult.isPresent()) {
                int row = Integer.parseInt(rowResult.get());
                int col = Integer.parseInt(colResult.get());
                if (isValidIndex(row, col)) {
                    int value = twoDimArray[row][col];
                    showAlert("Value At Index (" + row + ", " + col + "): " + value);
                } else {
                    showAlert("Invalid Input. Try Again.");
                }
            }
        }
    }
    private void setValueAtIndexGUI() { // TODO .setValueBtn }
    }

    private void findIndexOfValueGUI() { // TODO .findIndexBtn }
    }

    private void printAllValuesGUI() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ARRAY VALUES \n");
        for (int row = 0; row < twoDimArray.length; row++) 
        {
            for (int col = 0; col < twoDimArray[row].length; col++) 
            {
                sb.append(twoDimArray[row][col]).append(" ");
            }
            sb.append("\n");
        }
        showAlert(sb.toString());
    }

    private void deleteValueAtIndexGUI() 
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("DELETE VALUE");
        dialog.setHeaderText("ENTER INDEX");
        dialog.setContentText("ROW: ");
        Optional<String> rowResult = dialog.showAndWait();
        if (rowResult.isPresent()) 
        {
            dialog.setContentText("COLUMN: ");
            Optional<String> colResult = dialog.showAndWait();
            if (colResult.isPresent()) 
            {
                int row = Integer.parseInt(rowResult.get());
                int col = Integer.parseInt(colResult.get());
                if (isValidIndex(row, col)) 
                {
                    twoDimArray[row][col] = 0;
                    showAlert("VALUE DELETED AT INDEX (" + row + ", " + col + ").");
                } 
                else 
                {
                    showAlert("INVALID INPUT. TRY AGAIN.");
                }
            }
        }
    }

    private void expandArrayGUI() 
    {
        int[][] newArray = new int[twoDimArray.length + 1][twoDimArray[0].length + 1];
        for (int row = 0; row < twoDimArray.length; row++) 
        {
            for (int col = 0; col < twoDimArray[row].length; col++) 
            {
                newArray[row][col] = twoDimArray[row][col];
            }
        }
        twoDimArray = newArray;
        showAlert("ARRAY EXPANDED");
    }

    private void shrinkArrayGUI() 
    {
        if (twoDimArray.length > 1 && twoDimArray[0].length > 1) 
        {
            int[][] newArray = new int[twoDimArray.length - 1][twoDimArray[0].length - 1];
            for (int row = 0; row < newArray.length; row++) 
            {
                for (int col = 0; col < newArray[row].length; col++) 
                {
                    newArray[row][col] = twoDimArray[row][col];
                }
            }
            twoDimArray = newArray;
            showAlert("ARRAY SHRUNK");
        } 
        else 
        {
            showAlert("ARRAY CANNOT BE FURTHER SHRUNK");
        }
    }

    // Method to show alert or warning with given message.
    private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERT");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
/*
 * Includes the implementation of a JavaFX GUI menu using buttons and dialog boxes for user input and displaying messages.
 * Methods getValueAtIndexGUI(), setValueAtIndexGUI(), findIndexOfValueGUI(), printAllValuesGUI(), deleteValueAtIndexGUI(), expandArrayGUI(), and shrinkArrayGUI()
 * used to handle the corresponding actions in the GUI interface.
 * Networking functionality is not implemented in this code.
 * Implementing networking would involve creating a separate class for the server and client,
 * establishing a network connection, and exchanging data between them using sockets and input/output streams.
 */
    class User implements Serializable // NOT NECESSARY ??
    {
        private String name;
        public User(String name)
        {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
