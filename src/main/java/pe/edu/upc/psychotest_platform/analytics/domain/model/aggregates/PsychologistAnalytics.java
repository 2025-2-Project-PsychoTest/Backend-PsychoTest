package pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.AppointmentSummary;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.SharedInfoInsight;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.UsageMetrics;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.List;

@Entity
@Table(name = "psychologist_analytics")
public class PsychologistAnalytics extends AuditableAbstractAggregateRoot<PsychologistAnalytics> {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Embedded
    private AppointmentSummary appointmentSummary;

    @ElementCollection
    @CollectionTable(name = "psychologist_shared_info_insights", joinColumns = @JoinColumn(name = "psychologist_analytics_id"))
    private List<SharedInfoInsight> sharedInfoInsights;

    @Embedded
    private UsageMetrics usageMetrics;

    public PsychologistAnalytics() {}

    public PsychologistAnalytics(Long userId, AppointmentSummary appointmentSummary, List<SharedInfoInsight> sharedInfoInsights, UsageMetrics usageMetrics) {
        this.userId = userId;
        this.appointmentSummary = appointmentSummary;
        this.sharedInfoInsights = sharedInfoInsights;
        this.usageMetrics = usageMetrics;
    }

    public Long getUserId() { return userId; }

    public AppointmentSummary getAppointmentSummary() { return appointmentSummary; }

    public List<SharedInfoInsight> getSharedInfoInsights() { return sharedInfoInsights; }

    public UsageMetrics getUsageMetrics() { return usageMetrics; }
}
