package pe.edu.upc.psychotest_platform.assessment.domain.model.entities;

import jakarta.persistence.*;

/**
 * Answer Entity.
 * Represents an answer option for a question.
 */
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String answerText;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "answer_value")
    private Integer value;

    /**
     * Default constructor for JPA.
     */
    protected Answer() {
    }

    /**
     * Constructor with parameters.
     */
    public Answer(String answerText, Boolean isCorrect, Integer value) {
        if (answerText == null || answerText.isBlank()) {
            throw new IllegalArgumentException("Answer text cannot be null or empty");
        }
        this.answerText = answerText;
        this.isCorrect = isCorrect != null ? isCorrect : false;
        this.value = value;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public Integer getValue() {
        return value;
    }
}

