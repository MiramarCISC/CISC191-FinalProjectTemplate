package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

// Your code is well-written. You applied a lot of concepts such as Interface, multi classes, inheritance... in your code.
// And your JavaFX could perform many actions which is impressed. However, it would be nice if you could add more comments
// to explain your code, so that other programmers can understand your code. When I run your code, I saw the "delete" button
// works well with the "both" Show Entries, but it is missing with the "Expense" and "Income" Show Entries.

public class JavaFX extends Application {

    private void deleteEntry(ActionEvent event, Entry toDelete, Book book, GridPane grid, GridPane summeryGrid, ArrayList<Entry> entries)
    {
        book.removeEntry(toDelete);
        grid.getChildren().clear();
        renderEntry(book, grid,summeryGrid, entries);
    };

    public void renderEntry(Book book, GridPane grid, GridPane summeryGrid, ArrayList<Entry> entries) {
        grid.add(new Label("Description"), 0,0);
        grid.add(new Label("Type"), 1,0);
        grid.add(new Label("Amount"), 2,0);

        for (int i=0; i < entries.size(); i++) {
            String descriptionString = entries.get(i).getDescription();
            Label descriptionLabel = new Label(descriptionString);
            grid.add(descriptionLabel,0, i+1);

            String typeString = entries.get(i).getType();
            Label typeLabel = new Label(typeString);
            grid.add(typeLabel,1, i+1);

            String amountString = entries.get(i).getAmount();
            Label amountLabel = new Label("$"+String.valueOf(amountString));
            grid.add(amountLabel,2, i+1);


            Button deleteButton = new Button("Delete");
            Entry toDelete = entries.get(i);
            deleteButton.setOnAction(e -> deleteEntry(e, toDelete, book, grid,summeryGrid, entries));
            grid.add(deleteButton, 5, i+1);

        }
        renderSummery(book, summeryGrid);
    }

    public void renderSummery(Book book, GridPane summeryGrid) {
        summeryGrid.getChildren().clear();
        summeryGrid.add(new Label("Total Income:"), 2,1);
        Label totalIncome = new Label("$"+String.valueOf(book.getTotalIncome()));
        summeryGrid.add(totalIncome, 3,1);
        summeryGrid.add(new Label("Total Expenses:"), 2,2);
        Label totalExpenses = new Label("$"+String.valueOf(book.getTotalExpenses()));
        summeryGrid.add(totalExpenses, 3,2);
        summeryGrid.add(new Label("Net Profit:"), 2,3);
        Label netProfit = new Label("$"+String.valueOf(book.getNetProfit()));
        summeryGrid.add(netProfit, 3,3);
    }


    public void start(Stage stage) throws IOException {
        Book book = new Book();

        book.addEntry(new Income("Window clean", "Income", 65.00));
        book.addEntry(new Income("Cut grass", "Income", 85.00));
        book.addEntry(new Income("Fix sink", "Income", 35.00));
        book.addEntry(new Income("Wash car", "Income", 25.00));

        book.addEntry(new Expense("Gas", "Expense", 125.00));
        book.addEntry(new Expense("Supplies", "Expense", 40.00));
        book.addEntry(new Expense("Car soap", "Expense", 10.00));

        ArrayList<Entry> entries = book.getEntries();

        GridPane headerGrid = new GridPane();
        headerGrid.setAlignment(Pos.TOP_LEFT);
        headerGrid.setHgap(10);
        headerGrid.setVgap(10);
        headerGrid.setPadding(new Insets(5,5,5,5));

        GridPane entryGrid = new GridPane();
        entryGrid.setAlignment(Pos.TOP_LEFT);
        entryGrid.setHgap(10);
        entryGrid.setVgap(10);
        entryGrid.setPadding(new Insets(5,5,5,5));

        GridPane actionGrid = new GridPane();
        actionGrid.setAlignment(Pos.TOP_LEFT);
        actionGrid.setHgap(10);
        actionGrid.setVgap(10);
        actionGrid.setPadding(new Insets(5,5,5,5));

        GridPane summeryGrid = new GridPane();
        summeryGrid.setAlignment(Pos.TOP_RIGHT);
        summeryGrid.setHgap(10);
        summeryGrid.setVgap(10);
        summeryGrid.setPadding(new Insets(5,5,5,5));


        Label showLabel = new Label("Show Entries:");
        headerGrid.add(showLabel, 0 ,0);
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Expenses", "Incomes", "Both"); //using addAll() to clean your code
       // comboBox.getItems().add("Incomes");
       // comboBox.getItems().add("Both");
        comboBox.setValue("Both");
//        ComboBox sortComboBox = new ComboBox();
//        sortComboBox.getItems().add("Brand");
//        sortComboBox.getItems().add("Model");
//        sortComboBox.getItems().add("Year");
//        sortComboBox.getItems().add("Miles");
//        sortComboBox.setValue("Brand");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (comboBox.getValue() == "Expenses") {
                    ArrayList<Entry> expenses = book.getExpenses();
                    entryGrid.getChildren().clear();
                    renderEntry(book, entryGrid, summeryGrid, expenses);
                }
                if (comboBox.getValue() == "Incomes") {
                    ArrayList<Entry> incomes = book.getIncome();
                    entryGrid.getChildren().clear();
                    renderEntry(book, entryGrid, summeryGrid, incomes);
                }
                if (comboBox.getValue() == "Both") {
                    ArrayList<Entry> entries = book.getEntries();
                    entryGrid.getChildren().clear();
                    renderEntry(book, entryGrid, summeryGrid, entries);
                }

            }
        };

        comboBox.setOnAction(event);
        headerGrid.add(comboBox, 1, 0);

//        headerGrid.add(new Label("Sort Criteria: "), 2,0);
//        sortComboBox.setOnAction(event);
//        headerGrid.add(sortComboBox, 3, 0);


        renderEntry(book, entryGrid,summeryGrid, entries);

        actionGrid.add(new Label ("Description:"), 0, 1);
        TextField descriptionInput = new TextField();
        actionGrid.add(descriptionInput,1,1);

        actionGrid.add(new Label("Type:"),0,2);
        ComboBox typeComboBox = new ComboBox();
        typeComboBox.getItems().add("Expense");
        typeComboBox.getItems().add("Income");
        typeComboBox.setValue("Expense");
        actionGrid.add(typeComboBox,1,2);

        actionGrid.add(new Label("Amount:"),0,3);
        TextField amountInput = new TextField();
        actionGrid.add(amountInput,1,3);



        EventHandler<ActionEvent> addEntry = e -> {
            if (typeComboBox.getValue().equals("Expense")) {
                book.addEntry(new Expense(descriptionInput.getText(), "Expense", parseDouble(amountInput.getText())));
            }
            if (typeComboBox.getValue().equals("Income")) {
                book.addEntry(new Income(descriptionInput.getText(), "Income", parseDouble(amountInput.getText())));
            }
            entryGrid.getChildren().clear();
            renderEntry(book, entryGrid,summeryGrid, entries);
        };


        Button addEntryButton = new Button("Add Entry");
        addEntryButton.setOnAction(addEntry);
        actionGrid.add(addEntryButton, 0, 5);

        summeryGrid.add(new Label("Total Income:"), 2,1);
        Label totalIncome = new Label("$"+String.valueOf(book.getTotalIncome()));
        summeryGrid.add(totalIncome, 3,1);
        summeryGrid.add(new Label("Total Expenses:"), 2,2);
        Label totalExpenses = new Label("$"+String.valueOf(book.getTotalExpenses()));
        summeryGrid.add(totalExpenses, 3,2);
        summeryGrid.add(new Label("Net Profit:"), 2,3);
        Label netProfit = new Label("$"+String.valueOf(book.getNetProfit()));
        summeryGrid.add(netProfit, 3,3);




        VBox vbox = new VBox(headerGrid, entryGrid, actionGrid);
        HBox hBox = new HBox(vbox, summeryGrid);
        Scene scene = new Scene(hBox, 600, 600);
        stage.setTitle("Expenses/Income System");
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {launch();}
}
