package budgettracker;

import java.math.BigDecimal;

public class CategorySummary {
    private String category;
    private BigDecimal amount;
    private double percentage;
    private int transactionCount;

    public CategorySummary(String category, BigDecimal amount, double percentage, int transactionCount){
        this.category = category;
        this.amount = amount;
        this.percentage = percentage;
        this.transactionCount = transactionCount;
    }
    public String getCategory(){
        return this.category;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public double getPercentage(){
        return this.percentage;
    }
    public int getTransactionCount(){
        return this.transactionCount;
    }
}
