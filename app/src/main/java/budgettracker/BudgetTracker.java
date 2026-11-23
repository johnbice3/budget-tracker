
package budgettracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;


public class BudgetTracker extends Application {

    @Override 
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        HBox monthlyViews = new HBox();
        window.setTitle("Budget Tracker");

        Scene scene = new Scene(layout);

        window.setWidth(1200);
        window.setHeight(1000);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) throws Exception{
        launch(BudgetTracker.class);
    }
}
