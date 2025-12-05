package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * TestProgress Value Object.
 * Represents the progress of a student in tests with completed tests count and average score.
 */
@Embeddable
public record TestProgress(int completedTests, double averageScore, int totalTests, int inProgressTests) {

    public TestProgress {
        if (completedTests < 0) {
            throw new IllegalArgumentException("Completed tests cannot be negative");
        }
        if (averageScore < 0 || averageScore > 100) {
            throw new IllegalArgumentException("Average score must be between 0 and 100");
        }
        if (totalTests < 0) {
            throw new IllegalArgumentException("Total tests cannot be negative");
        }
        if (inProgressTests < 0) {
            throw new IllegalArgumentException("In progress tests cannot be negative");
        }
        if (completedTests > totalTests) {
            throw new IllegalArgumentException("Completed tests cannot exceed total tests");
        }
    }

    /**
     * Default constructor for empty test progress.
     */
    public TestProgress() {
        this(0, 0.0, 0, 0);
    }

    /**
     * Gets the completion percentage of tests.
     * @return completion percentage
     */
    public double getCompletionPercentage() {
        if (totalTests == 0) return 0.0;
        return (completedTests * 100.0) / totalTests;
    }
}

