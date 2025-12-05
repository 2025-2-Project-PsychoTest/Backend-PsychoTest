package pe.edu.upc.psychotest_platform.analytics.domain.services;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.CreatePsychologistAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdatePsychologistAnalyticsCommand;

import java.util.Optional;

/**
 * PsychologistAnalyticsCommandService.
 * Service interface for psychologist analytics command operations.
 */
public interface PsychologistAnalyticsCommandService {

    /**
     * Handle create psychologist analytics command.
     * @param command the create psychologist analytics command
     * @return the created psychologist analytics
     */
    Optional<PsychologistAnalytics> handle(CreatePsychologistAnalyticsCommand command);

    /**
     * Handle update psychologist analytics command.
     * @param command the update psychologist analytics command
     * @return the updated psychologist analytics
     */
    Optional<PsychologistAnalytics> handle(UpdatePsychologistAnalyticsCommand command);
}

