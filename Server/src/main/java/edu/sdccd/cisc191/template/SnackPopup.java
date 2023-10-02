package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class SnackPopup extends Popup {

    private SandwichButton sandwichButton = new SandwichButton();
    private DessertButton dessertButton = new DessertButton();
    private AppLabel snackLabel = new AppLabel();
    private AppLabel drinkLabel = new AppLabel();
    private HBox sandwichOrDessertRow = new HBox();
    private SnackButton snackButton = new SnackButton();
    private AppLabel studyMessage = new AppLabel();

    /**
     * Sets contents and style of snack popup
     * @param sandwichOrDessertRow HBox row containing two buttons deciding between sandwich or dessert
     * @param snackLabel label that displays user's snack
     * @param drinkLabel label that displays user's drink
     * @return VBox containing popup's contents
     */
    public VBox getSnackPopupColumn(HBox sandwichOrDessertRow, AppLabel snackLabel, AppLabel drinkLabel) {
        VBox snackPopupColumn = new VBox(10, snackLabel, drinkLabel, sandwichOrDessertRow);
        snackPopupColumn.setStyle("-fx-background-color: #F2E3F5; -fx-border-color: #E6C8EC;-fx-border-width:5");
        snackPopupColumn.setPadding(new Insets(10.0));
        return snackPopupColumn;
    }

    /**
     * Row that contains two buttons - one for if user wants a sandwich and another for if they want dessert
     * @param sandwichButton changes snack label to a sandwich
     * @param dessertButton changes snack label to a dessert
     * @return HBox of two buttons
     */
    public HBox getSandwichOrDessert(SandwichButton sandwichButton, DessertButton dessertButton) {
        HBox sandwichOrDessertRow = new HBox(10, sandwichButton, dessertButton);
        return sandwichOrDessertRow;
    }

    /**
     * Resets contents of popup to default after user is finished
     * @param snackButton button only visible immediately after finishing a study session
     * @param sandwichOrDessertRow HBox row containing two buttons deciding between sandwich or dessert
     * @param snackLabel label that displays user's snack
     * @param drinkLabel label that displays user's drink
     */
    public void reset(SnackButton snackButton, HBox sandwichOrDessertRow, AppLabel snackLabel, AppLabel drinkLabel) {
        snackButton.setVisible(false);
        snackButton.setText("Snack Generator");
        sandwichOrDessertRow.setVisible(true);
        snackLabel.setText("Choose Snack Type");
        drinkLabel.setVisible(false);
    }
}
