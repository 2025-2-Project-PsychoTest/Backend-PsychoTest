package pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.TestProgress;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.CareerRecommendation;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.UsageMetrics;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.List;

@Entity
@Table(name = "student_analytics")
public class StudentAnalytics extends AuditableAbstractAggregateRoot<StudentAnalytics> {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Embedded
    private TestProgress testProgress;

    @ElementCollection
    @CollectionTable(name = "student_career_recommendations", joinColumns = @JoinColumn(name = "student_analytics_id"))
    private List<CareerRecommendation> careerRecommendations;

    @Embedded
    private UsageMetrics usageMetrics;

    public StudentAnalytics() {}

    public StudentAnalytics(Long userId, TestProgress testProgress, List<CareerRecommendation> careerRecommendations, UsageMetrics usageMetrics) {
        this.userId = userId;
        this.testProgress = testProgress;
        this.careerRecommendations = careerRecommendations;
        this.usageMetrics = usageMetrics;
    }

    public Long getUserId() { return userId; }

    public TestProgress getTestProgress() { return testProgress; }

    public List<CareerRecommendation> getCareerRecommendations() { return careerRecommendations; }

    public UsageMetrics getUsageMetrics() { return usageMetrics; }
}
