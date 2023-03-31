package work.sam.expensesApp.Temp;

public class TempController {
}
/*

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @PostMapping("/users/{userId}/expenses")
    public ResponseEntity<Expense> createExpenseForUser(@PathVariable Long userId, @RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpenseForUser(userId, expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> findExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.findExpenseById(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}/expenses/category/{categoryId}")
    public ResponseEntity<List<Expense>> findExpensesAccountByCategory(@PathVariable Long accountId, @PathVariable Long categoryId) {
        List<Expense> expenses = expenseService.findExpensesAccountByCategory(accountId, categoryId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}/expenses/date")
    public ResponseEntity<List<Expense>> findExpensesByDate(@PathVariable Long accountId,
                                                            @RequestParam("startDate") String startDate,
                                                            @RequestParam("endDate") String endDate) {

    @GetMapping("/accounts/{accountId}/expenses/date")
    public ResponseEntity<List<Expense>> findExpensesByDate(@PathVariable Long accountId,
                                                            @RequestParam("startDate") String startDate,
                                                            @RequestParam("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

        List<Expense> expenses = expenseService.findExpensesByDate(accountId, startDateTime, endDateTime);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}/expenses/total")
    public ResponseEntity<BigDecimal> getTotalExpensesByAccountId(@PathVariable Long accountId) {
        BigDecimal total = expenseService.getTotalExpensesByAccountId(accountId);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense updatedExpense) {
        Expense updated = expenseService.updateExpense(id, updatedExpense);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{userId}/expenses")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/expenses/total")
    public ResponseEntity<BigDecimal> getTotalExpensesByUserId(@PathVariable Long userId) {
        BigDecimal total = expenseService.getTotalExpensesByUserId(userId);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<User> getUserByFirstNameAndLastNameAndId(@RequestParam String firstname,
                                                                   @RequestParam String lastname,
                                                                   @RequestParam Long id) {
        User user = userService.getUserByFirstNameAndLastNameAndId(firstname, lastname, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {

 */