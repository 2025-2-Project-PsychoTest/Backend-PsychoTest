package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.assemblers;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.AppointmentSummary;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.SharedInfoInsight;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.UsageMetrics;
import pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PsychologistAnalyticsAssembler.
 * Assembler to convert between PsychologistAnalytics entity and resources.
 */
public class PsychologistAnalyticsAssembler {

    /**
     * Convert PsychologistAnalytics entity to PsychologistAnalyticsResource.
     */
    public static PsychologistAnalyticsResource toResourceFromEntity(PsychologistAnalytics entity) {
        return new PsychologistAnalyticsResource(
                entity.getId(),
                entity.getUserId(),
                toAppointmentSummaryResource(entity.getAppointmentSummary()),
                toSharedInfoInsightResourceList(entity.getSharedInfoInsights()),
                toUsageMetricsResource(entity.getUsageMetrics())
        );
    }

    /**
     * Convert AppointmentSummary to AppointmentSummaryResource.
     */
    public static AppointmentSummaryResource toAppointmentSummaryResource(
            AppointmentSummary appointmentSummary) {
        if (appointmentSummary == null) {
            return new AppointmentSummaryResource(0, 0, 0, 0, 0, 0.0);
        }
        return new AppointmentSummaryResource(
                appointmentSummary.totalAppointments(),
                appointmentSummary.upcomingAppointments(),
                appointmentSummary.completedAppointments(),
                appointmentSummary.cancelledAppointments(),
                appointmentSummary.monthlyAppointments(),
                appointmentSummary.getCompletionRate()
        );
    }

    /**
     * Convert list of SharedInfoInsight to list of SharedInfoInsightResource.
     */
    public static List<SharedInfoInsightResource> toSharedInfoInsightResourceList(
            List<SharedInfoInsight> sharedInfoInsights) {
        return sharedInfoInsights.stream()
                .map(PsychologistAnalyticsAssembler::toSharedInfoInsightResource)
                .collect(Collectors.toList());
    }

    /**
     * Convert SharedInfoInsight to SharedInfoInsightResource.
     */
    public static SharedInfoInsightResource toSharedInfoInsightResource(
            SharedInfoInsight sharedInfoInsight) {
        return new SharedInfoInsightResource(
                sharedInfoInsight.postsPublished(),
                sharedInfoInsight.totalViews(),
                sharedInfoInsight.testsValidated(),
                sharedInfoInsight.studentsMonitored(),
                sharedInfoInsight.getAverageViewsPerPost()
        );
    }

    /**
     * Convert UsageMetrics to UsageMetricsResource.
     */
    public static UsageMetricsResource toUsageMetricsResource(UsageMetrics usageMetrics) {
        if (usageMetrics == null) {
            return new UsageMetricsResource(0, 0L, null, 0, 0.0, 0.0);
        }
        return new UsageMetricsResource(
                usageMetrics.loginCount(),
                usageMetrics.timeSpentMinutes(),
                usageMetrics.lastLoginDate(),
                usageMetrics.actionsPerformed(),
                usageMetrics.getAverageTimePerLogin(),
                usageMetrics.getAverageActionsPerLogin()
        );
    }
}

