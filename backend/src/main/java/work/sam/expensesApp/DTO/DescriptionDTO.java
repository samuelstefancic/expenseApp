package work.sam.expensesApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DescriptionDTO {
    private Long id;
    private String text;
    private LocalDateTime updatedAt;
    private ExpenseDTO expense;
}
