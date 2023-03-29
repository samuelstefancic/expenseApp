package work.sam.expensesApp.DTOTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import work.sam.expensesApp.DTO.AccountDTO;
import work.sam.expensesApp.DTO.CategoryDTO;
import work.sam.expensesApp.DTO.UserDTO;
import work.sam.expensesApp.entity.Account;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.entity.User;
import work.sam.expensesApp.mapper.*;
import work.sam.expensesApp.service.account.AccountServiceImpl;
import work.sam.expensesApp.service.category.CategoryServiceImpl;
import work.sam.expensesApp.service.description.DescriptionServiceImpl;
import work.sam.expensesApp.service.expense.ExpenseService;
import work.sam.expensesApp.service.expense.ExpenseServiceImpl;
import work.sam.expensesApp.service.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GlobalDTOTest {
//User
    @Autowired
    private UserServiceImpl userServiceImpl;
  @Autowired
  private UserMapper userMapper;

//Account
  @Autowired
  private AccountServiceImpl accountServiceImpl;
  @Autowired
  private AccountMapper accountMapper;

  //Expense

    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;
    @Autowired
    private ExpenseMapper expenseMapper;

    //Category
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private CategoryMapper categoryMapper;

    //Description

    @Autowired
    private DescriptionServiceImpl descriptionServiceImpl;
    @Autowired
    private DescriptionMapper descriptionMapper;

  //User DTO
    @Test
    public void createUserDTO() {
        User userDTO = new User();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setAge(30);
        userDTO.setLocation("New York");
        userDTO.setIncome(BigDecimal.valueOf(90000));
        User testCreatedUser = userServiceImpl.createUser(userDTO);

        assertNotNull(testCreatedUser.getId());
        assertEquals(userDTO.getFirstName(),testCreatedUser.getFirstName() );
        userMapper.toDTO(userDTO);

        //Account DTO

        Account accountDTO = new Account();
        accountDTO.setAccountName("Main Account Test");
        accountDTO.setPassword("password123");
        accountDTO.setUser(userDTO);
        accountServiceImpl.createAccount(accountDTO);
        Account testDto = accountServiceImpl.createAccount(accountDTO);
        assertNotNull(accountDTO);
       AccountDTO realAccountDTO = accountMapper.toDTO(accountDTO);
        assertEquals(accountDTO.getAccountName(), testDto.getAccountName());

        //Category

        Category categoryDTO = new Category();
        categoryDTO.setName("Groceries");
        categoryDTO.setAccount(accountDTO);
        categoryServiceImpl.createCategory(categoryDTO);

    }


}
