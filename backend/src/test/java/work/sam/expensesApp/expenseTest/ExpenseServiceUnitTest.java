package work.sam.expensesApp.expenseTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.repository.AccountRepository;
import work.sam.expensesApp.repository.CategoryRepository;
import work.sam.expensesApp.repository.ExpenseRepository;
import work.sam.expensesApp.repository.UserRepository;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExpenseServiceUnitTest {
    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private AccountRepository accountRepository;

    private User testUser;
    private Expense testExpense;


}
