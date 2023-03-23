package work.sam.expensesApp.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.exception.ExpenseException;
import work.sam.expensesApp.exception.UserException;
import work.sam.expensesApp.repository.ExpenseRepository;
import work.sam.expensesApp.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }


    //Create

    public Expense createExpense(Expense expense) {
        try {
            return expenseRepository.save(expense);
        } catch (DataAccessException | ConstraintViolationException e) {
            throw new ExpenseException("Failed to create this expense" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Find expense

    public Expense findExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense with ID : " + id + " not found", HttpStatus.NOT_FOUND));
    }

    //Update Expense

    public Expense updateExpense(Long id, @Valid Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseException("Expense not found with id: " + id, HttpStatus.NOT_FOUND));
        existingExpense.setDescriptionWithValidation(updatedExpense.getDescription());
        existingExpense.setDescription(updatedExpense.getDescription());
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

}
