package work.sam.expensesApp.DTOTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import work.sam.expensesApp.DTO.UserDTO;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.mapper.UserMapper;
import work.sam.expensesApp.service.expense.ExpenseService;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;
import work.sam.expensesApp.service.user.UserServiceImpl;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GlobalDTOTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

  @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

  @Autowired
  private UserMapper userMapper;
    @Test
    public void createUserDTO() {
        User userDTO = new User();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setAge(30);
        userDTO.setLocation("New York");
        userDTO.setIncome(BigDecimal.valueOf(90000));
        userServiceImpl.createUser(userDTO);
        userMapper.toDTO(userDTO);

    }

}
/*
    @Test
    public void testWithDTOs() {
        // Create User and save it
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setAge(30);
        userDTO.setLocation("New York");
        userDTO.setIncome(BigDecimal.valueOf(90000));
        userServiceImpl.createUser(userDTO);

        // Create Account and save it
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountName("Main Account");
        accountDTO.setPassword("password123");
        accountDTO.setUser(userDTO);
        accountServiceImpl.createAccount(accountDTO);

        // Create Category and save it
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Groceries");
        categoryDTO.setAccount(accountDTO);
        categoryServiceImpl.createCategory(categoryDTO);

        // Create Descriptions
        DescriptionDTO description1 = new DescriptionDTO();
        description1.setText("Bought groceries");
        DescriptionDTO description2 = new DescriptionDTO();
        description2.setText("Bought more groceries");
        List<DescriptionDTO> descriptions1 = Collections.singletonList(description1);
        List<DescriptionDTO> descriptions2 = Collections.singletonList(description2);

        // Create Expenses and save them
        ExpenseDTO expenseDTO1 = new ExpenseDTO();
        expenseDTO1.setAmount(BigDecimal.valueOf(100));
        expenseDTO1.setDescriptions(descriptions1);
        expenseDTO1.setUser(userDTO);
        expenseDTO1.setCategory(categoryDTO);
        expenseDTO1.setAccount(accountDTO);
        expenseServiceImpl.createExpense(expenseDTO1);

        ExpenseDTO expenseDTO2 = new ExpenseDTO();
        expenseDTO2.setAmount(BigDecimal.valueOf(200));
        expense
        */