package work.sam.expensesApp.entitiesTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.sam.expensesApp.entity.*;
import work.sam.expensesApp.service.user.UserServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class fullTestEntityCreation {
    private User user;
    @Autowired
    private UserServiceImpl userService;


    @Test

    public void createEntity() {
        //Création des éléments de test

        //User
        User user = new User("Prénom", "Nom", 25, "City", BigDecimal.valueOf(35000));
        userService.createUser(user);


        //Expense
        Expense expenseOne = new Expense();
        Expense expenseTwo = new Expense();
        expenseOne.setAmount(BigDecimal.valueOf(1500));
        expenseTwo.setAmount(BigDecimal.valueOf(3500));

        //Description
        Description description1Text = new Description("Achat télévision");
        Description description2Text = new Description("Achat Voiture");
        List<Description> description1 = new ArrayList<>();
        description1.add(description1Text);
        List<Description> description2 = new ArrayList<>();
        description2.add(description2Text);
        expenseOne.setDescription(description1);



        //Account

        Account account = new Account();
        account.setAccountName("Main Account");
        account.setPassword("password123");
        account.setUser(user);

        //Category
        Category categoryOne = new Category();
        categoryOne.setName("Category1");
        categoryOne.setAccount(account);
        expenseOne.setCategory(categoryOne);

        Category categoryTwo = new Category();
        categoryTwo.setName("Category2");
        categoryTwo.setAccount(account);
        expenseTwo.setCategory(categoryTwo);
    }
}
