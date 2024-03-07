package edu.sdccd.cisc191.template;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.awt.Checkbox;
import java.util.Scanner;
import javax.swing.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class ViewStartScreen extends Application {
    private int screenWidth, screenHeight; //allows buttons to be scaled accordingly
    private int selectedIndex;
    private BorderPane layout;
    private Stage stage;
    private Scene startScene, sceneClassName;
    private String temp;
    private ArrayList<Subject> subjectArrayList = new ArrayList<>();

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set. The primary stage will be embedded in
     *              the browser if the application was launched as an applet.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages and will not be embedded in the browser.
     *              Initial screen that the user sees
     *              Hooray for javafx
     */
    public void start(Stage stage) throws FileNotFoundException {
        //variables???
        Glow glow = new Glow();
        glow.setLevel(0.2);
        this.stage = stage;
        // 720x1200 resolution
        screenWidth = 1000;
        screenHeight = 1120;
        Font font = Font.font("Montserrat", FontWeight.BOLD, 36);

        //button to direct the user to set up
        OptionButton setupButton = new OptionButton("Make your Schedule", 500, 100);
        setupButton.changeTextColor(Color.web("#34A3ED"));
        setupButton.changeBackGroundColor();
        setupButton.setFont(font);
        setupButton.setWrapText(true);

        //csv file of saved schedule
        OptionButton importSchedule = new OptionButton("Import Schedule", 500, 100);
        importSchedule.changeTextColor(Color.web("#34A3ED"));
        importSchedule.changeBackGroundColor();
        importSchedule.setFont(font);
        importSchedule.setWrapText(true);

        //title and credits to the authors
        Label title = new Label("Schedule and Homework Tracker");
        Label credits = new Label("Credits: Logan, Simon, Theo, Willy");
        title.setFont(font);
        //organize title and setup button to be spaced accordingly, set it in center
        VBox buttons = new VBox(screenHeight / 120, title, setupButton, importSchedule);
        Image image = new Image(new FileInputStream("Server/src/main/java/edu/sdccd/cisc191/template/Homework-modified.png"));
        Image color = new Image(new FileInputStream("Server/src/main/java/edu/sdccd/cisc191/template/Homework.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        buttons.getChildren().addAll(imageView);

        setupButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        setupButton.setEffect(glow);
                        imageView.setImage(color);

                    }
                });
        setupButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        setupButton.setEffect(null);
                        imageView.setImage(image);
                    }
                });

        importSchedule.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        importSchedule.setEffect(glow);
                        imageView.setImage(color);

                    }
                });
        importSchedule.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        importSchedule.setEffect(null);
                        imageView.setImage(image);
                    }
                });

        setupButton.setOnAction((ActionEvent e) -> {
            try {
                runSetup2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        importSchedule.setOnAction((ActionEvent e)-> {
            subjectArrayList = convertCSVToSubject();
            try {
                runMainScreen(subjectArrayList, selectedIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttons.setStyle("-fx-background-color: #FFF1DC");
        buttons.setAlignment(Pos.CENTER);
        layout = new BorderPane(buttons);

        buttons = new VBox(credits);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setBottom(buttons);
        startScene = new Scene(layout, screenWidth, screenHeight);
        startScene.setFill(Color.web("#81c483"));
        stage.setTitle("Schedule and Homework Tracker");
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /*
      (change to javadoc when uncommenting code)
      Asks the user for how many classes they have
      updates global variable subjectArrayList according to the user
      directs to runSetup2()
     */

   /* public void runSetup1() throws Exception{

        TextField answers = new TextField("100");
        answers.setPrefSize(screenWidth/2, screenHeight/8);

        Label promptClasses = new Label("How many classes with homework do you have?");

        OptionButton confirm = new OptionButton("Confirm", screenWidth/4, screenHeight/16);
        confirm.setOnAction((ActionEvent e)-> {
            //TODO make sure user only inputs integers
            subjectArrayList = new ArrayList<>(Integer.parseInt(answers.getText()));
            amountOfClasses = Integer.parseInt(answers.getText());
            try {
                runSetup2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        VBox buttons = new VBox(screenHeight/60, promptClasses, answers, confirm);
        layout = new BorderPane(buttons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        stage.setTitle("Set Schedule");
        stage.setScene(sceneClassName);
        stage.show();
    }
*/

    /**
     * First screen the user sees when
     *
     * @throws Exception
     */
    public void runSetup2() throws Exception {
        Font font = Font.font("Montserrat", FontWeight.BOLD, 18);

        Label promptName = new Label("Enter the name of the class you would like to add");
        TextField name = new TextField();
        name.setFont(font);
        name.setPrefSize(screenWidth / 2, screenHeight / 10);
        name.setMaxWidth(screenWidth / 2);
        promptName.setFont(font);

        CheckBox promtWeighted = new CheckBox("Is the class weighted?");
        promtWeighted.setFont(font);

        Label promptGrade = new Label("What is your current grade in the class?");
        promptGrade.setFont(font);

        TextField grade = new TextField();
        grade.setPrefSize(screenWidth / 2, screenHeight / 8);
        grade.setMaxWidth(screenWidth / 2);
        grade.setFont(font);
        //prevents any compiler errors when adding to sujectArrayList
        OptionButton confirm = new OptionButton("Confirm", screenWidth / 6, screenHeight / 24);
        Label colorChoice = new Label("What color would you like the subject to be?");
        colorChoice.setFont(font);
        ChoiceBox<String> dropDown = new ChoiceBox<>();
        dropDown.getItems().add("Red");
        dropDown.getItems().add("Blue");
        dropDown.getItems().add("Yellow");
        dropDown.getItems().add("Green");
        dropDown.getItems().add("Orange");
        dropDown.getItems().add("Purple");
            /* adds first class to subject array list
               directs to main interface
             */
        confirm.setOnAction((ActionEvent yes) -> {
            try {
                Subject tempSubject = new Subject(name.getText(), Double.parseDouble(grade.getText()));
                subjectArrayList.add(tempSubject);
                selectedIndex = dropDown.getSelectionModel().getSelectedIndex();
                runMainScreen(subjectArrayList, selectedIndex);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        VBox buttons = new VBox(50);

        buttons.setStyle("-fx-background-color: #FFF1DC;");
        buttons.getChildren().addAll(promptName, name, promptGrade, grade, promtWeighted, colorChoice, dropDown, confirm);
        buttons.setAlignment(Pos.CENTER);
        layout = new BorderPane(buttons);

        name.clear();
        grade.clear();
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Set your classes");
        stage.show();
    }

    /**
     * this is done in like every method so i wanted it to be convenient
     *
     * @param scene scene to switch to
     * @param title title of new scene
     */
    public void switchScene(Scene scene, String title) {
        stage.setScene(scene);
        stage.setTitle(title);
    }

    /**
     * @param a subjectArrayList, used to have the proper amount of buttons
     * @throws Exception prevents anything from not compiling
     *                   TODO deal with weird user inputs
     */
    public void runMainScreen(ArrayList<Subject> a, int colorNumber) throws Exception {
        Label classList = new Label("Your Classes");
        classList.setStyle("-fx-font-size: 20; -fx-underline: true; -fx-font-weight: bold;");
        VBox classes = new VBox(screenHeight / 240, classList);
        classes.setStyle("-fx-background-color: #FFF1DC;");
        classes.setAlignment(Pos.TOP_LEFT);
        for (int i = 0; i < a.size(); i++) {
            OptionButton button = new OptionButton(a.get(i).getNameOfSubject(), screenWidth / 3, screenHeight / 10);
            //i helps keep track of which subject is which
            button.changeTextColor(colorNumber);
            int finalI = i;
            try {
                button.setOnAction((ActionEvent e) -> {
                    viewAssignmentList(finalI);
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            classes.getChildren().add(button);
        }
        //allows user to add another class to the list
        OptionButton addClass = new OptionButton("Add Class", screenWidth / 5, screenHeight / 17.5);
        addClass.setOnAction((ActionEvent e) -> {
            try {
                runSetup2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        OptionButton saveSchedule = new OptionButton("Save Schedule", screenWidth / 5, screenHeight / 17.5);
        saveSchedule.setOnAction((ActionEvent e)-> {
            convertSubjectToCSV(subjectArrayList);
        });
        HBox bottomButtons = new HBox(screenWidth / 1.5, addClass, saveSchedule);
        bottomButtons.setAlignment(Pos.BOTTOM_LEFT);
        layout = new BorderPane(classes);
        layout.setBottom(bottomButtons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "View Information");
        stage.show();
    }

    /**
     * @param subjectArrayIndex index of subjectArray i.e which subject does the user want to access
     *                          dear God did I do anything correctly
     *                          TODO deal with weird user inputs
     */
    public void viewAssignmentList(int subjectArrayIndex) {
        Subject subject = new Subject(subjectArrayList.get(subjectArrayIndex));
        ArrayList<Assignment> tempArray = subject.getAssignmentList();
        Label nameOfSubject = new Label(subjectArrayList.get(subjectArrayIndex).getNameOfSubject());
        VBox assignments = new VBox(screenHeight / 240, nameOfSubject);
        assignments.setStyle("-fx-background-color: #FFF1DC;");
        assignments.setAlignment(Pos.TOP_LEFT);
        OptionButton addAssignment = new OptionButton("Add Assignment", screenWidth / 5, screenHeight / 17.5);
        addAssignment.setOnAction((ActionEvent e) -> {
            try {
                addAssignment(subjectArrayIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        OptionButton backButton = new OptionButton("Back to main screen", screenWidth / 5, screenHeight / 17.5);
        backButton.setOnAction((ActionEvent e) -> {
            try {
                runMainScreen(subjectArrayList, selectedIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        for (int i = 0; i < tempArray.size(); i++) {
            Assignment assignment = tempArray.get(i);
            OptionButton button = new OptionButton(assignment.getNameOfAssignment(), screenWidth / 3, screenHeight / 10);
            int finalI = i;
            button.setOnAction((ActionEvent e) -> {
                try {
                    viewAssignmentInfo(subjectArrayIndex, finalI);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            assignments.getChildren().add(button);
        }
        HBox bottomButtons = new HBox(screenWidth / 1.5, addAssignment, backButton);
        layout = new BorderPane(assignments);
        layout.setBottom(bottomButtons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Assignments");
        stage.show();
    }

    public void viewAssignmentInfo(int subjectIndex, int assignmentIndex) {
        Subject subject = subjectArrayList.get(subjectIndex);
        ArrayList<Assignment> assignments = subject.getAssignmentList();
        Assignment selectedAssignment = assignments.get(assignmentIndex);

        // Create labels to display assignment details
        Label assignmentNameLabel = new Label("Assignment Name: " + selectedAssignment.getNameOfAssignment());


        // Create a button to go back to the assignment list
        OptionButton backButton = new OptionButton("Back to Assignment List", screenWidth / 5, screenHeight / 17.5);
        backButton.setOnAction((ActionEvent e) -> {
            try {
                viewAssignmentList(subjectIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Create a VBox to hold the labels and button
        VBox assignmentInfoLayout = new VBox(screenHeight / 60, assignmentNameLabel, backButton);
        assignmentInfoLayout.setAlignment(Pos.CENTER);

        // Set the layout for the scene
        layout = new BorderPane(assignmentInfoLayout);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Assignment Details");
        stage.show();
    }


    public void addAssignment(int subjectIndex) {
        Subject subject = subjectArrayList.get(subjectIndex); // Get the subject from the ArrayList
        Label assignmentNameLabel = new Label("Enter name of Assignment:");
        TextField assignmentNameField = new TextField();
        assignmentNameField.setPrefSize(screenWidth / 2, screenHeight / 8);

        OptionButton confirmButton = new OptionButton("Confirm", screenWidth / 6, screenHeight / 24);
        confirmButton.setOnAction((ActionEvent e) -> {
            String assignmentName = assignmentNameField.getText();
            // Create a new Assignment object with the entered name
            Assignment assignment = new Assignment(assignmentName);
            // Add the assignment to the subject's ArrayList
            subject.addAssignment(assignment);
            try {
                runMainScreen(subjectArrayList, selectedIndex); // Refresh the main screen with the updated assignment list
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox buttons = new VBox(screenHeight / 60, assignmentNameLabel, assignmentNameField, confirmButton);
        layout = new BorderPane(buttons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Add Assignment");
        stage.show();
    }

    /**
     *  Convert the list of subjects to a CSV file
     *
     * @param a The list of subjects to be converted.
     */
    public void convertSubjectToCSV(ArrayList<Subject> a) {
        FileChooser.ExtensionFilter availableFiles = new FileChooser.ExtensionFilter("txt files", "*.txt");
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Schedule");
        fc.setInitialFileName("My_Schedule.txt");
        fc.getExtensionFilters().add(availableFiles);
        File saveLocation = fc.showSaveDialog(stage);
        try (FileWriter writer = new FileWriter(saveLocation)){
            for (int i = 0; i < a.size(); i++){
                if(a.get(i).isWeighted()) {
                    writer.append(a.get(i).getNameOfSubject())
                            .append(',')
                            .append(String.valueOf(a.get(i).getGradeInClass()))
                            .append(',')
                            .append("true")
                            .append('\n');
                }
                else{
                    writer.append(a.get(i).getNameOfSubject())
                            .append(',')
                            .append(String.valueOf(a.get(i).getGradeInClass()))
                            .append(',')
                            .append("false")
                            .append('\n');
                }
            }
            writer.close();

            //msg to javafx success
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * allows the user to import a previously saved schedule in the form of a csv file
     * @return subject array to be imported
     */
    public ArrayList<Subject> convertCSVToSubject() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.TXT"),
                new FileChooser.ExtensionFilter("txt files", "*.txt"));
        File inFile = fc.showOpenDialog(null);
        ArrayList<Subject> subjectSave = new ArrayList<>();
        if (inFile != null) {
            try{
                Scanner inputStream = new Scanner(inFile);
                while(inputStream.hasNextLine()) {
                    String temp = inputStream.nextLine();
                    String[] tokens = temp.split(",");
                    Subject tempSubject = new Subject(tokens[0],  Boolean.parseBoolean(tokens[2]), Double.parseDouble(tokens[1]));
                    subjectSave.add(tempSubject);
                }
            }catch (FileNotFoundException e) {
                e.getMessage();
            }
        }
        else {
            System.out.println("Invalid file");
        }
        return subjectSave;
    }
}
