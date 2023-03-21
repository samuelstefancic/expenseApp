package work.sam.expensesApp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.sam.expensesApp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
