package pe.edu.upc.psychotest_platform.analytics.domain.services;

import java.util.Optional;

import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdateStudentAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.CreateStudentAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;

/**
 * Servicio de comandos para las operaciones de StudentAnalytics.
 */
public interface StudentAnalyticsCommandService {

    /**
     * Handle update student analytics command.
     *
     * @param command the update student analytics command
     * @return the updated student analytics
     */
    Optional<StudentAnalytics> handle(UpdateStudentAnalyticsCommand command);

    /**
     * Handle create student analytics command.
     *
     * @param command the create student analytics command
     * @return the created student analytics
     */
    Optional<StudentAnalytics> handle(CreateStudentAnalyticsCommand command);
}