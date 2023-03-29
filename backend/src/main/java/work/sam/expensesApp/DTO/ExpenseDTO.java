package work.sam.expensesApp.DTO;

import lombok.Getter;
import lombok.Setter;
import work.sam.expensesApp.entity.Description;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExpenseDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;
    private List<DescriptionDTO> descriptions;
    private UserDTO user;
    private CategoryDTO category;
    private AccountDTO account;
}
