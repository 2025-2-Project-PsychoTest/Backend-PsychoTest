package pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.AppointmentSummary;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.SharedInfoInsight;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.UsageMetrics;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * PsychologistAnalytics Aggregate Root.
 * Represents analytics data for a psychologist user in the psychotest platform.
 */
@Entity
@Table(name = "psychologist_analytics")
public class PsychologistAnalytics extends AuditableAbstractAggregateRoot<PsychologistAnalytics> {

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "totalAppointments", column = @Column(name = "total_appointments")),
        @AttributeOverride(name = "upcomingAppointments", column = @Column(name = "upcoming_appointments")),
        @AttributeOverride(name = "completedAppointments", column = @Column(name = "completed_appointments")),
        @AttributeOverride(name = "cancelledAppointments", column = @Column(name = "cancelled_appointments")),
        @AttributeOverride(name = "monthlyAppointments", column = @Column(name = "monthly_appointments"))
    })
    private AppointmentSummary appointmentSummary;

    @ElementCollection
    @CollectionTable(name = "psychologist_shared_info_insights", joinColumns = @JoinColumn(name = "psychologist_analytics_id"))
    private List<SharedInfoInsight> sharedInfoInsights;

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
    protected PsychologistAnalytics() {
        this.sharedInfoInsights = new ArrayList<>();
    }

    /**
     * Constructor with parameters.
     * @param userId the user id
     * @param appointmentSummary the appointment summary
     * @param sharedInfoInsights the shared info insights
     * @param usageMetrics the usage metrics
     */
    public PsychologistAnalytics(Long userId, AppointmentSummary appointmentSummary, List<SharedInfoInsight> sharedInfoInsights, UsageMetrics usageMetrics) {
        this();
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be valid");
        }
        this.userId = userId;
        this.appointmentSummary = appointmentSummary != null ? appointmentSummary : new AppointmentSummary();
        this.sharedInfoInsights = sharedInfoInsights != null ? new ArrayList<>(sharedInfoInsights) : new ArrayList<>();
        this.usageMetrics = usageMetrics != null ? usageMetrics : new UsageMetrics();
    }

    /**
     * Update appointment summary.
     * @param appointmentSummary the new appointment summary
     */
    public void updateAppointmentSummary(AppointmentSummary appointmentSummary) {
        if (appointmentSummary == null) {
            throw new IllegalArgumentException("Appointment summary cannot be null");
        }
        this.appointmentSummary = appointmentSummary;
    }

    /**
     * Update shared info insights.
     * @param sharedInfoInsights the new shared info insights
     */
    public void updateSharedInfoInsights(List<SharedInfoInsight> sharedInfoInsights) {
        if (sharedInfoInsights == null) {
            throw new IllegalArgumentException("Shared info insights cannot be null");
        }
        this.sharedInfoInsights = new ArrayList<>(sharedInfoInsights);
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
    public AppointmentSummary getAppointmentSummary() { return appointmentSummary; }
    public List<SharedInfoInsight> getSharedInfoInsights() { return new ArrayList<>(sharedInfoInsights); }
    public UsageMetrics getUsageMetrics() { return usageMetrics; }
}
