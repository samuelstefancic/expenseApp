package work.sam.expensesApp.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import work.sam.expensesApp.exception.ExpenseException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    @PositiveOrZero
    private BigDecimal amount;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Description> description = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Expense(BigDecimal amount, List<Description> description, User user) {
        this.amount = amount;
        this.description = description;
        this.user = user;
    }

    public void setAmountWithValidation(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ExpenseException("The amount must be positive", HttpStatus.BAD_REQUEST);
        }
        this.amount = amount;
    }


}
