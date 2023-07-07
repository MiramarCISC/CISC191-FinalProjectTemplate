package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.*; // ServerSocket, Socket
import java.util.*; //ArrayList, HashMap, Scanner

public class Server extends Application 
{
    private HashMap<String, User> userMap;
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

    private void getValueAtIndex() { // TODO CASE 1 }
    }

    private void setValueAtIndex() { // TODO CASE 2 }
    }

    private void findIndexOfValue() { // TODO CASE 3 }
    }

    private void printAllValues() { // TODO CASE 4 }
    }

    private void deleteValueAtIndex() { // TODO CASE 5 }
    }

    private void expandArray() { // TODO CASE 6 }
    }

    private void shrinkArray() { // TODO CASE 7 }
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
    public void start(Stage primaryStage) 
    {
        // TODO - SET UP GUI W/ ACTION EVENT E & VBOX
    }

    private void getValueAtIndexGUI() { // TODO .getValueBtn }
    }

    private void setValueAtIndexGUI() { // TODO .setValueBtn }
    }

    private void findIndexOfValueGUi() { // TODO .findIndexBtn }
    }

    private void printAllValuesGUI() { // TODO .printAllBtn }
    }

    private void deleteValueAtIndexGUI() { // TODO .deleteValueBtn }
    }

    private void expandArrayGUI() { // TODO .expandArrayBtn }
    }

    private void shrinkArrayGUI() { // TODO .shrinkArrayBtn }
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
