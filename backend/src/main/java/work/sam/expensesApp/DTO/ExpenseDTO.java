package work.sam.expensesApp.DTO;

import lombok.Getter;
import lombok.Setter;
import work.sam.expensesApp.entity.Description;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ExpenseDTO {
    private Long id;
    private BigDecimal amount;
    private List<Description> descriptions;
    private Long categoryId;
    private Long accountId;
}
