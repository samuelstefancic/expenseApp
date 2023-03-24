package work.sam.expensesApp.DTOTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.sam.expensesApp.DTO.ExpenseDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseDTOTest {

    private ExpenseDTO expenseDTO;
    @BeforeEach
    public void setUp() {
        expenseDTO = new ExpenseDTO();
        expenseDTO.setAmount(new BigDecimal("10.00"));
    }

    @Test
    public void testExpenseDTOProperties() {
        assertEquals(new BigDecimal("10.00"), expenseDTO.getAmount());
    }
}
