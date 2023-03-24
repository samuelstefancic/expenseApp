package work.sam.expensesApp.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import work.sam.expensesApp.DTO.AccountDTO;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.exception.MappingException;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        if (account == null) {
            throw new MappingException("Account is null", HttpStatus.NOT_FOUND);
        }
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountName(account.getAccountName());
        dto.setPassword(account.getPassword());
        dto.setCategories(CategoryMapper.toDTOList(account.getCategories()));
        dto.setExpenses(ExpenseMapper.toDTOList(account.getExpenses()));
        return dto;
    }

    public static List<AccountDTO> toDTOList(List<Account> accounts) {
        return accounts.stream().map(AccountMapper::toDTO).collect(Collectors.toList());
    }
}
