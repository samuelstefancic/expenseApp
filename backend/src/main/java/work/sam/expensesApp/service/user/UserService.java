package work.sam.expensesApp.service.user;

import org.springframework.stereotype.Service;
import work.sam.expensesApp.DTO.ExpenseDTO;
import work.sam.expensesApp.entity.User;
@Service
public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByFirstNameAndLastNameAndId(String firstname, String lastname, Long id);
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);
    ExpenseDTO addExpense(Long userId, ExpenseDTO expenseDTO);
}
