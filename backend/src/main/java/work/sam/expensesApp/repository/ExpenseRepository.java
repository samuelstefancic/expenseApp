package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.entity.Expense;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);

    List<Expense> findByAccountId(Long accountId);

    List<Expense> findByAccountAndCategory(Account account, Category category);

    List<Expense> findByAccountAndDateBetween(Account account, LocalDateTime startDate, LocalDateTime endDate);

    List<Expense> findByAccountAndName(Long accountId, String name);

    Optional<Expense> findByIdAndAccount(Long id, Long accountId);
    List<Expense> findByAccountIdAndNameContainingIgnoreCase(Long accountId, String name);

}
