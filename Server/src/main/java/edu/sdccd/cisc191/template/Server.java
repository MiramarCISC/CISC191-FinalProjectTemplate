package edu.sdccd.cisc191.template;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Server extends Application {
    private AppLabel quoteLabel = new AppLabel();
    private Quote quote = new Quote();
    private AppLabel homeworkLabel = new AppLabel();
    private AppLabel notesLabel = new AppLabel();
    private TextArea notesArea = new TextArea();
    private HomeworkButton homeworkButton = new HomeworkButton();
    private SubjectButton subjectButton = new SubjectButton();
    private AppLabel newHomework = new AppLabel();
    private TextField inputHomeworkName = new TextField();
    private CheckBox urgentCheck = new CheckBox();
    private AppLabel completedLabel = new AppLabel();
    private CheckBox completedCheck = new CheckBox();
    private DatePicker inputDueDate = new DatePicker();
    private ComboBox<String> subjectBox = new ComboBox<>();
    private Subject userSubject = new Subject();
    private TextField inputSubjectName = new TextField();
    private TextField inputSubjectNumber = new TextField();
    private TimerButton timerButton = new TimerButton();
    private AppLabel timerLabel = new AppLabel();
    private Time time = new Time();
    private Timeline timeline = new Timeline();
    private int minutes = 0;
    private int seconds = 0;
    private int secondsTotal;
    private TextField inputTimerMinutes = new TextField();
    private ListView<String> homeworkListView = new ListView<>();
    private AppLabel studyMessage = new AppLabel();
    private SnackButton snackButton = new SnackButton();
    private SandwichButton sandwichButton = new SandwichButton();
    private DessertButton dessertButton = new DessertButton();
    private AppLabel snackLabel = new AppLabel();
    private AppLabel drinkLabel = new AppLabel();
    private Sandwich sandwich = new Sandwich();
    private Dessert dessert = new Dessert();

    public Server() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //set label text and prompt text
        homeworkLabel.setText("Homework");
        notesLabel.setText("Notes");
        newHomework.setText("New Homework");
        timerLabel.setText("00:00");
        subjectBox.setPromptText("Select One");
        notesArea.setPromptText("Type Notes Here");
        studyMessage.setText("Hello!");
        completedLabel.setText("Mark as Complete: ");
        snackLabel.setText("Choose Snack Type");
        drinkLabel.setText("");
        quoteLabel.setText(quote.getQuote());

        //set presets for visibility
        snackButton.setVisible(false);
        drinkLabel.setVisible(false);

        //set BorderPane and Boxes for layout
        BorderPane root = new BorderPane();
        HBox timerRow = new HBox(10, timerButton, timerLabel, studyMessage, snackButton);
        HBox homeworkRow = new HBox(10,subjectButton, homeworkButton);
        HBox buttonBar = new HBox(10, homeworkRow, timerRow);
        HBox homeworkBar = new HBox(15, homeworkLabel, completedLabel, completedCheck);
        VBox homeworkBox = new VBox(5, homeworkBar, homeworkListView);
        VBox notesBox = new VBox(9, notesLabel, notesArea);
        HBox centerConsole = new HBox(5,homeworkBox, notesBox);

        //set preferred size for ListView and notes area
        homeworkListView.setPrefSize(400,400);
        notesArea.setPrefSize(400,400);

        //organize components in the Border Pane
        root.setTop(quoteLabel);
        root.setCenter(centerConsole);
        root.setBottom(buttonBar);

        //add padding around the components
        BorderPane.setMargin(quoteLabel, new Insets(10, 10, 5, 10));
        BorderPane.setMargin(centerConsole, new Insets(5, 10, 5, 10));
        BorderPane.setMargin(buttonBar, new Insets(5, 10, 10, 10));

        //TODO move this to a css file
        root.setStyle("-fx-background-color: #F2E3F5");
        notesArea.setStyle("-fx-border-color: #E6F5E3; -fx-border-width: 5; -fx-background-color: #E6F5E3");
        homeworkListView.setStyle("-fx-control-inner-background: #FBF6FC; -fx-border-color: #FDFCE5; -fx-border-width: 5");

        //TODO move this someplace better
        completedCheck.setOnAction(e -> {
            int index = homeworkListView.getSelectionModel().getSelectedIndex();
            if (index != -1 && completedCheck.isSelected()) {
                homeworkListView.getItems().remove(index);
                completedCheck.setSelected(false);
            }
        });

        //Homework popup
        HomeworkPopup homeworkPopup = new HomeworkPopup();
        VBox homeworkAdding = homeworkPopup.getHomeworkAdding(inputHomeworkName, urgentCheck, inputDueDate, subjectBox);
        homeworkPopup.getContent().addAll(homeworkAdding);

        
         * creates a new homework entry
         * @pa
         */
        homeworkButton.setOnAction(e -> {
            if (!homeworkPopup.isShowing()) {
                homeworkPopup.show(stage);
                homeworkButton.setText("Create");
            }
            else {
                homeworkPopup.hide();
                homeworkButton.setText("New Homework");
                //get the homework entry information
                String name = homeworkPopup.getName(inputHomeworkName);
                String subject = homeworkPopup.getSubject(subjectBox);
                boolean urgent = homeworkPopup.getUrgent(urgentCheck);
                String date = homeworkPopup.getDate(inputDueDate);
                //create a new homework entry with data inputted/selected by user
                Homework newHwEntry = new Homework(name, subject, urgent, date);
                //print the new homework entry as a string and pass it to the list
                String homeworkString = newHwEntry.getHomework();
                homeworkString += newHwEntry.isUrgent();
                //add new homework entry string to the list.
                homeworkListView.getItems().add(homeworkString);
                //reset the values
                homeworkPopup.reset(inputHomeworkName, urgentCheck, inputDueDate, subjectBox);
            }
        });

        //Subject popup
        SubjectPopup subjectPopup = new SubjectPopup();
        VBox subjectAdding = subjectPopup.getSubjectAdding(inputSubjectName, inputSubjectNumber);
        subjectPopup.getContent().addAll(subjectAdding);

        //creates a new subject and saves it to the ComboBox
        subjectButton.setOnAction(e -> {
            if (!subjectPopup.isShowing()) {
                subjectPopup.show(stage);
                subjectButton.setText("Create");
            }
            else {
                userSubject.setSubjectName(inputSubjectName.getText());
                userSubject.setSubjectNumber(inputSubjectNumber.getText());
                subjectBox.getItems().add(userSubject.getSubject());
                subjectPopup.hide();
                subjectPopup.reset(subjectButton, inputSubjectName, inputSubjectNumber);
            }
        });

        //timer popup
        TimerPopup timerPopup = new TimerPopup();
        HBox timerAdding = timerPopup.getTimerAdding(inputTimerMinutes);
        timerPopup.getContent().add(timerAdding);

        //sets timer in minutes and timer label in mm:ss
        timerButton.setOnAction(e -> {
            if (!timerPopup.isShowing()) {
                timerPopup.show(stage);
                timerButton.setText("Set");
                snackButton.setVisible(false);
            }
            else {
                timerPopup.hide();
                secondsTotal = timerPopup.getSecondsTotal(inputTimerMinutes);
                inputTimerMinutes.clear();
                timerButton.setText("Timer");
                studyMessage.setText("Time to Study!");
                //updates time left every second
                //TODO move this in TimerPopup class as a method?
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
                    secondsTotal--;
                    minutes = secondsTotal / 60;
                    seconds = secondsTotal % 60;
                    timerLabel.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                    if (secondsTotal == 0) {
                        timeline.stop();
                        studyMessage.setText("Take a Break!");
                        snackButton.setVisible(true);
                    }
                });
                timeline.getKeyFrames().add(keyFrame);
                timeline.setCycleCount(secondsTotal);
                timeline.play();
            }
        });

        //snack popup
        SnackPopup snackPopup = new SnackPopup();
        HBox sandwichOrDessertRow = snackPopup.getSandwichOrDessert(sandwichButton, dessertButton);
        VBox snackPopupColumn = snackPopup.getSnackPopupColumn(sandwichOrDessertRow, snackLabel, drinkLabel);
        snackPopup.getContent().add(snackPopupColumn);

        //create a new snack
        this.snackButton.setOnAction((e) -> {
            if (!snackPopup.isShowing()) {
                snackPopup.show(stage);
                //create a savory sandwich
                snackButton.setText("Exit Generator");
                sandwichButton.setOnAction((event) -> {
                    sandwichOrDessertRow.setVisible(false);
                    snackLabel.setText(this.sandwich.getSnack());
                    drinkLabel.setVisible(true);
                    drinkLabel.setText(this.sandwich.getDrink());
                });
                //create a sweet dessert
                dessertButton.setOnAction((event) -> {
                    sandwichOrDessertRow.setVisible(false);
                    snackLabel.setText(this.dessert.getSnack());
                    drinkLabel.setVisible(true);
                    drinkLabel.setText(this.dessert.getDrink());
                });
            }
            else {
                snackPopup.hide();
                snackPopup.reset(snackButton, sandwichOrDessertRow, snackLabel, drinkLabel);
            }
        });

        //create main scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Homework App");
        stage.show();
    }
}