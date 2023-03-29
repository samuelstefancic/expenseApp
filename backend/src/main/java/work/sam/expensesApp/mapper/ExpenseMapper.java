package work.sam.expensesApp.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import work.sam.expensesApp.DTO.DescriptionDTO;
import work.sam.expensesApp.DTO.ExpenseDTO;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.exception.MappingException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ExpenseMapper {

    public static ExpenseDTO toDTO(Expense expense) {
        if (expense == null) {
            throw new MappingException("Expense is null", HttpStatus.NOT_FOUND);
        }
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount() != null ? expense.getAmount() : BigDecimal.ZERO);
        List<DescriptionDTO> descriptionDTOS = expense.getDescription()
                        .stream()
                                .map(DescriptionMapper::toDTO)
                                        .collect(Collectors.toList());
        dto.setDescriptions(descriptionDTOS);
        dto.setCategory(expense.getCategory() != null ? CategoryMapper.toDTO(expense.getCategory()) : null);
        dto.setAccount(expense.getAccount() != null ? AccountMapper.toDTO(expense.getAccount()) : null);
        return dto;
    }
    public static List<ExpenseDTO> toDTOList(List<Expense> expenses) {
        return expenses.stream().map(ExpenseMapper::toDTO).collect(Collectors.toList());
    }

}
