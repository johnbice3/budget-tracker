
package budgettracker;

import java.math.BigDecimal;
import java.util.Locale;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.RoundingMode;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;



public class BudgetTracker extends Application {
    private ObservableList<Transaction> transactionsList = FXCollections.observableArrayList();
    private List<String> categoryList = new ArrayList<>(List.of("Groceries", "Mortgage", "Utilities", "Dining"));

    private VBox createMonthlyView(Month month){

        List<Transaction> monthlyTransactions = transactionsList.stream()
            .filter(transaction -> transaction.getDate().getMonth() == month)
            .toList();
        int monthlyTransactionCount = monthlyTransactions.size();
        BigDecimal monthlySpend = monthlyTransactions.stream()
            .map(transaction -> transaction.getAmount())
            .reduce(BigDecimal.ZERO, (currentAmount, transaction) -> currentAmount.add(transaction));

        Map<String, BigDecimal> transactionMap = new HashMap<>(); 
        for (String category: categoryList){
            transactionMap.put(category, BigDecimal.ZERO);
        }
        for (Transaction transaction: monthlyTransactions){
            BigDecimal currentTotal = transactionMap.get(transaction.getCategory());
            BigDecimal updatedTotal = currentTotal.add(transaction.getAmount());
            transactionMap.replace(transaction.getCategory(), updatedTotal);
        }
        VBox monthlyLayout = new VBox();
        HBox header = new HBox();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        String stringMonthlySpend = currencyFormatter.format(monthlySpend);
        Label monthInfo = new Label(month + " - Total Spend: " + stringMonthlySpend);
        Label transactionCount = new Label("Transactions: " + monthlyTransactionCount);
        HBox chartArea = new HBox();
        PieChart transactionSummary = new PieChart();
        TableView<CategorySummary> dataTable = new TableView<>();
        TableColumn<CategorySummary, String> category = new TableColumn<>("Category");
        TableColumn<CategorySummary, BigDecimal> amount = new TableColumn<>("Amount");
        TableColumn<CategorySummary, Double> percentage = new TableColumn<>("Percentage");
        TableColumn<CategorySummary, Long> transactions = new TableColumn<>("Transactions");

        dataTable.getColumns().addAll(category, amount, percentage, transactions);
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (Map.Entry<String, BigDecimal> entry: transactionMap.entrySet()){
            if (entry.getValue().compareTo(BigDecimal.ZERO) > 0){
                chartData.add(new PieChart.Data(entry.getKey(), entry.getValue().doubleValue()));
            }
        }
        ObservableList<CategorySummary> categorySummaryData = FXCollections.observableArrayList();
        for (String categoryName: categoryList){
            BigDecimal categoryAmount = transactionMap.get(categoryName);
            long categoryTransactionCount = monthlyTransactions.stream()
                .filter(transaction -> transaction.getCategory().equals(categoryName))
                .count();
            BigDecimal categoryPercentage = BigDecimal.ZERO;
            if (monthlySpend.compareTo(BigDecimal.ZERO) > 0) {
                categoryPercentage = categoryAmount.divide(monthlySpend, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            } 
            categorySummaryData.add(new CategorySummary(categoryName, categoryAmount, categoryPercentage.doubleValue(), categoryTransactionCount));
        }

        category.setCellValueFactory(cellData ->
            new ReadOnlyObjectWrapper<>(cellData.getValue().getCategory())   
        );
        amount.setCellValueFactory(cellData -> 
            new ReadOnlyObjectWrapper<>(cellData.getValue().getAmount())
        );
        amount.setCellFactory(column -> new TableCell<CategorySummary, BigDecimal>(){
            @Override 
            protected void updateItem(BigDecimal item, boolean empty){
                super.updateItem(item, empty);

                if (empty){
                    setText(null);
                } else {
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
                    setText(currencyFormatter.format(item));
                }
                
            }
        });
        percentage.setCellValueFactory(cellData ->
            new ReadOnlyObjectWrapper<>(cellData.getValue().getPercentage())
        );
        transactions.setCellValueFactory(cellData ->
            new ReadOnlyObjectWrapper<>(cellData.getValue().getTransactionCount())
        );

        transactionSummary.setData(chartData);
        transactionSummary.setLabelsVisible(true);
        dataTable.setItems(categorySummaryData);

        chartArea.getChildren().addAll(transactionSummary, dataTable);
        header.getChildren().addAll(monthInfo, transactionCount);
        monthlyLayout.getChildren().addAll(header, chartArea);
        header.setSpacing(15);

        return monthlyLayout;
    }

    @Override 
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        VBox tabViews = new VBox();
        tabViews.setSpacing(20);
        HBox firstRowOfMonths = new HBox();
        HBox additionalViews = new HBox();
        window.setTitle("Budget Tracker");

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

        januaryView.setOnAction(event -> 
            layout.setCenter(createMonthlyView(Month.JANUARY))
        );
        februaryView.setOnAction(event ->
            layout.setCenter(createMonthlyView(Month.FEBRUARY))
        );
        transactionsList.add(new Transaction("Walmart", LocalDate.of(2026, 01, 23), new BigDecimal("450.00"),"Groceries"));
        transactionsList.add(new Transaction("Texas Roadhouse", LocalDate.of(2026, 01, 23), new BigDecimal("300.00"), "Dining"));
        transactionsList.add(new Transaction("NewRez", LocalDate.of(2026, 01, 05), new BigDecimal("1200.00"), "Mortgage"));

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
