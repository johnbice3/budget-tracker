package budgettracker;

import java.math.BigDecimal;

public class CategorySummary {
    private String category;
    private BigDecimal amount;
    private double percentage;
    private long transactionCount;

    public CategorySummary(String category, BigDecimal amount, double percentage, long transactionCount){
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
    public long getTransactionCount(){
        return this.transactionCount;
    }
}
