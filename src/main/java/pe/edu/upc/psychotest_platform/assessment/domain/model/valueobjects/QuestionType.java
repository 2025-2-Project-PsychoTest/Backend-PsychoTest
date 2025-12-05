package pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * QuestionType Value Object.
 * Represents the type of question in a test.
 */
@Embeddable
public record QuestionType(String type) {

    /**
     * Default constructor for JPA.
     */
    public QuestionType() {
        this(null);
    }

    /**
     * Constructor with validation.
     */
    public QuestionType {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Question type cannot be null or empty");
        }
    }

    /**
     * Predefined question types.
     */
    public static QuestionType MULTIPLE_CHOICE = new QuestionType("MULTIPLE_CHOICE");
    public static QuestionType TRUE_FALSE = new QuestionType("TRUE_FALSE");
    public static QuestionType SCALE = new QuestionType("SCALE");
    public static QuestionType OPEN_ENDED = new QuestionType("OPEN_ENDED");
}

