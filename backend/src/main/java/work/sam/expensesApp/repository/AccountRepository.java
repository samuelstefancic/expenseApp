package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.sam.expensesApp.entity.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountName(String accountName);


    Account getAccountById(Long accountID);
}
