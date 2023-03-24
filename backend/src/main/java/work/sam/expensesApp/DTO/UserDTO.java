package work.sam.expensesApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastname;
    private Integer age;
    private String location;
    private Double income;
    private List<ExpenseDTO> expenses;
    private List<AccountDTO> accounts;
}
