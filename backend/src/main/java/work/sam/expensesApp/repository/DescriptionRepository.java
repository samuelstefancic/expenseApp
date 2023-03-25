package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.sam.expensesApp.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
