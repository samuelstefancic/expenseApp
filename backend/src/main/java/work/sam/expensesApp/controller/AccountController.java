package work.sam.expensesApp.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.exception.ResourceNotFoundException;
import work.sam.expensesApp.repository.ExpenseRepository;
import work.sam.expensesApp.service.account.AccountServiceImpl;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountServiceImpl accountServiceImpl;
    private final ExpenseServiceImpl expenseServiceImpl;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public AccountController(AccountServiceImpl accountServiceImpl, ExpenseServiceImpl expenseServiceImpl,
                             ExpenseRepository expenseRepository) {
        this.accountServiceImpl = accountServiceImpl;
        this.expenseServiceImpl = expenseServiceImpl;
        this.expenseRepository = expenseRepository;
    }

    //Get

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account accountSearch = accountServiceImpl.getAccountById(accountId);
        return new ResponseEntity<>(accountSearch, HttpStatus.OK);
    }

    @GetMapping("/{accountId}/expenses")
    public ResponseEntity<List<Expense>> getExpensesByAccountId(@PathVariable Long accountId) {
        List<Expense> expenses = expenseServiceImpl.getExpensesByAccountId(accountId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{accountId}/expenses/total")
    public ResponseEntity<BigDecimal> getTotalExpensesByAccountId(@PathVariable Long accountId) {
        BigDecimal totalExp = expenseServiceImpl.getTotalExpensesByAccountId(accountId);
        return new ResponseEntity<>(totalExp, HttpStatus.OK);
    }

    @GetMapping("/{accountId}/expenses/search")
    public ResponseEntity<List<Expense>> findExpenseByName(@PathVariable Long accountId, @RequestParam("name") String name) {
        List<Expense> expenses = accountServiceImpl.findExpensesByNameAndByAccountId(accountId, name).orElseThrow(() ->
                new ResourceNotFoundException("No expenses found with the name: " + name + " for account id : " + accountId, HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(expenses);
    }

    //Post

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountServiceImpl.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    //Put

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount (@PathVariable Long accountId, @RequestBody @Valid Account account) {
        Account accountUpdated = accountServiceImpl.updateAccount(accountId, account);
        return new ResponseEntity<>(accountUpdated, HttpStatus.OK);
    }

    @PutMapping("/{accountId}/expenses/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long accountId, @PathVariable Long expenseId, @RequestBody @Valid Expense updatedExpense) {
        Expense expense = accountServiceImpl.updateExpense(accountId, expenseId, updatedExpense);
        return ResponseEntity.ok(expense);
    }

    //Delete

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountServiceImpl.deleteAccountById(accountId);
        return ResponseEntity.noContent().build();
    }

    //Delete an expense from an account
    @DeleteMapping("/{accountId}/expenses/{expenseId}")
    public ResponseEntity<Void> deleteExpenseFromAccount(@PathVariable Long accountId, @PathVariable Long expenseId) {
        accountServiceImpl.deleteExpense(accountId, expenseId);
        return ResponseEntity.noContent().build();
    }




}
