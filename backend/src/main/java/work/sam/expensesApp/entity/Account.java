package work.sam.expensesApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator_accounts")
    @SequenceGenerator(name="generator_accounts", sequenceName = "sequence_account", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(name = "accountName", nullable = false, unique = true)
    private String accountName;

    @NotBlank
    @Column(name="accountPassword", nullable = false)
    private String password;



    //Ajouter les informations de compte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    //Ajouter la liste de dépenses pour ce compte
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    //List de catégories

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    public Account(String accountName, String password, User user, List<Expense> expenses, List<Category> categories) {
        this.accountName = accountName;
        this.password = password;
        this.user = user;
        this.expenses = expenses;
        this.categories = categories;
    }
}
