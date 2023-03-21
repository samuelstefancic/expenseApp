package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.sam.expensesApp.entity.Expense;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
