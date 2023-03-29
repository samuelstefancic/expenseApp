package work.sam.expensesApp.service.account;

import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;

import java.util.List;

@Service
public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(Long id);
    List<Category> getAllCategoriesForAccount(Long id);
    Account updateAccountName(Long id, Account updateAccount);
    Account updateAccount(Long id, Account updateAccount);
    void deleteAccountById(Long id);
}
