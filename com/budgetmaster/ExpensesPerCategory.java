package BudgetMasterCorrection.com.budgetmaster;

import java.util.List;

public class ExpensesPerCategory {
    private Category category ;
    private List<Expense> expenses;

    public ExpensesPerCategory(Category category, List<Expense> expenses) {
        this.category = category;
        this.expenses = expenses;
    }

    public Category getCategory() {
        return category;
    }

    public List<Expense> getExpenses(){
        return this.expenses;
    }

    public double getAverage() {
        if (this.expenses.isEmpty()) return 0;

        return getSum() / this.expenses.size();
    }

    public double getSum() {
        if (this.expenses.isEmpty()) return 0;

        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public String toString() {
        return "{" +
                "category=" + category +
                ", average=" + getAverage() +
                '}';
    }
}
