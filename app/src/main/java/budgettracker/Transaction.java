package budgettracker;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String store;
    private LocalDate date;
    private BigDecimal amount; 
    private String category;
    private String notes;

    public Transaction(String store, LocalDate date, BigDecimal amount, String category){
        this(store, date, amount, category, "");
    }
    public Transaction(String store, LocalDate date, BigDecimal amount, String category, String notes){
        this.store = store;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.notes = notes;
    }
    public String getStore(){
        return this.store;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public String getCategory(){
        return this.category;
    }
    public String getNotes(){
        return this.notes;
    }
}
