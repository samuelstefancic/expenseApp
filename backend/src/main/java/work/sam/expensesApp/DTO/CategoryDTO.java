package work.sam.expensesApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private Long accountId;
    private List<ExpenseDTO> expenses;
}
