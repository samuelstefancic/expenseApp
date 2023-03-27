package work.sam.expensesApp.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.*;
import work.sam.expensesApp.exception.AccountException;
import work.sam.expensesApp.exception.CategoryException;
import work.sam.expensesApp.exception.ExpenseException;
import work.sam.expensesApp.exception.UserException;
import work.sam.expensesApp.repository.AccountRespository;
import work.sam.expensesApp.repository.CategoryRepository;
import work.sam.expensesApp.repository.ExpenseRepository;
import work.sam.expensesApp.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRespository accountRespository;


    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository,
                          CategoryRepository categoryRepository, AccountRespository accountRespository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;

        this.categoryRepository = categoryRepository;
        this.accountRespository = accountRespository;
    }


    //Create

    public Expense createExpense(Expense expense) {
        try {
            expense.getDescription().forEach(description -> description.setExpense(expense));
            return expenseRepository.save(expense);
        } catch (DataAccessException | ConstraintViolationException e) {
            throw new ExpenseException("Failed to create this expense" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Expense createExpenseForUser(Long userId, Expense expense) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with id : " + userId, HttpStatus.NOT_FOUND));
        expense.setUser(user);
        return createExpense(expense);
    }

    //Find expense

    public Expense findExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense with ID : " + id + " not found", HttpStatus.NOT_FOUND));
    }

    //Find expense by category
    public List<Expense> findExpensesAccountByCategory(Long accountId, Long categoryId) {
        Account account = accountRespository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account with id : " + accountId + " not found", HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category with id ; " + categoryId + " not found", HttpStatus.NOT_FOUND));
        return expenseRepository.findByAccountAndCategory(account, category);
    }

    public List<Expense> findExpensesByDate(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        Account account = accountRespository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account with id : " + accountId + " not found", HttpStatus.NOT_FOUND ));
        return expenseRepository.findByAccountAndDateBetween(account, startDate, endDate);
    }
    //Sum of expenses by a category

    public BigDecimal getTotalExpensesByAccountId(Long accountID) {
        Account account = accountRespository.findById(accountID)
                .orElseThrow(() ->
                        new AccountException("Account not found with id " + accountID, HttpStatus.NOT_FOUND));
        List<Expense> expenses = expenseRepository.findByAccountId(accountID);
        if (expenses == null || expenses.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //Update Expense

    public Expense updateExpense(Long id, @Valid Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense not found with id: " + id, HttpStatus.NOT_FOUND));
        setDescriptionWithValidation(existingExpense, updatedExpense.getDescription());
        return expenseRepository.save(existingExpense);
    }

    public void deleteExpenseById(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ExpenseException("Expense with ID : " + id + " not found", HttpStatus.NOT_FOUND);
        }
        try {
            expenseRepository.deleteById(id);
        } catch (Exception e) {
            throw new ExpenseException("failed to delete expense with id : " + id, HttpStatus.BAD_REQUEST);
        }

    }

    //return a list of expenses
    public List<Expense> getExpensesByUserId(Long userId) {
        User tempUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found with id : " + userId, HttpStatus.NOT_FOUND));
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses != null && !expenses.isEmpty() ? expenses : new ArrayList<>();
    }

    //Return total amount of expenses for the user
    public BigDecimal getTotalExpensesByUserId(Long userId) {
        User tempUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found with id : " + userId, HttpStatus.NOT_FOUND));
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        if (expenses == null || expenses.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setDescriptionWithValidation(Expense expense, List<Description> descriptions) {
        if (descriptions == null || descriptions.isEmpty()) {
            throw new ExpenseException("The description must be filled", HttpStatus.BAD_REQUEST);
        }
        descriptions.forEach(x -> x.setUpdatedAt(LocalDateTime.now()));
        expense.setDescription(descriptions);
    }

    public void updateAmount(Expense expense, BigDecimal newAmount) {
        expense.setAmount(newAmount);
        if (!expense.getDescription().isEmpty()) {
            Description latestDescription = expense.getDescription().get(expense.getDescription().size() - 1);
            latestDescription.setUpdatedAt(LocalDateTime.now());
        }
    }


}
