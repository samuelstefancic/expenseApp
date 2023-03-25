package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.sam.expensesApp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
