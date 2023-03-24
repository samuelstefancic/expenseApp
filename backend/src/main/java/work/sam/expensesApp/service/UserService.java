package work.sam.expensesApp.service;

import work.sam.expensesApp.entity.User;
public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByFirstNameAndLastNameAndId(String firstname, String lastname, Long id);
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);
}
