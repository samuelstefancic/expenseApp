package work.sam.expensesApp.service.account;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.exception.AccountException;
import work.sam.expensesApp.exception.ExpenseException;
import work.sam.expensesApp.exception.ResourceNotFoundException;
import work.sam.expensesApp.repository.AccountRepository;
import work.sam.expensesApp.repository.CategoryRepository;
import work.sam.expensesApp.repository.ExpenseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              CategoryRepository categoryRepository,
                              ExpenseRepository expenseRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    //Create the account
    public Account createAccount(Account account) {
        try {
            Account createdAccount = accountRepository.save(account);
            if (createdAccount == null || createdAccount.getId() == null) {
                throw new AccountException("Failed to create account : account ID is null", HttpStatus.NOT_FOUND);
            }
            return createdAccount;
        } catch (DataAccessException | ConstraintViolationException e) {
            throw new AccountException("Failed to create account : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Find the account

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new AccountException("Account with id : " + id + " not found"
                , HttpStatus.NOT_FOUND));
    }

    //Find Expenses with their names
    public Optional<List<Expense>> findExpensesByNameAndByAccountId(Long accountId, String name) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account with id : " + accountId + " not found", HttpStatus.NOT_FOUND));
        List<Expense> expenses = expenseRepository.findByAccountIdAndNameContainingIgnoreCase(accountId, name);
        if (expenses.isEmpty()) {
            return Optional.empty();
        }
            return Optional.of(expenses);
    }

    //Get every categories for the account

    public List<Category> getAllCategoriesForAccount(Long id) {
        Account account = getAccountById(id);
        return categoryRepository.findByAccount(account);
    }



    //Update account

    //Update account name
    public Account updateAccountName(Long id, Account updateAccount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account with id : " + id + " not found", HttpStatus.NOT_FOUND));

        String newNameAccount = updateAccount.getAccountName();
        Account checkExistingAccount = accountRepository.findByAccountName(newNameAccount);
        if (checkExistingAccount != null && !checkExistingAccount.getId().equals(id)) {
            throw new AccountException("This account name : " + newNameAccount + " already exists", HttpStatus.CONFLICT);
        }
        account.setAccountName(updateAccount.getAccountName());
        return accountRepository.save(account);
    }

    //Update full account
    public Account updateAccount(Long id, Account updateAccount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account with id : " + id + " not found", HttpStatus.NOT_FOUND));

        String newNameAccount = updateAccount.getAccountName();
        Account checkExistingAccount = accountRepository.findByAccountName(newNameAccount);
        if (checkExistingAccount != null && !checkExistingAccount.getId().equals(id)) {
            throw new AccountException("This account name : " + newNameAccount + " already exists", HttpStatus.CONFLICT);
        }
        account.setAccountName(updateAccount.getAccountName());
        account.setPassword(updateAccount.getPassword());
        return accountRepository.save(account);
    }

    //Delete account

    public void deleteAccountById(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountException("Account with ID : " + id + " not found", HttpStatus.NOT_FOUND);
        }
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            throw new AccountException("Failed to delete account with ID : " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteExpense(Long accountId, Long expenseId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account id :" + accountId + " not found", HttpStatus.NOT_FOUND));
      Expense expense = expenseRepository.findById(expenseId)
              .orElseThrow(() ->  new ExpenseException("Expense id : " + expenseId + " not found", HttpStatus.NOT_FOUND));
      if (!expense.getAccount().getId().equals(account.getId())) {
          throw new ResourceNotFoundException("Expense with id " + expenseId + " is not present on the account with id " + accountId, HttpStatus.NOT_FOUND);
      }
      expenseRepository.delete(expense);
    }


    public Expense updateExpense(Long accountId, Long expenseId, @Valid Expense updatedExpense) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account id :" + accountId + " not found", HttpStatus.NOT_FOUND));
        Expense expenseExist = expenseRepository.findByIdAndAccount(expenseId, accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with this id : " + expenseId + " and the account id : " + accountId, HttpStatus.NOT_FOUND));

        expenseExist.setAmount(updatedExpense.getAmount());
        expenseExist.setDate(LocalDateTime.now());
        expenseExist.setCategory(updatedExpense.getCategory());
        expenseExist.setDescription(updatedExpense.getDescription());
        return expenseRepository.save(expenseExist);
    }
}
