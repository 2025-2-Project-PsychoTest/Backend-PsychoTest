package pe.edu.upc.psychotest_platform.assessment.domain.model.entities;

import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.QuestionType;
import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String questionText;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "question_type"))
    })
    private QuestionType questionType;

    @Column(name = "question_order", nullable = false)
    private Integer order;

    @Column(name = "points")
    private Integer points;

    protected Question() {
        // Default constructor for JPA
    }

    /**
     * Constructor with parameters.
     */
    public Question(String questionText, QuestionType questionType, Integer order, Integer points) {
        if (questionText == null || questionText.isBlank()) {
            throw new IllegalArgumentException("Question text cannot be null or empty");
        }
        if (questionType == null) {
            throw new IllegalArgumentException("Question type cannot be null");
        }
        if (order == null || order < 1) {
            throw new IllegalArgumentException("Order must be positive");
        }
        this.questionText = questionText;
        this.questionType = questionType;
        this.order = order;
        this.points = points != null ? points : 1;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public Integer getOrder() {
        return order;
    }

    public Integer getPoints() {
        return points;
    }
}