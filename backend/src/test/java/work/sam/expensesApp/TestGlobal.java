package work.sam.expensesApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.*;
import work.sam.expensesApp.repository.AccountRepository;
import work.sam.expensesApp.repository.DescriptionRepository;
import work.sam.expensesApp.repository.UserRepository;
import work.sam.expensesApp.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class TestGlobal {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DescriptionService descriptionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void createUser2() {
        User user3 = new User("Compte full", "testCompte", 25, "Pandora", BigDecimal.valueOf(715000));
        userServiceImpl.createUser(user3);
        userServiceImpl.getUserById(user3.getId());
        assertNotNull(user3);

        Account account1 = new Account("Savings Account", "password123", user3, new ArrayList<>(), new ArrayList<>());
        accountService.createAccount(account1);

        Category category1 = new Category();
        category1.setName("Shopping");
        category1.setAccount(account1);
        categoryService.createCategory(category1);

        Description description1 = new Description("Achat shiva");
        Description description2 = new Description("Achat poutre");
        List<Description> descriptions1 = Collections.singletonList(description1);
        List<Description> descriptions2 = Collections.singletonList(description2);

        Expense expense1 = new Expense(BigDecimal.valueOf(20000), descriptions1, user3);
        expense1.setCategory(category1);
        expense1.setAccount(account1);
        Expense expense2 = new Expense(BigDecimal.valueOf(30000), descriptions2, user3);
        expense2.setCategory(category1);
        expense2.setAccount(account1);

        expenseService.createExpense(expense1);
        expenseService.createExpense(expense2);

        List<Expense> expenses = expenseService.getExpensesByUserId(user3.getId());
        assertEquals(2, expenses.size());

        for (Expense expense : expenses) {
            assertEquals(user3.getId(), expense.getUser().getId());
            assertNotNull(expense.getAccount());
            assertNotNull(expense.getCategory());
        }
    }


    @Test
    public void createDescriptionTest() {
        Description description1 = new Description("Achat shiva");
        Description description2 = new Description("Achat poutre");

        List<Description> descriptions1 = Collections.singletonList(description1);
        List<Description> descriptions2 = Collections.singletonList(description2);
    }
    @Test
    public void createExpenseTest() {
        Expense expense1 = new Expense(BigDecimal.valueOf(20000));
        Expense expense2 = new Expense(BigDecimal.valueOf(30000));
        expenseService.createExpense(expense1);
        expenseService.createExpense(expense2);
    }



    /*    List<Expense> expenses = expenseService.getExpensesByUserId(user5.getId());
        assertEquals(2, expenses.size());

        for (Expense expense : expenses) {
            assertEquals(user5.getId(), expense.getUser().getId());
        }
    }*/
}

