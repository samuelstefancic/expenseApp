package work.sam.expensesApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.sam.expensesApp.entity.Account;

public interface AccountRespository extends JpaRepository<Account, Long> {

    Account findByAccountName(String accountName);


    Account getAccountById(Long accountID);
}
