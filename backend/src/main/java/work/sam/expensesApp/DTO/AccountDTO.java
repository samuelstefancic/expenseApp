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
    private UserDTO user;
    private List<ExpenseDTO> expenses;
    private List<CategoryDTO> categories;
}
