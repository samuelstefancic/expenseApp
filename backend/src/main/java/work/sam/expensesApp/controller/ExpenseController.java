package work.sam.expensesApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.service.ExpenseService;
import work.sam.expensesApp.service.UserService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    //Controller retournant les dépenses par userId
    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        userService.getUserById(userId);
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }

    //Controller pour retourner toutes les dépenses
    //Format BigDécimal au lieu de List
    @GetMapping("/total/{userId}")
    public ResponseEntity<BigDecimal> getSumExpensesByUserId(@PathVariable Long userId) {
        userService.getUserById(userId);
        BigDecimal totalExpenses = expenseService.getTotalExpensesByUserId(userId);
        return ResponseEntity.ok(totalExpenses);
    }

    //Delete expenses

    @DeleteMapping("/delete/{expenseId}")
        public ResponseEntity<Void> deleteExpense (@PathVariable Long expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return ResponseEntity.noContent().build();
    }
}
