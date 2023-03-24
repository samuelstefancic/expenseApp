package work.sam.expensesApp.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import work.sam.expensesApp.DTO.UserDTO;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.exception.MappingException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            throw new MappingException("User is null", HttpStatus.NOT_FOUND);
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastname(user.getLastName());
        dto.setAge(user.getAge());
        dto.setLocation(user.getLocation());
        dto.setIncome(user.getIncome());
        dto.setExpenses(ExpenseMapper.toDTOList(user.getExpenses()));
        dto.setAccounts(AccountMapper.toDTOList(user.getAccounts()));
        return dto;
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }
}






