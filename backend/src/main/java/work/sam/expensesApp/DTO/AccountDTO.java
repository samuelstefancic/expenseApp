package work.sam.expensesApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountDTO {
    private Long id;
    private String accountName;
    private String password;
    private List<CategoryDTO> categories;
    private List<ExpenseDTO> expenses;
}
