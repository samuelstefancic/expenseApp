package work.sam.expensesApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generateur_expenses")
    @SequenceGenerator(name = "generateur_expenses", sequenceName = "sequence_expenses", initialValue = 50,
    allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense(double amount, String description, User user) {
        this.amount = amount;
        this.description = description;
        this.user = user;
    }
}
