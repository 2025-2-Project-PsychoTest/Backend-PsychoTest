package pe.edu.upc.psychotest_platform.management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.psychotest_platform.management.domain.model.entities.Question;
import pe.edu.upc.psychotest_platform.management.domain.model.valueobjects.TestDateRange;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tests")
@NoArgsConstructor
@Getter
public class Test extends AuditableAbstractAggregateRoot<Test> {

    @Column(nullable = false)
    private String name;

    private String description;

    @Embedded // Value Object
    private TestDateRange dateRange;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", nullable = false)
    private List<Question> questions;

    // Constructor
    public Test(String name, String description, TestDateRange dateRange, List<Question> questions) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Test name cannot be null or empty");
        }
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException("Test must have at least one question");
        }
        this.name = name;
        this.description = description;
        this.dateRange = dateRange;
        this.questions = questions;
    }


    public boolean start() {
        if (!this.dateRange.isWithinDate(Instant.now())) {
            return false;
        }
        return true;
    }

    public void pause() {
    }

    public void finish() {
    }
}