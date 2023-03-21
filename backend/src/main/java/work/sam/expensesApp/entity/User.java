package work.sam.expensesApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generateur_user")
    @SequenceGenerator(name="generateur_user", sequenceName = "sequence_user", initialValue = 20, allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name= "FirstName",nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    private int age;
    private String location;

    private double income;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public User(String firstName, String lastName, int age, String location, double income) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.location = location;
        this.income = income;
    }


}
