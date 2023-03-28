package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.sam.expensesApp.entity.Description;
@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
