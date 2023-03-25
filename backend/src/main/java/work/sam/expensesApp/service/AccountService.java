package work.sam.expensesApp.service;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.exception.AccountException;
import work.sam.expensesApp.repository.AccountRespository;

@Service
public class AccountService {

    private final AccountRespository accountRespository;

    @Autowired
    public AccountService(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    //Create the account
    public Account createAccount(Account account) {
        try {
            Account createdAccount = accountRespository.save(account);
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
        return accountRespository.findById(id).orElseThrow(() ->
                new AccountException("Account with id : " + id + " not found"
                , HttpStatus.NOT_FOUND));
    }


    //Update account (soon)

    //Update account name
    public Account updateAccountName(Long id, Account updateAccount) {
        Account account = accountRespository.findById(id)
                .orElseThrow(() -> new AccountException("Account with id : " + id + " not found", HttpStatus.NOT_FOUND));

        String newNameAccount = updateAccount.getAccountName();
        Account checkExistingAccount = accountRespository.findByAccountName(newNameAccount);
        if (checkExistingAccount != null && !checkExistingAccount.getId().equals(id)) {
            throw new AccountException("This account name : " + newNameAccount + " already exists", HttpStatus.CONFLICT);
        }
        account.setAccountName(updateAccount.getAccountName());
        return accountRespository.save(account);
    }

    //Update full account
    public Account updateAccount(Long id, Account updateAccount) {
        Account account = accountRespository.findById(id)
                .orElseThrow(() -> new AccountException("Account with id : " + id + " not found", HttpStatus.NOT_FOUND));

        String newNameAccount = updateAccount.getAccountName();
        Account checkExistingAccount = accountRespository.findByAccountName(newNameAccount);
        if (checkExistingAccount != null && !checkExistingAccount.getId().equals(id)) {
            throw new AccountException("This account name : " + newNameAccount + " already exists", HttpStatus.CONFLICT);
        }
        account.setAccountName(updateAccount.getAccountName());
        account.setPassword(updateAccount.getPassword());
        return accountRespository.save(account);
    }

    //Delete account

    public void deleteAccountById(Long id) {
        if (!accountRespository.existsById(id)) {
            throw new AccountException("Account with ID : " + id + " not found", HttpStatus.NOT_FOUND);
        }
        try {
            accountRespository.deleteById(id);
        } catch (Exception e) {
            throw new AccountException("Failed to delete account with ID : " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
