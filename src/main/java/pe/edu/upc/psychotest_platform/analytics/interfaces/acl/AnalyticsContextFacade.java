package pe.edu.upc.psychotest_platform.analytics.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.TestProgress;
import pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects.AppointmentSummary;
import pe.edu.upc.psychotest_platform.analytics.domain.services.StudentAnalyticsQueryService;
import pe.edu.upc.psychotest_platform.analytics.domain.services.PsychologistAnalyticsQueryService;

import java.util.Optional;

/**
 * AnalyticsContextFacade.
 * Anti-Corruption Layer (ACL) facade for Analytics bounded context.
 * Provides public API for other bounded contexts to access analytics data.
 */
@Service
public class AnalyticsContextFacade {

    private final StudentAnalyticsQueryService studentAnalyticsQueryService;
    private final PsychologistAnalyticsQueryService psychologistAnalyticsQueryService;

    public AnalyticsContextFacade(
            StudentAnalyticsQueryService studentAnalyticsQueryService,
            PsychologistAnalyticsQueryService psychologistAnalyticsQueryService) {
        this.studentAnalyticsQueryService = studentAnalyticsQueryService;
        this.psychologistAnalyticsQueryService = psychologistAnalyticsQueryService;
    }

    /**
     * Get complete student analytics overview by user ID.
     * @param userId the user ID
     * @return optional student analytics
     */
    public Optional<StudentAnalytics> getStudentOverview(Long userId) {
        var query = new GetStudentOverviewQuery(userId);
        return studentAnalyticsQueryService.handle(query);
    }

    /**
     * Get student test progress by user ID.
     * @param userId the user ID
     * @return optional test progress
     */
    public Optional<TestProgress> getStudentTestProgress(Long userId) {
        var query = new GetStudentTestProgressQuery(userId);
        return studentAnalyticsQueryService.handle(query);
    }

    /**
     * Get complete psychologist analytics overview by user ID.
     * @param userId the user ID
     * @return optional psychologist analytics
     */
    public Optional<PsychologistAnalytics> getPsychologistOverview(Long userId) {
        var query = new GetPsychologistOverviewQuery(userId);
        return psychologistAnalyticsQueryService.handle(query);
    }

    /**
     * Get psychologist appointment summary by user ID.
     * @param userId the user ID
     * @return optional appointment summary
     */
    public Optional<AppointmentSummary> getPsychologistAppointmentSummary(Long userId) {
        var query = new GetPsychologistAppointmentSummaryQuery(userId);
        return psychologistAnalyticsQueryService.handle(query);
    }

    /**
     * Check if student analytics exists for user.
     * @param userId the user ID
     * @return true if exists, false otherwise
     */
    public boolean existsStudentAnalytics(Long userId) {
        var query = new GetStudentAnalyticsByUserIdQuery(userId);
        return studentAnalyticsQueryService.handle(query).isPresent();
    }

    /**
     * Check if psychologist analytics exists for user.
     * @param userId the user ID
     * @return true if exists, false otherwise
     */
    public boolean existsPsychologistAnalytics(Long userId) {
        var query = new GetPsychologistAnalyticsByUserIdQuery(userId);
        return psychologistAnalyticsQueryService.handle(query).isPresent();
    }
}
