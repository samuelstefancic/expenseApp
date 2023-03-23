package work.sam.expensesApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.service.ExpenseService;
import work.sam.expensesApp.service.UserService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class TestGlobal {
    @Autowired
    private UserService userService;
    @Autowired
    private ExpenseService expenseService;
    @Test
    public void createUserTest() {
        User user5 = new User("Samuel", "Martin", 25, "Pandora", BigDecimal.valueOf(75000));
        userService.createUser(user5);
        userService.getUserById(user5.getId());
        assertNotNull(user5);
        Description description1 = new Description("Achat shiva");
        Description description2 = new Description("Achat poutre");
        List<Description> descriptions1 = Collections.singletonList(description1);
        List<Description> descriptions2 = Collections.singletonList(description2);
        Expense expense1 = new Expense(BigDecimal.valueOf(20000),descriptions1,  user5);
        Expense expense2 = new Expense(BigDecimal.valueOf(30000), descriptions2, user5);
        expenseService.createExpense(expense1);
        expenseService.createExpense(expense2);

        List<Expense> expenses = expenseService.getExpensesByUserId(user5.getId());
        assertEquals(2, expenses.size());

        for (Expense expense : expenses) {
            assertEquals(user5.getId(), expense.getUser().getId());
        }
    }
}

