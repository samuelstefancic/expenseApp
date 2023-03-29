package work.sam.expensesApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastname;
    private Integer age;
    private String location;
    private BigDecimal income;
    private List<ExpenseDTO> expenses;
    private List<AccountDTO> accounts;


}
