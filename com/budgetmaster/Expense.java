package BudgetMasterCorrection.com.budgetmaster;

import java.time.LocalDate;

public class Expense {
    private String description;
    private double amount;
    private Category category;
    private LocalDate date;

    public Expense(String description, double amount, Category category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}