package work.sam.expensesApp;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;

import work.sam.expensesApp.service.expense.ExpenseServiceImpl;
import work.sam.expensesApp.service.user.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ExpensesAppApplicationTests {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private ExpenseServiceImpl expenseServiceImpl;


	public void ExpensesAppApplicationTests(UserServiceImpl userServiceImpl, ExpenseServiceImpl expenseServiceImpl) {
		this.userServiceImpl = userServiceImpl;
		this.expenseServiceImpl = expenseServiceImpl;
	}
/*
	@Test
	void contextLoads() {
	}*/

	@Test
	public void createUser2() {
		User user3 = new User("DZADSADA", "test", 25, "Pandora", BigDecimal.valueOf(75000));
		userServiceImpl.createUser(user3);
		userServiceImpl.getUserById(user3.getId());
		assertNotNull(user3);


		Description description1 = new Description("Achat shiva");
		Description description2 = new Description("Achat poutre");
		List<Description> descriptions1 = Collections.singletonList(description1);
		List<Description> descriptions2 = Collections.singletonList(description2);
/*
		Expense expense1 = new Expense(BigDecimal.valueOf(20000),descriptions1,  user3);
		Expense expense2 = new Expense(BigDecimal.valueOf(30000), descriptions2, user3);
		expenseServiceImpl.createExpense(expense1);
		expenseServiceImpl.createExpense(expense2);*/

		List<Expense> expenses = expenseServiceImpl.getExpensesByUserId(user3.getId());
		assertEquals(2, expenses.size());

		for (Expense expense : expenses) {
			assertEquals(user3.getId(), expense.getUser().getId());
		}
	}

	/*@Test
	public void testAddUserWithExpenses() {
		when(userRepository.save(any(User.class))).thenReturn(user);

		User savedUser = userService.createUser(user);

		verify(userRepository, times(1)).save(user);

		Assertions.assertEquals(savedUser, user);
	}*/

	@Test
	public void createUser() {
		User user2 = new User("Florine", "Martin", 25, "Pandora", BigDecimal.valueOf(75000));
		userServiceImpl.createUser(user2);
		userServiceImpl.getUserById(user2.getId());
		assertNotNull(user2);
	}



	/*@Test
	public void createUserWithExpenses() {
		List<Expense> expenses = new ArrayList<>();
		expenses.add(new Expense(BigDecimal.valueOf(100), new ArrayList<>(), null));
		expenses.add(new Expense(BigDecimal.valueOf(200), new ArrayList<>(), null));

		User user2 = new User("Florine", "Martin", 25, "Pandora", BigDecimal.valueOf(75000), expenses);
		user2.getExpenses().forEach(expense -> expense.setUser(user2));

		User createdUser = userService.createUser(user2);
		assertNotNull(createdUser);
		assertNotNull(createdUser.getId());
		assertEquals("Florine", createdUser.getFirstName());
		assertEquals("Martin", createdUser.getLastName());

		// Modify the user2 object if needed
		user2.setAge(26);
		user2.setIncome(BigDecimal.valueOf(80000));
		// ...
	}
*/

}
