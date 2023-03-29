package work.sam.expensesApp.service.expense;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ExpenseService {

    Expense createExpense(Expense expense);

    Expense createExpenseForUser(Long userId, Expense expense);

    Expense findExpenseById(Long id);

    List<Expense> findExpensesAccountByCategory(Long accountId, Long categoryId);

    List<Expense> findExpensesByDate(Long accountId, LocalDateTime startDate, LocalDateTime endDate);

    BigDecimal getTotalExpensesByAccountId(Long accountId);

    Expense updateExpense(Long id, @Valid Expense updatedExpense);

    void deleteExpenseById(Long id);

    List<Expense> getExpensesByUserId(Long userId);

    BigDecimal getTotalExpensesByUserId(Long userId);

    void setDescriptionWithValidation(Expense expense, List<Description> descriptions);

    void updateAmount(Expense expense, BigDecimal newAmount);



}
