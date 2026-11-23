
package budgettracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;


public class BudgetTracker extends Application {

    @Override 
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        VBox tabViews = new VBox();
        tabViews.setSpacing(20);
        HBox firstRowOfMonths = new HBox();
        HBox additionalViews = new HBox();

        Button januaryView = new Button("January");
        Button februaryView = new Button("February");
        Button marchView = new Button("March");
        Button aprilView = new Button("April");
        Button mayView = new Button("May");
        Button juneView = new Button("June");
        Button julyView = new Button("July");
        Button augustView = new Button("August");
        Button septemberView = new Button("September");
        Button octoberView = new Button("October");
        Button novemberView = new Button("November");
        Button decemberView = new Button("December");
        Button yearToDate = new Button("Year to Date");
        Button transactionInputs = new Button("Transaction Inputs");

        firstRowOfMonths.getChildren().addAll(januaryView, februaryView, marchView, aprilView, mayView, juneView, julyView, augustView, septemberView, octoberView, novemberView, decemberView);
        additionalViews.getChildren().addAll(yearToDate, transactionInputs);

        firstRowOfMonths.getChildren().stream()
            .map(node -> (Button) node)
            .forEach(button -> button.setPrefWidth(80));

        firstRowOfMonths.setSpacing(20);
        additionalViews.setSpacing(20);

        tabViews.getChildren().addAll(firstRowOfMonths, additionalViews);
        layout.setTop(tabViews);

        window.setTitle("Budget Tracker");

        Scene scene = new Scene(layout);

        window.setWidth(1200);
        window.setHeight(600);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) throws Exception{
        launch(BudgetTracker.class);
    }
}
