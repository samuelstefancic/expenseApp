package work.sam.expensesApp.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import work.sam.expensesApp.exception.ExpenseException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator_expenses")
    @SequenceGenerator(name = "generator_expenses", sequenceName = "sequence_expenses", initialValue = 50,
    allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "amount", nullable = false)
    @PositiveOrZero
    private BigDecimal amount;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Description> description = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;


    public Expense(BigDecimal amount, String name, List<Description> description, User user) {
        this.amount = amount;
        this.description = description;
        this.user = user;
        this.name = name;
        this.date = LocalDateTime.now();
    }

    public Expense(BigDecimal amount, String name, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
        this.name = name;
    }
    public Expense(BigDecimal amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public void setAmountWithValidation(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ExpenseException("The amount must be positive", HttpStatus.BAD_REQUEST);
        }
        this.amount = amount;
    }
    public List<Description> getDescription() {
        return Collections.unmodifiableList(description);
    }

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

}
