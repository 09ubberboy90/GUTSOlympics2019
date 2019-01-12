package datePicker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.concurrent.Task;

 
public class DatePickerSample extends Application {
 
    private Stage stage;
    private DatePicker checkInDatePicker;
    private DatePicker checkOutDatePicker;
    
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);                  
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("DatePickerSample ");
        initUI();
        stage.show();
    }
 
    private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        checkInDatePicker = new DatePicker();
        checkOutDatePicker = new DatePicker();
        checkInDatePicker.setValue(LocalDate.now());
        
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(checkInDatePicker.getValue().plusDays(1))) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }
                            Long time = ChronoUnit.DAYS.between(
                                    checkInDatePicker.getValue(), item
                            );
                            setTooltip(new Tooltip(
                                "You're about to stay for " + time + " days")
                            );
                    }
                };
            }
        };        
        checkOutDatePicker.setDayCellFactory(dayCellFactory);
        checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Label checkInlabel = new Label("Start Date:");
        gridPane.add(checkInlabel, 0, 0);
        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        Label checkOutlabel = new Label("End Date:");
        gridPane.add(checkOutlabel, 1, 0);
        GridPane.setHalignment(checkOutlabel, HPos.LEFT);
        gridPane.add(checkOutDatePicker, 1, 1);
        Label duration = new Label();
        GridPane.setHalignment(duration, HPos.LEFT);
        gridPane.add(duration, 0, 2);
        
        final Timeline timeline = new Timeline(
        	    new KeyFrame(
        	        Duration.millis( 500 ),
        	        event -> {final String durationTime = "You're about to stay for : " + Long.toString(ChronoUnit.DAYS.between(
        	                checkInDatePicker.getValue(), checkOutDatePicker.getValue()))+ " days";
        	        duration.setText(durationTime);

        	        }
        	    )
        	);
        	timeline.setCycleCount( Animation.INDEFINITE );
        	timeline.play();

        vbox.getChildren().add(gridPane);
    }
}
