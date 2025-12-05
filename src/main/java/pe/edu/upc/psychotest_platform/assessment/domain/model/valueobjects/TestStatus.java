package pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * TestStatus Value Object.
 * Represents the status of a test instance taken by a student.
 */
@Embeddable
public record TestStatus(String status) {

    /**
     * Default constructor for JPA.
     */
    public TestStatus() {
        this(null);
    }

    /**
     * Constructor with validation.
     */
    public TestStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Test status cannot be null or empty");
        }
    }

    /**
     * Predefined test statuses.
     */
    public static TestStatus NOT_STARTED = new TestStatus("NOT_STARTED");
    public static TestStatus IN_PROGRESS = new TestStatus("IN_PROGRESS");
    public static TestStatus COMPLETED = new TestStatus("COMPLETED");
    public static TestStatus VALIDATED = new TestStatus("VALIDATED");
    public static TestStatus SHARED = new TestStatus("SHARED");

    /**
     * Check if test is completed.
     */
    public boolean isCompleted() {
        return "COMPLETED".equals(status) || "VALIDATED".equals(status) || "SHARED".equals(status);
    }

    /**
     * Check if test is validated.
     */
    public boolean isValidated() {
        return "VALIDATED".equals(status) || "SHARED".equals(status);
    }
}

