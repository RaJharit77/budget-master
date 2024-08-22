package test;

import com.budgetmaster.Category;
import com.budgetmaster.Expense;
import com.budgetmaster.User;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        User john = new User("John", 250_000);

        Expense salto = new Expense("Salto chips for lunch", 1000, Category.FOOD_AND_DINING, LocalDate.now());
        Expense pringles = new Expense("Pringles, because why not ? ", 15_000, Category.FOOD_AND_DINING, LocalDate.now());
        Expense bus = new Expense("Bus", 5000, Category.TRANSPORTATION, LocalDate.now());
        Expense taxes = new Expense("Taxes", 5000, Category.PUBLIC_SERVICES, LocalDate.now());
        Expense games = new Expense("Tekken 8", 8000, Category.ENTERTAINMENT, LocalDate.now());

        john.addExpense(salto);
        john.addExpense(pringles);
        john.addExpense(bus);
        john.addExpense(taxes);
        john.addExpense(games);

        System.out.println("Total spent this month : " + john.getTotalSpentThisMonth());

        System.out.println("Expenses on food and dining : " + john.getExpenseByCategory(
                Category.FOOD_AND_DINING
        ));

        System.out.println("Remaining budget : " + john.getRemainingBudget());

        System.out.println("Top categories : " + john.getTopCategories());
        System.out.println("Top categories (1) : " + john.getTopCategories1());

        System.out.println("Average per category : " + john.calculateAverageSpendingPerCategory());
        System.out.println("Average per category (1): " + john.calculateAverageSpendingPerCategory1());
    }
}
