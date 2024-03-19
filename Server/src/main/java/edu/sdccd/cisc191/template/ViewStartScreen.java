package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
public class ViewStartScreen extends Application {
    private int screenWidth, screenHeight; //allows buttons to be scaled accordingly
    private int selectedIndex;
    private BorderPane layout;
    private Stage stage;
    private Scene sceneClassName;
    private ArrayList<Subject> subjectArrayList = new ArrayList<>();

    private String savedSchedule;

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set. The primary stage will be embedded in
     *              the browser if the application was launched as an applet.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages and will not be embedded in the browser.
     *              Initial screen that the user sees
     *              Hooray for javafx
     */
    public void start(Stage stage) throws IOException {
        //variables???
        Glow glow = new Glow();
        glow.setLevel(0.2);
        this.stage = stage;
        // 720x1200 resolution
        screenWidth = 1000;
        screenHeight = 1000;
        Font font = Font.font("Montserrat", FontWeight.BOLD, 36);

        //button to direct the user to set up
        OptionButton setupButton = new OptionButton("Make your Schedule", 500, 100);
        setupButton.changeTextColor(Color.web("#34A3ED"));
        setupButton.changeBackGroundColor();
        setupButton.setFont(font);
        setupButton.setWrapText(true);

        //button for import of csv file of saved schedule
        OptionButton importCSVButton = new OptionButton("Import from File", 500, 100);
        importCSVButton.changeTextColor(Color.web("#34A3ED"));
        importCSVButton.changeBackGroundColor();
        importCSVButton.setFont(font);
        importCSVButton.setWrapText(true);

        //button for import from text field (from website save)
        OptionButton importTextButton = new OptionButton("Import from Text", 500, 100);
        importTextButton.changeTextColor(Color.web("#34A3ED"));
        importTextButton.changeBackGroundColor();
        importTextButton.setFont(font);
        importTextButton.setWrapText(true);

        //title and credits to the authors
        Label title = new Label("Schedule and Homework Tracker");
        Label credits = new Label("Credits: Logan, Simon, Theo, Willy");
        title.setFont(font);
        //organize title and setup button to be spaced accordingly, set it in center
        VBox buttons = new VBox((double) screenHeight / 120, title, setupButton, importCSVButton, importTextButton);
        Image image = new Image(Files.newInputStream(Paths.get("Server/src/main/java/edu/sdccd/cisc191/template/Homework-modified.png")));
        Image color = new Image(Files.newInputStream(Paths.get("Server/src/main/java/edu/sdccd/cisc191/template/Homework.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        buttons.getChildren().addAll(imageView);

        buttonEffects(glow, setupButton, image, color, imageView);

        buttonEffects(glow, importCSVButton, image, color, imageView);

        buttonEffects(glow, importTextButton, image, color, imageView);

        setupButton.setOnAction((ActionEvent e) -> {
            try {
                runSetup2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        importCSVButton.setOnAction((ActionEvent e) -> {
            subjectArrayList = convertCSVFileToSubject();
            try {
                runMainScreen(subjectArrayList, selectedIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        importTextButton.setOnAction((ActionEvent e) -> {
            try {
                convertPlainTextToSubject();
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
        Scene startScene = new Scene(layout, screenWidth, screenHeight);
        startScene.setFill(Color.web("#81c483"));
        stage.setTitle("Schedule and Homework Tracker");
        stage.setScene(startScene);
        stage.show();
    }

    /**
     *
     * @param glow, the amount of the glow when hovering/interacting with a button
     * @param setupButton, the button which will be imbued with given effects
     * @param image,
     * @param color
     * @param imageView
     */
    private void buttonEffects(Glow glow, OptionButton setupButton, Image image, Image color, ImageView imageView) {
        setupButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            setupButton.setEffect(glow);
            imageView.setImage(color);

        });
        setupButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            setupButton.setEffect(null);
            imageView.setImage(image);
        });
    }

    public static void main(String[] args) {
        launch();
    }


    /**
     * First screen the user sees when
     *
     * @throws Exception when there's error :)
     */
    public void runSetup2() throws Exception {
        Font font = Font.font("Montserrat", FontWeight.BOLD, 18);

        Label promptName = new Label("Enter the name of the class you would like to add");
        TextField name = new TextField();
        name.setFont(font);
        name.setPrefSize(screenWidth / 2.0, screenHeight / 10.0);
        name.setMaxWidth(screenWidth / 2.0);
        promptName.setFont(font);

        CheckBox promptWeighted = new CheckBox("Is the class weighted?");
        promptWeighted.setFont(font);

        Label promptGrade = new Label("What is your current grade in the class?");
        promptGrade.setFont(font);

        TextField grade = new TextField();
        grade.setPrefSize(screenWidth / 2.0, screenHeight / 8.0);
        grade.setMaxWidth(screenWidth / 2.0);
        grade.setFont(font);
        //prevents any compiler errors when adding to subjectArrayList
        OptionButton confirm = new OptionButton("Confirm", screenWidth / 6.0, screenHeight / 24.0);
        Label colorChoice = new Label("What color would you like the subject to be?");
        colorChoice.setFont(font);
        ChoiceBox<String> dropDown = new ChoiceBox<>();
        dropDown.getItems().add("Red");
        dropDown.getItems().add("Blue");
        dropDown.getItems().add("Yellow");
        dropDown.getItems().add("Green");
        dropDown.getItems().add("Orange");
        dropDown.getItems().add("Purple");
        dropDown.setOnAction((event) -> {
            int selectedIndex = dropDown.getSelectionModel().getSelectedIndex();
        });
            /* adds first class to subject array list
               directs to main interface
             */
        confirm.setOnAction((ActionEvent yes) -> {
            try {

                selectedIndex = dropDown.getSelectionModel().getSelectedIndex();
                Subject tempSubject = new Subject(name.getText(), Double.parseDouble(grade.getText()));
                tempSubject.setColor(selectedIndex);
                int tempColor = tempSubject.getColor();


                subjectArrayList.add(tempSubject);

                runMainScreen(subjectArrayList, tempColor);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("INPUT ERROR");
                alert.setContentText("YOUR GRADE, " + "'" + ((grade.getText())) + "'" + " IS NOT A NUMBER" +
                        "\nTRY AGAIN");
                alert.showAndWait();
            }
        });

        VBox buttons = new VBox(50);
        //TODO Add something to get the information of the color


        buttons.setStyle("-fx-background-color: #FFF1DC;");
        buttons.getChildren().addAll(promptName, name, promptGrade, grade, promptWeighted, colorChoice, dropDown, confirm);
        buttons.setAlignment(Pos.CENTER);
        layout = new BorderPane(buttons);

        name.clear();
        grade.clear();
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Set your classes");
        stage.show();
    }

    /**
     * switchScene to handle repetitive action
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
     */
    public void runMainScreen(ArrayList<Subject> a, int colorNumber) throws Exception {
        Label classList = new Label("Your Classes");
        classList.setStyle("-fx-font-size: 20; -fx-underline: true; -fx-font-weight: bold;");
        VBox classes = new VBox(screenHeight / 240.0, classList);
        classes.setStyle("-fx-background-color: #FFF1DC;");
        classes.setAlignment(Pos.TOP_LEFT);

        // set individual color for each subject
        for (Subject subject : a) {
            OptionButton button = new OptionButton(subject.getNameOfSubject(), screenWidth / 3, screenHeight / 10);


            button.changeTextColor(subject.getColor()); // Set color based on subject
            button.setOnAction((ActionEvent e) -> {
                try {
                    int subjectIndex = a.indexOf(subject);
                    viewAssignmentList(subjectIndex);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            classes.getChildren().add(button);
        }

        //allows user to add another class to the list
        OptionButton addClass = new OptionButton("Add Class", screenWidth / 3, screenHeight / 17.5);
        addClass.setOnAction((ActionEvent e) -> {
            try {
                runSetup2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        OptionButton saveSchedule = new OptionButton("Save Schedule", screenWidth / 3, screenHeight / 17.5);
        saveSchedule.setOnAction((ActionEvent e) -> {
            convertSubjectToCSV(a);
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
     *                                                   TODO deal with weird user inputs
     */
    public void viewAssignmentList(int subjectArrayIndex) {
        Subject subject = new Subject(subjectArrayList.get(subjectArrayIndex));
        ArrayList<Assignment> tempArray = subject.getAssignmentList();
        Label nameOfSubject = new Label(subjectArrayList.get(subjectArrayIndex).getNameOfSubject());
        nameOfSubject.setStyle("-fx-font-size: 20; -fx-underline: true; -fx-font-weight: bold;");

        VBox assignments = new VBox(screenHeight / 240.0, nameOfSubject);
        assignments.setStyle("-fx-background-color: #FFF1DC;");
        assignments.setAlignment(Pos.TOP_LEFT);
        OptionButton addAssignment = new OptionButton("Add Assignment", screenWidth / 3.0, screenHeight / 17.5);
        addAssignment.setOnAction((ActionEvent e) -> {
            try {
                addAssignment(subjectArrayIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        OptionButton backButton = new OptionButton("Back", screenWidth / 3.0, screenHeight / 17.5);
        backButton.setOnAction((ActionEvent e) -> {
            try {
                runMainScreen(subjectArrayList, selectedIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        for (int i = 0; i < tempArray.size(); i++) {
            Assignment assignment = tempArray.get(i);
            OptionButton button = new OptionButton(assignment.getNameOfAssignment(), screenWidth / 3.0, screenHeight / 10.0);
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
        Label assignmentLabel = new Label("Assignment Name: " + selectedAssignment.getNameOfAssignment() + "\n" +
                "Total Points Possible: " + Double.toString(selectedAssignment.getTotalPoints()));


        // Create a button to go back to the assignment list
        OptionButton backButton = new OptionButton("Back to Assignment List", screenWidth / 5.0, screenHeight / 17.5);
        backButton.setOnAction((ActionEvent e) -> {
            try {
                viewAssignmentList(subjectIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Create a VBox to hold the labels and button
        VBox assignmentInfoLayout = new VBox(screenHeight / 60.0, assignmentLabel, backButton);
        assignmentInfoLayout.setAlignment(Pos.CENTER);

        // Set the layout for the scene
        layout = new BorderPane(assignmentInfoLayout);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Assignment Details");
        stage.show();
    }

     // points earned is killed bc homework planner assumes assignments have not been done yet
    public void addAssignment(int subjectIndex) {
        Subject subject = subjectArrayList.get(subjectIndex); // Get the subject from the ArrayList
        Label assignmentNameLabel = new Label("Enter name of Assignment:");
        TextField assignmentNameField = new TextField();

        //Label assignmentPointsLabel = new Label("Enter amount of points earned on Assignment:");
         // TextField assignmentPointsField = new TextField();

        Label assignmentTotalPointsLabel = new Label("Enter number of points for Assignment:");
        TextField assignmentTotalPointsField = new TextField();

        assignmentNameField.setPrefSize(screenWidth / 2.0, screenHeight / 8.0);
        // assignmentPointsField.setPrefSize(screenWidth / 2.0, screenHeight / 20.0);
        assignmentTotalPointsField.setPrefSize(screenWidth / 2.0, screenHeight / 20.0);

        OptionButton confirmButton = new OptionButton("Confirm", screenWidth / 6.0, screenHeight / 24.0);
        confirmButton.setOnAction((ActionEvent e) -> {
            try {
                String assignmentName = assignmentNameField.getText();
                // int assignmentPoints = Integer.parseInt(assignmentPointsField.getText());
                int totalAssignmentPoints = Integer.parseInt(assignmentTotalPointsField.getText());
                // Create a new Assignment object with the entered name
                Assignment assignment = new Assignment(assignmentName);
                // assignment.setPointsOfAssignment(assignmentPoints);
                assignment.setTotalPoints(totalAssignmentPoints);
                // Add the assignment to the subject's ArrayList
                subject.addAssignment(assignment);
            }
            catch (Exception error) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("INPUT ERROR");
                alert.setContentText("YOUR GRADE, " + "'" + ((assignmentTotalPointsField.getText())) + "'" + " IS NOT A NUMBER" +
                        "\nTRY AGAIN");
                alert.showAndWait();
            }
            try {
                runMainScreen(subjectArrayList, selectedIndex); // Refresh the main screen with the updated assignment list
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox buttons = new VBox(screenHeight / 60.0, assignmentNameLabel, assignmentNameField, assignmentTotalPointsLabel, assignmentTotalPointsField, confirmButton);
        layout = new BorderPane(buttons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
        switchScene(sceneClassName, "Add Assignment");
        stage.show();
    }

    /**
     * Convert the list of subjects to a CSV file
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
        try (FileWriter writer = new FileWriter(saveLocation)) {
            for (Subject subject : a) {
                writer.append(subject.getNameOfSubject())
                        .append(',')
                        .append(String.valueOf(subject.getGradeInClass()))
                        .append(',')
                        .append(String.valueOf(subject.isWeighted()))
                        .append(',')
                        .append(String.valueOf(subject.getColor()))
                        .append(',');
                ArrayList<Assignment> temp = subject.getAssignmentList();
                for (Assignment assignment : temp) {
                    writer.append(assignment.getNameOfAssignment()).append(',');
                }
                writer.append("\n");
            }
            writer.close();

            //msg to javafx success
        } catch (IOException e) {
        }
    }

    /**
     * allows the user to import a previously saved schedule in the form of a csv file
     *
     * @return subject array to be imported
     */
    public ArrayList<Subject> convertCSVFileToSubject() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.TXT"),
                new FileChooser.ExtensionFilter("txt files", "*.txt"));
        File inFile = fc.showOpenDialog(null);
        ArrayList<Subject> subjectSave = new ArrayList<>();
        if (inFile != null) {
            try {
                Scanner inputStream = new Scanner(inFile);
                while (inputStream.hasNextLine()) {
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    String temp = inputStream.nextLine();
                    String[] tokens = temp.split(",");
                    Subject tempSubject = new Subject(tokens[0], Boolean.parseBoolean(tokens[2]), Double.parseDouble(tokens[1]));
                    tempSubject.setColor(Integer.parseInt(tokens[3]));
                    for (int i = 4; i < tokens.length; i++) {
                        assignments.add(new Assignment(tokens[i]));
                    }
                    tempSubject.setAssignmentList(assignments);
                    subjectSave.add(tempSubject);
                }
            } catch (FileNotFoundException e) {
                e.getMessage();
            }
        } else {
            System.out.println("Invalid file");
        }
        return subjectSave;
    }

    public void convertPlainTextToSubject() {
        // Create a TextArea on new page for the user to input the schedule text
        TextArea scheduleText = new TextArea();
        scheduleText.setPrefSize(screenWidth, screenHeight / 3.0);

        // Creates a confirm button to process the entered text when done
        OptionButton confirmButton = new OptionButton("Confirm", screenWidth / 6.0, screenHeight / 10);
        ArrayList<Subject> subjectSave = new ArrayList<>();
        confirmButton.setOnAction((ActionEvent e) -> {
            String[] tokens = scheduleText.getText().split("\n");
            String[] tokens1;
            for (int i = 0; i < tokens.length; i++) {
                ArrayList<Assignment> assignments = new ArrayList<>();
                tokens1 = tokens[i].split(",");
                Subject tempSubject = new Subject(tokens1[0], Boolean.parseBoolean(tokens1[2]), Double.parseDouble(tokens1[1]));
                tempSubject.setColor(Integer.parseInt(tokens1[3]));
                for (int j = 4; j < tokens1.length; j++) {
                    assignments.add(new Assignment(tokens1[j]));
                }
                tempSubject.setAssignmentList(assignments);
                subjectSave.add(tempSubject);

            }
            try {
                // After processing the text, switch to the main screen
                runMainScreen(subjectSave, selectedIndex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Create a VBox to hold the text input field and the confirm button
        VBox buttons = new VBox(20, scheduleText, confirmButton);
        buttons.setAlignment(Pos.CENTER);

        // Set up the layout
        layout = new BorderPane(buttons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight); // Set scene dimensions
        switchScene(sceneClassName, "Import Schedule From Text");
        stage.show();
    }

    /**
     * used for tomcat activities
     * @param a subject array to convert
     * @return string consisting of all schedule info
     */
    public String convertEverythingToString(ArrayList<Subject> a) {
        String finalString = "";
        String temp = "";
        for (Subject subject : a) {
            temp = subject.getNameOfSubject() + ',' + String.valueOf(subject.getGradeInClass()) + ',' +
                    String.valueOf(subject.isWeighted()) + ',' + String.valueOf(subject.getColor()) + ',';
            finalString += temp;
            ArrayList<Assignment> assignments = subject.getAssignmentList();
            for (int j = 0; j < assignments.size(); j++) {
                finalString += assignments.get(j).getNameOfAssignment() + (',');
            }
            finalString += "\n";
        }
        return finalString;
    }
    public void chooseSchedule(ArrayList<Subject> a){
        OptionButton saveFile = new OptionButton("Save as File", screenWidth/3.0, screenHeight/2.0);
        OptionButton saveRemote = new OptionButton("Save Remotely", screenWidth/3.0, screenHeight/2.0);
        HBox buttons = new HBox(50,saveFile, saveRemote);
        buttons.setAlignment(Pos.CENTER);

        saveFile.setOnAction((ActionEvent e) -> {
            convertSubjectToCSV(a);
        });

        saveRemote.setOnAction((ActionEvent e) -> {
           //TODO figure this out for me logan
           savedSchedule = convertEverythingToString(a);
        });
        //set layout
        layout = new BorderPane(buttons);
        sceneClassName = new Scene(layout, screenWidth, screenHeight);
            switchScene(sceneClassName, "Choose Save Option");
        stage.show();
    }
    public String getSavedSchedule() {
        return savedSchedule;
    }
}