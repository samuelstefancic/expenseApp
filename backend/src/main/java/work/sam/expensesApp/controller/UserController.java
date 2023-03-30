package work.sam.expensesApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.sam.expensesApp.DTO.ExpenseDTO;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;
import work.sam.expensesApp.service.user.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ExpenseServiceImpl expenseServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ExpenseServiceImpl expenseServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.expenseServiceImpl = expenseServiceImpl;
    }

    // Get

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userServiceImpl.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/expenses")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        List<Expense> expenses = expenseServiceImpl.getExpensesByUserId(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{userId}/expenses/total")
    public ResponseEntity<BigDecimal> getTotalExpensesByUserId(@PathVariable Long userId) {
        BigDecimal expenses = expenseServiceImpl.getTotalExpensesByUserId(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }




    //Post

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userServiceImpl.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/expenses")
    public ResponseEntity<ExpenseDTO> addExpense(@PathVariable long userId, @RequestBody @Valid ExpenseDTO expenseDTO) {
        ExpenseDTO addExpense = userServiceImpl.addExpense(userId, expenseDTO);
        return new ResponseEntity<>(addExpense, HttpStatus.OK);
    }


    //Put

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody @Valid User updatedUser) {
        User user = userServiceImpl.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    //Delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userServiceImpl.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }


}
