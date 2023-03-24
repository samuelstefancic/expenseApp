package work.sam.expensesApp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.service.ExpenseService;
import work.sam.expensesApp.service.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class UserControllerTest {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ExpenseService expenseService;
    @Test
    public void createUser() {
        User user2 = new User("Florine", "Martin", 25, "Pandora", BigDecimal.valueOf(75000));
        userServiceImpl.createUser(user2);
        userServiceImpl.getUserById(user2.getId());
        assertNotNull(user2);
        Description description1 = new Description("Achat shiva");
        Description description2 = new Description("Achat poutre");
        List<Description> descriptions1 = Collections.singletonList(description1);
        List<Description> descriptions2 = Collections.singletonList(description2);
        Expense expense1 = new Expense(BigDecimal.valueOf(20000),descriptions1,  user2);
        Expense expense2 = new Expense(BigDecimal.valueOf(30000), descriptions2, user2);
        expenseService.createExpense(expense1);
        expenseService.createExpense(expense2);

        List<Expense> expenses = expenseService.getExpensesByUserId(user2.getId());
        assertEquals(2, expenses.size());

        for (Expense expense : expenses) {
            assertEquals(user2.getId(), expense.getUser().getId());
        }
    }
}
