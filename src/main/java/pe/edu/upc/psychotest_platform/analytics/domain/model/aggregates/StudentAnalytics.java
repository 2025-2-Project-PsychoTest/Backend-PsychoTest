package pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.TestProgress;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.CareerRecommendation;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.UsageMetrics;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * StudentAnalytics Aggregate Root.
 * Represents analytics data for a student user in the psychotest platform.
 */
@Entity
@Table(name = "student_analytics")
public class StudentAnalytics extends AuditableAbstractAggregateRoot<StudentAnalytics> {

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "completedTests", column = @Column(name = "completed_tests")),
        @AttributeOverride(name = "averageScore", column = @Column(name = "average_score")),
        @AttributeOverride(name = "totalTests", column = @Column(name = "total_tests")),
        @AttributeOverride(name = "inProgressTests", column = @Column(name = "in_progress_tests"))
    })
    private TestProgress testProgress;

    @ElementCollection
    @CollectionTable(name = "student_career_recommendations", joinColumns = @JoinColumn(name = "student_analytics_id"))
    private List<CareerRecommendation> careerRecommendations;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "loginCount", column = @Column(name = "login_count")),
        @AttributeOverride(name = "timeSpentMinutes", column = @Column(name = "time_spent_minutes")),
        @AttributeOverride(name = "lastLoginDate", column = @Column(name = "last_login_date")),
        @AttributeOverride(name = "actionsPerformed", column = @Column(name = "actions_performed"))
    })
    private UsageMetrics usageMetrics;

    /**
     * Default constructor for JPA.
     */
    protected StudentAnalytics() {
        this.careerRecommendations = new ArrayList<>();
    }

    /**
     * Constructor with parameters.
     * @param userId the user id
     * @param testProgress the test progress
     * @param careerRecommendations the career recommendations
     * @param usageMetrics the usage metrics
     */
    public StudentAnalytics(Long userId, TestProgress testProgress, List<CareerRecommendation> careerRecommendations, UsageMetrics usageMetrics) {
        this();
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
        this.userId = userId;
        this.testProgress = testProgress != null ? testProgress : new TestProgress();
        this.careerRecommendations = careerRecommendations != null ? new ArrayList<>(careerRecommendations) : new ArrayList<>();
        this.usageMetrics = usageMetrics != null ? usageMetrics : new UsageMetrics();
    }

    /**
     * Update test progress.
     * @param testProgress the new test progress
     */
    public void updateTestProgress(TestProgress testProgress) {
        if (testProgress == null) {
            throw new IllegalArgumentException("Test progress cannot be null");
        }
        this.testProgress = testProgress;
    }

    /**
     * Update career recommendations.
     * @param careerRecommendations the new career recommendations
     */
    public void updateCareerRecommendations(List<CareerRecommendation> careerRecommendations) {
        if (careerRecommendations == null) {
            throw new IllegalArgumentException("Career recommendations cannot be null");
        }
        this.careerRecommendations = new ArrayList<>(careerRecommendations);
    }

    /**
     * Update usage metrics.
     * @param usageMetrics the new usage metrics
     */
    public void updateUsageMetrics(UsageMetrics usageMetrics) {
        if (usageMetrics == null) {
            throw new IllegalArgumentException("Usage metrics cannot be null");
        }
        this.usageMetrics = usageMetrics;
    }

    // Getters
    public Long getUserId() { return userId; }
    public TestProgress getTestProgress() { return testProgress; }
    public List<CareerRecommendation> getCareerRecommendations() { return new ArrayList<>(careerRecommendations); }
    public UsageMetrics getUsageMetrics() { return usageMetrics; }
}
