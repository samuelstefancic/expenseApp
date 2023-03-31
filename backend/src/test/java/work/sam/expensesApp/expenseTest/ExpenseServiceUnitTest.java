package work.sam.expensesApp.expenseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.*;
import work.sam.expensesApp.repository.AccountRepository;
import work.sam.expensesApp.repository.CategoryRepository;
import work.sam.expensesApp.repository.ExpenseRepository;
import work.sam.expensesApp.repository.UserRepository;
import work.sam.expensesApp.service.category.CategoryServiceImpl;
import work.sam.expensesApp.service.description.DescriptionServiceImpl;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;
import work.sam.expensesApp.service.user.UserServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExpenseServiceUnitTest {
    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryServiceImpl categoryService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private DescriptionServiceImpl descriptionService;

    private User testUser;
    private Expense testExpense;
    private Description testDescription;
    private Category testCategory;
    private Account testAccount;


@Test
    void setUp2() {
        testUser = new User("Jean", "Dupont", 54, "Marseille", BigDecimal.valueOf(7500));
        testExpense = new Expense(BigDecimal.valueOf(1500),"Achat pont", LocalDateTime.now());
        Description description1 = new Description("Ce pont était nécessaire");
        testCategory = new Category("Achats nécessaires");
    List<Description> descriptions = Collections.singletonList(description1);

        //Create and persist entities
        expenseService.createExpense(testExpense);
        userService.createUser(testUser);
        testExpense.setUser(testUser);
        descriptionService.createDescription(description1);
        categoryService.createCategory(testCategory);
    }
    @Test
    void testCreateExpense() {
        Expense testExpense = new Expense(BigDecimal.valueOf(1500),"Achat pont", LocalDateTime.now());
        expenseService.createExpense(testExpense);
        Expense result = expenseService.createExpense(testExpense);

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
