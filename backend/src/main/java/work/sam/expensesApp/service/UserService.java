package work.sam.expensesApp.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.exception.UserException;
import work.sam.expensesApp.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Créer l'utilisateur
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataAccessException | ConstraintViolationException e) {
            throw new UserException("Failed to create user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Find l'utilisateur

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("user with ID : " + id + "not found", HttpStatus.NOT_FOUND));
    }

    public User getUserByFirstNameAndLastNameAndId(@NotBlank String firstname, @NotBlank String lastname, Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserException("User with ID : " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return userRepository.findByFirstNameAndLastNameAndId(firstname, lastname, id);
    }

    //Update
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("The user with id " + id + " does not exist", HttpStatus.NOT_FOUND));

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setAge(updatedUser.getAge());
            user.setLocation(updatedUser.getLocation());
            user.setIncome(updatedUser.getIncome());

            // Met à jour les dépenses existantes et ajoute les nouvelles dépenses
            List<Expense> updatedExpenses = updatedUser.getExpenses();
            Map<Long, Expense> updatedExpensesMap = updatedExpenses.stream()
                    .collect(Collectors.toMap(Expense::getId, Function.identity()));

            List<Expense> existingExpenses = user.getExpenses();
            Map<Long, Expense> existingExpensesMap = existingExpenses.stream()
                    .collect(Collectors.toMap(Expense::getId, Function.identity()));

            // Update les dépenses existantes
            updatedExpensesMap.forEach((expenseId, updatedExpense) -> {
                Expense existingExpense = existingExpensesMap.get(expenseId);
                if (existingExpense != null) {
                    existingExpense.setAmount(updatedExpense.getAmount());
                    existingExpense.setDescription(updatedExpense.getDescription());
                } else {
                    user.addExpense(updatedExpense);
                }
            });

            // Remove les expenses qui ne sont pas présentes dans la liste mise à jour
            existingExpenses.removeIf(expense -> !updatedExpensesMap.containsKey(expense.getId()));

            return userRepository.save(user);

    }



    //Delete
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserException("User with ID : " + id + " not found", HttpStatus.NOT_FOUND);
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserException("failed to delete user with id : " + id, HttpStatus.BAD_REQUEST);
        }

    }
}
