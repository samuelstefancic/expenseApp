package work.sam.expensesApp.service;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.exception.AccountException;
import work.sam.expensesApp.repository.AccountRepository;
import work.sam.expensesApp.repository.CategoryRepository;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          CategoryRepository categoryRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
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

    //Get every categories for the account

    public List<Category> getAllCategoriesForAccount(Long id) {
        Account account = getAccountById(id);
        return categoryRepository.findByAccount(account);
    }


    //Update account (soon)

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
}
