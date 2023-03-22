package work.sam.expensesApp.service;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.exception.UserException;
import work.sam.expensesApp.repository.UserRepository;

import java.util.Optional;

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
                .orElseThrow(() -> new UserException("user not found", HttpStatus.NOT_FOUND));
    }

    public User getUserByFirstNameAndLastNameAndId(String firstname, String lastname, Long id) {
        if (firstname == null || firstname.trim().isEmpty()) {
            throw new UserException("First name is required", HttpStatus.BAD_REQUEST );
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            throw new UserException("Last name is required", HttpStatus.BAD_REQUEST);
        }
        if (!userRepository.existsById(id)) {
            throw new UserException("User with ID : " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return userRepository.findByFirstNameAndLastNameAndId(firstname, lastname, id);
    }

    //Update
    public User updateUser(Long id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setExpenses(updatedUser.getExpenses());
            existingUser.setLocation(updatedUser.getLocation());
            existingUser.setAge(updatedUser.getAge());
            return userRepository.save(existingUser);
        } else {
            throw new UserException("Server not found with id : " + id, HttpStatus.BAD_REQUEST);
        }
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
