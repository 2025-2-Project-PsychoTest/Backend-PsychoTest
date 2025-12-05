package pe.edu.upc.psychotest_platform.analytics.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.CreatePsychologistAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdatePsychologistAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.services.PsychologistAnalyticsCommandService;
import pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories.PsychologistAnalyticsRepository;
import pe.edu.upc.psychotest_platform.shared.domain.exceptions.ValidationException;

import java.util.Optional;

/**
 * PsychologistAnalyticsCommandServiceImpl.
 * Implementation of psychologist analytics command service.
 */
@Service
public class PsychologistAnalyticsCommandServiceImpl implements PsychologistAnalyticsCommandService {

    private final PsychologistAnalyticsRepository psychologistAnalyticsRepository;

    public PsychologistAnalyticsCommandServiceImpl(PsychologistAnalyticsRepository psychologistAnalyticsRepository) {
        this.psychologistAnalyticsRepository = psychologistAnalyticsRepository;
    }

    @Override
    public Optional<PsychologistAnalytics> handle(CreatePsychologistAnalyticsCommand command) {
        // Verify that analytics doesn't already exist for this user
        if (psychologistAnalyticsRepository.existsByUserId(command.userId())) {
            throw new ValidationException("Psychologist analytics already exists for user ID: " + command.userId());
        }

        // Create new psychologist analytics with default values
        var psychologistAnalytics = new PsychologistAnalytics(
                command.userId(),
                null,  // Will use default constructor values
                null,
                null
        );

        // Save and return
        var savedAnalytics = psychologistAnalyticsRepository.save(psychologistAnalytics);
        return Optional.of(savedAnalytics);
    }

    @Override
    public Optional<PsychologistAnalytics> handle(UpdatePsychologistAnalyticsCommand command) {
        // Find existing analytics
        var existingAnalytics = psychologistAnalyticsRepository.findByUserId(command.userId());
        if (existingAnalytics.isEmpty()) {
            throw new ValidationException("Psychologist analytics not found for user ID: " + command.userId());
        }

        var analytics = existingAnalytics.get();

        // Update fields if provided
        if (command.appointmentSummary() != null) {
            analytics.updateAppointmentSummary(command.appointmentSummary());
        }
        if (command.sharedInfoInsights() != null) {
            analytics.updateSharedInfoInsights(command.sharedInfoInsights());
        }
        if (command.usageMetrics() != null) {
            analytics.updateUsageMetrics(command.usageMetrics());
        }

        // Save and return
        var updatedAnalytics = psychologistAnalyticsRepository.save(analytics);
        return Optional.of(updatedAnalytics);
    }
}

