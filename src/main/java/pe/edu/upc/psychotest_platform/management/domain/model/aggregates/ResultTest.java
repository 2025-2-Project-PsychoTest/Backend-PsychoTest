package pe.edu.upc.psychotest_platform.management.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.psychotest_platform.management.domain.model.entities.ResultQuestion;
import pe.edu.upc.psychotest_platform.management.domain.model.valueobjects.StudentId;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "results_tests")
@NoArgsConstructor
@Getter
public class ResultTest extends AuditableAbstractAggregateRoot<ResultTest> {

    @Column(nullable = false)
    private Long testId;

    @Embedded // Value Object
    private StudentId studentId;

    @Column(nullable = false)
    private Instant date;

    private String summary;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "test_results_points", joinColumns = @JoinColumn(name = "results_test_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "points")
    private Map<String, Double> points;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "results_test_id", nullable = false)
    private List<ResultQuestion> resultQuestions;


    // Constructor
    public ResultTest(Long testId, StudentId studentId, List<ResultQuestion> resultQuestions) {
        if (testId == null) {
            throw new IllegalArgumentException("Test ID cannot be null");
        }
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        this.testId = testId;
        this.studentId = studentId;
        this.resultQuestions = resultQuestions;
        this.date = Instant.now();

        this.castResult(); //calcular resultados
    }

    public void castResult() {
        double totalPoints = 0.0;
        for (ResultQuestion rq : this.resultQuestions) {
            // totalPoints += rq.calculatePoints();
        }
        this.points = Map.of("total", totalPoints);
        this.summary = "Resultado calculado: " + totalPoints;
    }
}