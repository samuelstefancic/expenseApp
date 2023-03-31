package work.sam.expensesApp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import work.sam.expensesApp.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstNameAndLastNameAndId(String firstname, String lastname, Long id);

    @Query("SELECT u FROM User u JOIN FETCH u.accounts a JOIN FETCH a.categories c JOIN FETCH a.expenses e")
    List<User> findAllWithAccountsCategoriesExpenses();
}
