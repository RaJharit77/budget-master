package BudgetMasterCorrection.com.budgetmaster;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String name;
    private double monthlyBudget;
    private List<Expense> expenses;

    public User(String name, double monthlyBudget) {
        this.name = name;
        this.monthlyBudget = monthlyBudget;
        this.expenses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(String description, double amount, Category category, LocalDate date) {
        if (amount < 0) throw new IllegalArgumentException("Expense amount cannot be negative");
        expenses.add(new Expense(description, amount, category, date));
    }

    public void addExpense(Expense newExpense){
        if (newExpense.getAmount() < 0) {
            throw new IllegalArgumentException("Expense amount cannot be negative");
        }

        expenses.add(newExpense);
    }

    public List<Expense> getExpenseByCategory(Category category) {
        return this.expenses.stream()
                .filter(expense -> expense.getCategory().equals(category))
                .toList();
    }

    public double getTotalSpentThisMonth() {
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();

        return expenses.stream()
                .filter(expense -> expense.getDate().getMonth().equals(currentMonth))
                .filter(expense -> expense.getDate().getYear() == currentYear)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getRemainingBudget() {
        return monthlyBudget - getTotalSpentThisMonth();
    }

    public List<Category> getTopCategories() {
        Map<Category, Double> categoryTotals = new HashMap<>();

        for (Expense expense : expenses) {
            categoryTotals.merge(expense.getCategory(), expense.getAmount(), Double::sum);
        }

        return categoryTotals.entrySet().stream()
                .sorted(Map.Entry.<Category, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    /*
     * Ceci aurait tellement été plus facile si pour chaque catégorie on avait stocké la liste des
     * dépenses aussi.
     */
    public List<Category> getTopCategories1() {
        List<ExpensesPerCategory> expensesPerCategory = new ArrayList<>();

        // Devrait être une fonction séparée réutilisable si l'on voulait vraiment optimale
        for (Category category : Category.values()) {
            expensesPerCategory.add(new ExpensesPerCategory(
                    category,
                    expenses.stream()
                            .filter(expense -> expense.getCategory().equals(category))
                            .toList()
            ));
        }

        return expensesPerCategory.stream()
                .sorted((a,b) -> (int) (b.getSum() - a.getSum()))
                .limit(3)
                .map(ExpensesPerCategory::getCategory)
                .toList();
    }

    public Map<Category, Double> calculateAverageSpendingPerCategory() {
        Map<Category, Double> categoryTotals = new HashMap<>();
        Map<Category, Integer> categoryCounts = new HashMap<>();

        for (Expense expense : expenses) {
            categoryTotals.merge(expense.getCategory(), expense.getAmount(), Double::sum);
            categoryCounts.merge(expense.getCategory(), 1, Integer::sum);
        }

        Map<Category, Double> averages = new HashMap<>();
        for (Category category : categoryTotals.keySet()) {
            averages.put(category, categoryTotals.get(category) / categoryCounts.get(category));
        }

        return averages;
    }

    public List<ExpensesPerCategory> calculateAverageSpendingPerCategory1(){
        List<ExpensesPerCategory> expensePerCategories = new ArrayList<>();

        for (Category category : Category.values()) {
            expensePerCategories.add(new ExpensesPerCategory(
                    category,
                    expenses.stream()
                            .filter(expense -> expense.getCategory().equals(category))
                            .toList()
            ));
        }

        return expensePerCategories;
    }
}
