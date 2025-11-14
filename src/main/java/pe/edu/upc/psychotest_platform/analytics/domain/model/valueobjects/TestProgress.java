package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TestProgress {
    private int completedTests;
    private double averageScore;

    public TestProgress() {}

    public TestProgress(int completedTests, double averageScore) {
        if (completedTests < 0) {
            throw new IllegalArgumentException("Completed tests cannot be negative");
        }
        if (averageScore < 0 || averageScore > 100) {
            throw new IllegalArgumentException("Average score must be between 0 and 100");
        }
        this.completedTests = completedTests;
        this.averageScore = averageScore;
    }

    public int getCompletedTests() { return completedTests; }

    public double getAverageScore() { return averageScore; }
}

