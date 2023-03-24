package work.sam.expensesApp.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator_user")
    @SequenceGenerator(name="generator_user", sequenceName = "sequence_user", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name= "FirstName",nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "LastName", nullable = false)
    @NotBlank
    private String lastName;

    private int age;
    private String location;

    private BigDecimal income;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Valid
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Valid
    private List<Account> accounts = new ArrayList<>();

    public User(String firstName, String lastName, int age, String location, BigDecimal income, List<Expense> expenses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
        this.income = income;
        this.expenses = expenses != null ? expenses : new ArrayList<>();
    }

    public User(String firstName, String lastName, int age, String location, BigDecimal income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
        this.income = income;
    }
    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setUser(this);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expense.setUser(null);
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setUser(null);
    }

}
