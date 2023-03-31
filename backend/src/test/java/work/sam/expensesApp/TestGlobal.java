package work.sam.expensesApp;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.*;
import work.sam.expensesApp.repository.ExpenseRepository;
import work.sam.expensesApp.repository.UserRepository;
import work.sam.expensesApp.service.account.AccountServiceImpl;
import work.sam.expensesApp.service.category.CategoryServiceImpl;
import work.sam.expensesApp.service.description.DescriptionServiceImpl;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;
import work.sam.expensesApp.service.user.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestGlobal {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DescriptionServiceImpl descriptionServiceImpl;
    @Autowired
    private AccountServiceImpl accountServiceImpl;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ExpenseRepository expenseRepository;


    @Test
    public void testRelationshipEntities() {
        User user = new User("test", "Nom", 25, "City", BigDecimal.valueOf(35000));
        User savedUser = userServiceImpl.createUser(user);

        Account account = new Account("TestAccount", "password123", user, new ArrayList<>(), new ArrayList<>());
        Account savedAccount = accountServiceImpl.createAccount(account);

        Category category = new Category("test category2322");
        account.addCategory(category);
       Category savedCategory = categoryServiceImpl.createCategory(category);

        Expense expense = new Expense(BigDecimal.valueOf(1500), "Expense test2");
        expense.setCategory(category);
        account.addExpense(expense);
        expense.setUser(user);
       Expense savedExpense = expenseServiceImpl.createExpense(expense);



        assertNotNull(savedUser);
        assertNotNull(savedAccount);
        assertNotNull(savedCategory);
        assertNotNull(savedExpense);

        assertEquals(1, account.getCategories().size());
        assertEquals(1, account.getExpenses().size());
        assertNotNull(category);
        assertEquals(account.getId(), category.getAccount().getId());
        assertNotNull(expense);
        assertEquals(account.getId(), expense.getAccount().getId());
        assertEquals(category.getId(), expense.getCategory().getId());

    }
    @Test
    public void createUser2() {
        User user3 = new User("Compte full", "testCompte", 25, "Pandora", BigDecimal.valueOf(715000));
        userServiceImpl.createUser(user3);
        userServiceImpl.getUserById(user3.getId());
        assertNotNull(user3);

        Account account1 = new Account("Savings Account", "password123", user3, new ArrayList<>(), new ArrayList<>());
        accountServiceImpl.createAccount(account1);

        Category category1 = new Category();
        category1.setName("Shopping");
        category1.setAccount(account1);
        categoryServiceImpl.createCategory(category1);

        Description description1 = new Description("Achat shiva");
        Description description2 = new Description("Achat poutre");
        List<Description> descriptions1 = Collections.singletonList(description1);
        List<Description> descriptions2 = Collections.singletonList(description2);

       /* Expense expense1 = new Expense(BigDecimal.valueOf(20000), descriptions1, user3);
        expense1.setCategory(category1);
        expense1.setAccount(account1);
        Expense expense2 = new Expense(BigDecimal.valueOf(30000), descriptions2, user3);
        expense2.setCategory(category1);
        expense2.setAccount(account1);

        expenseServiceImpl.createExpense(expense1);
        expenseServiceImpl.createExpense(expense2);*/

        List<Expense> expenses = expenseServiceImpl.getExpensesByUserId(user3.getId());
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
    void testCreateExpense() {
        Expense testExpense = new Expense(BigDecimal.valueOf(1500),"Achat pont", LocalDateTime.now());
        expenseServiceImpl.createExpense(testExpense);
        Expense result = expenseServiceImpl.createExpense(testExpense);

        assertNotNull(testExpense);
        assertNotNull(testExpense.getId());
        assertEquals(testExpense.getAmount(), result.getAmount());
        assertEquals(testExpense.getName(), result.getName());

        Optional<Expense> savedExpense = expenseRepository.findById(result.getId());
        assertTrue(savedExpense.isPresent());
        assertEquals(testExpense.getAmount(), savedExpense.get().getAmount());
        assertEquals(testExpense.getName(), savedExpense.get().getName());
    }

}

