package work.sam.expensesApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "description")
@Getter
@Setter
@NoArgsConstructor
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator_descriptions")
    @SequenceGenerator(name = "generator_descriptions", sequenceName = "sequence_description", initialValue = 15, allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "text", nullable = false)
    @Size(min = 1, max = 500)
    private String text;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    public Description(String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

}
