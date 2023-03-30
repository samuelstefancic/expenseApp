package work.sam.expensesApp.service.account;

import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.entity.Expense;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(Long id);
    List<Category> getAllCategoriesForAccount(Long id);
    Account updateAccountName(Long id, Account updateAccount);
    Account updateAccount(Long id, Account updateAccount);
    Optional<List<Expense>> findExpensesByNameAndByAccountId(Long accountId, String name);
    void deleteAccountById(Long id);
    void deleteExpense(Long accountId, Long expenseId);
    Expense updateExpense(Long accountId, Long expenseId, Expense updatedExpense);


}
