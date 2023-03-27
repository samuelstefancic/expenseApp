package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByAccount(Account account);
}
