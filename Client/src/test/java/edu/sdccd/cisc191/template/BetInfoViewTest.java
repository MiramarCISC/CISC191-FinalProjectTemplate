package edu.sdccd.cisc191.template;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class BetInfoViewTest {

    @BeforeAll
    public static void initJFX() throws Exception {
        // Set the default timezone to UTC so that time labels are predictable.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        if (!Platform.isFxApplicationThread()) {
            final CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(() -> latch.countDown());
            if (!latch.await(5, TimeUnit.SECONDS)) {
                throw new Exception("JavaFX platform did not start.");
            }
        }
    }

    // Dummy implementation of Bet to supply known data.
    private static class DummyBet extends Bet {
        @Override
        public double[][] getWinOddsOvertime() {
            // Return two data points:
            // First point: odd=1.5 at timestamp 1609459200 (which formats to "00:00" in UTC)
            // Second point: odd=2.0 at timestamp 1609462800 (which formats to "01:00" in UTC)
            return new double[][] {
                    {1.5, 1609459200},
                    {2.0, 1609462800}
            };
        }
        @Override
        public String toString() {
            return "Dummy Bet";
        }
    }

    @Test
    public void testDisplayWinOddsData() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                DummyBet dummyBet = new DummyBet();
                BetInfoView view = new BetInfoView();
                // Launch the view using the betInfoView() method.
                view.betInfoView(stage, dummyBet);
                Scene scene = stage.getScene();
                // Get the root layout (VBox) from the scene.
                VBox root = (VBox) scene.getRoot();
                // The line chart is added as the third child (index 2) in the VBox.
                LineChart<?, ?> lineChart = (LineChart<?, ?>) root.getChildren().get(2);
                ObservableList<? extends XYChart.Series<?, ?>> seriesList = lineChart.getData();
                assertEquals(1, seriesList.size(), "There should be one data series in the line chart.");

                XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) seriesList.get(0);
                assertEquals("Win Odds", series.getName(), "Series name should be 'Win Odds'.");
                ObservableList<XYChart.Data<String, Number>> dataPoints = series.getData();
                assertEquals(2, dataPoints.size(), "Series should contain 2 data points.");

                // Validate the first data point.
                XYChart.Data<String, Number> firstData = dataPoints.get(0);
                assertEquals("00:00", firstData.getXValue(), "First data point time label should be '00:00'.");
                assertEquals(1.5, firstData.getYValue().doubleValue(), 0.001, "First data point odd should be 1.5.");

                // Validate the second data point.
                XYChart.Data<String, Number> secondData = dataPoints.get(1);
                assertEquals("01:00", secondData.getXValue(), "Second data point time label should be '01:00'.");
                assertEquals(2.0, secondData.getYValue().doubleValue(), 0.001, "Second data point odd should be 2.0.");
            } catch(Exception e) {
                e.printStackTrace();
                fail("Exception in Platform.runLater: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Test did not complete in time.");
        }
    }
}