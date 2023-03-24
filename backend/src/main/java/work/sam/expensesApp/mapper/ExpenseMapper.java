package work.sam.expensesApp.mapper;

import org.springframework.http.HttpStatus;
import work.sam.expensesApp.DTO.ExpenseDTO;
import work.sam.expensesApp.entity.Expense;
import work.sam.expensesApp.exception.MappingException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseMapper {

    public static ExpenseDTO toDTO(Expense expense) {
        if (expense == null) {
            throw new MappingException("Expense is null", HttpStatus.NOT_FOUND);
        }
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount() != null ? expense.getAmount() : BigDecimal.ZERO);
        dto.setDescriptions(expense.getDescription());
        dto.setCategoryId(expense.getCategory() != null ? expense.getCategory().getId() : null);
        dto.setAccountId(expense.getAccount() != null ? expense.getAccount().getId() : null);
        return dto;
    }
    public static List<ExpenseDTO> toDTOList(List<Expense> expenses) {
        return expenses.stream().map(ExpenseMapper::toDTO).collect(Collectors.toList());
    }

}
