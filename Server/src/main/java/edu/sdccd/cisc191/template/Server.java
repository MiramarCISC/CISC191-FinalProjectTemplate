package org.example;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//for menu
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;


public class Server extends Application{


    private static GUILabel message; // to hold the title of this GUI


    public static SurfLocation[][] surfLocations = new SurfLocation[2][2]; //creates an array of locations
    public static SurfReport[][] surfReports = new SurfReport[2][2]; //creates an array of surf reports
    private static java.util.Date Date;

    public static void main(String[] args) {

        //getAtIndex, setAtIndex, findIndexOf, printAll, deleteAtIndex, expand, shrink


        populateSurfLocations();
        printSurfLocations(surfLocations);
        populateSurfReports();
        launch();//must extend Application
        System.out.println(message);

        for (int i = 0; i < surfReports.length; i++) {
         System.out.println(surfReports[i]);
        }

    }
    //public static void loadSurfLocations(){ //preloading with example
        //surfLocations[0][0] = new SurfLocation("La Jolla Shores", false, "rocky cliffs");

   // }
   public static void populateSurfLocations() {
       surfLocations[0][0] = new SurfLocation("Huntington Beach", false, "sandy beach");
       surfLocations[0][1] = new SurfLocation("Malibu", false, "point break");
       surfLocations[1][0] = new SurfLocation("Trestles", true, "sandy beach");
       surfLocations[1][1] = new SurfLocation("Rincon", false, "point break");
   }
    public static void  printSurfLocations(SurfLocation surfLocationsArray[][]) {

        // Loop through all rows in succession
        for (int i = 0; i < surfLocationsArray.length; i++)

            // Loop through all elements of current row
            for (int j = 0; j < surfLocationsArray[i].length; j++)
                System.out.println(surfLocationsArray[i][j] + " ");

    }



public static void populateSurfReports(){

        surfReports[0][0] = new SurfReport(Date,"Wave height is around 7ft, onshore winds blowing 6mph, tide height is mid to high",3.5,15,2);
        surfReports[0][1] = new SurfReport(Date, "Wave height is around 8ft, onshore winds blowing 5mph, tide height is high",3.5,15,2);
        surfReports[1][0] = new SurfReport(Date, "Wave height is around 1.5ft, offshore winds blows 4mph, tide height is low",3.5,15,2);
        surfReports[1][1] = new SurfReport(Date,"Wave height is around 6ft, onshore winds blowing 3mph, tide height is mid to high",3.5,15,2);

    }
    public SurfReport[][] getSurfReports() {
        return surfReports;
    }
    public static void displaySurfLocations(SurfLocation location) {
        // Check if a surf report exists for the selected location
        //SurfReport new surfReports  = getSurfReports();

        SurfReport surfReports = location.getSurfReports();

        if (surfReports != null) {

            // Assuming you have a canvas to display the surf report, you can update it like this:
            message.setText("Surf Report for " + location.getBeachName() +  "\n" + "Date: " + surfReports.getDate() + "\n" + "Wave Height: " + surfReports.getWaveHeight() + " ft\n" + "Wind Speed: " + surfReports.getWindSpeed() + " mph\n" + "Tide Height: " + surfReports.getTideHeight() + " ft\n" );
            // You can format and display the surf report details


            // Display the report on the canvas

        } else {
            // Handle the case where no surf report is available for the selected location
            System.out.println("No surf report available for " + location.getBeachName());
        }
    }



    //private static void loadSurfReports() {
        //SurfReportImporter surfReportImporter = new SurfReportImporterText();
        //uses interface to access the object of the implemented class
        // The data type (the interface SurfReportImporter) is a pointer.
        // surfReportImporter points to the object SurfReportImporterText.
        // The code that follows is an interface method.
        // Do not want main method to care about importing surf report data.
        // We only want main to care about methods in the interface SurfReportImporter.
        //surfReportImporter.importSurfReport();
        //this method (importSurfReport()) is defined
        // in the class that implements the interface SurfReportImporter (SurfReportImporterText)
        //TODO import array of surf reports into a 2D array that will be created.



    @Override
    public void start(Stage stage) throws Exception {//begin method "start"
        Canvas reportCanvas = new Canvas(); //calls Canvas constructor, returns object

        message = new GUILabel();
        message.setText("Select Your Surf Spot");
        BorderPane borderPane= new BorderPane(); // I used variable borderPane, an instance object of a class, BorderPane
        borderPane.setCenter(reportCanvas);
        HBox headerHbox = new HBox(10,message);
        borderPane.setTop(headerHbox);

        // Create FlowPane
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < surfLocations.length; i++) {
            for (int j = 0; j < surfLocations[i].length; j++) {
                if (surfLocations[i][j] != null) {
                    SurfLocation location = surfLocations[i][j];
                    Button locationButton = new Button(location.getBeachName());
                    locationButton.setOnAction(e -> displaySurfLocations(location));
                    flowPane.getChildren().add(locationButton);
                }
            }
        }

        //menu
        //Create menu
        Menu SurfSpotMenu = new Menu("Location");
        //create items for location menu
        javafx.scene.control.MenuItem locationMenu1 = new javafx.scene.control.MenuItem("City of San Diego");
        javafx.scene.control.MenuItem locationMenu2 = new javafx.scene.control.MenuItem( "San Diego County");
        javafx.scene.control.MenuItem locationMenu3 = new javafx.scene.control.MenuItem( "Southern California");
        //add items to menu
        SurfSpotMenu.getItems().addAll(locationMenu1, locationMenu2, locationMenu3);

        Menu EnvironmentMenu=new Menu("Environment");
        //create items for environment menu
        javafx.scene.control.MenuItem EnvironmentMenu1=new javafx.scene.control.MenuItem("Sandy Beach");
        javafx.scene.control.MenuItem EnvironmentMenu2=new javafx.scene.control.MenuItem("Point Break");
        javafx.scene.control.MenuItem EnvironmentMenu3 =new MenuItem("Pier");
        // add items to menu
        EnvironmentMenu.getItems().addAll(EnvironmentMenu1, EnvironmentMenu2, EnvironmentMenu3);
        //create menu bar
        MenuBar menuBar = new MenuBar(SurfSpotMenu, EnvironmentMenu);

        borderPane.setLeft(menuBar);
        //display menu


        // create scene, stage, set title, and show
        Scene scene = new Scene(borderPane, 300, 250);//created scene
        stage.setScene(scene);// created stage
        stage.setTitle("Surf Locations");//set title
        stage.show();//created show

    }//end method "start"

    //public void displaySurfLocation(SurfLocation location) {
        // Implement the logic to display the surf location's forecast
        // Update the canvas or any other UI element as needed
    }


//end class Server
