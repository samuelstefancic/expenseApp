package work.sam.expensesApp.DTOTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.sam.expensesApp.DTO.AccountDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountDTOTest {
    private AccountDTO accountDTO;

    @BeforeEach
    public void setUp() {
        accountDTO = new AccountDTO();
        accountDTO.setAccountName("Savings");
        accountDTO.setPassword("mypassword");
    }

    @Test
    public void testAccountDTOProperties() {
        assertEquals("Savings", accountDTO.getAccountName());
        assertEquals("mypassword", accountDTO.getPassword());
    }
}
