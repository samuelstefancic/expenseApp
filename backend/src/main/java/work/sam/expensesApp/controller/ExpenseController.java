package work.sam.expensesApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.service.ExpenseService;
import work.sam.expensesApp.service.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserServiceImpl userServiceImpl;

    public ExpenseController(ExpenseService expenseService, UserServiceImpl userServiceImpl) {
        this.expenseService = expenseService;
        this.userServiceImpl = userServiceImpl;
    }

    //Controller retournant les dépenses par userId
    @GetMapping("/users/{userId}/expenses")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        userServiceImpl.getUserById(userId);
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }

    //Controller pour retourner toutes les dépenses
    //Format BigDécimal au lieu de List
    @GetMapping("/users/{userId}/expenses/total")
    public ResponseEntity<BigDecimal> getSumExpensesByUserId(@PathVariable Long userId) {
        userServiceImpl.getUserById(userId);
        BigDecimal totalExpenses = expenseService.getTotalExpensesByUserId(userId);
        return ResponseEntity.ok(totalExpenses);
    }

    //Delete expenses

    @DeleteMapping("/expenses/delete/{expenseId}")
        public ResponseEntity<Void> deleteExpense (@PathVariable Long expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/expenses/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long expenseId, @RequestBody Expense updatedExpense) {
        Expense expense = expenseService.updateExpense(expenseId, updatedExpense);
        return ResponseEntity.ok(expense);
    }
}
