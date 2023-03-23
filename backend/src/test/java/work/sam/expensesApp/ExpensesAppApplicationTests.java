package work.sam.expensesApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.repository.UserRepository;
import work.sam.expensesApp.service.ExpenseService;
import work.sam.expensesApp.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExpensesAppApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	private ExpenseService expenseService;

	@Mock
	private User user;

	@MockBean
	private UserRepository userRepository;

	public void ExpensesAppApplicationTests(UserService userService, ExpenseService expenseService) {
		this.userService = userService;
		this.expenseService = expenseService;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateUserAddExpenses() {
		//Création de l'utilisateur
		User user = new User("Samuel",
				"Stefancic",
				27,
				"Pandora",
				BigDecimal.valueOf(75000));
		user = userService.createUser(user);

		Expense expense1 = new Expense(BigDecimal.valueOf(20000),"Achat voiture",  user);
		Expense expense2 = new Expense(BigDecimal.valueOf(30000), "achat appartement", user);
		expenseService.createExpense(expense1);
		expenseService.createExpense(expense2);

		List<Expense> expenses = expenseService.getExpensesByUserId(user.getId());
		assertEquals(2, expenses.size());

		for (Expense expense : expenses) {
			assertEquals(user.getId(), expense.getUser().getId());
		}
	}

	@Test
	public void testAddUserWithExpenses() {
		when(userRepository.save(any(User.class))).thenReturn(user);

		User savedUser = userService.createUser(user);

		verify(userRepository, times(1)).save(user);

		Assertions.assertEquals(savedUser, user);
	}

}
