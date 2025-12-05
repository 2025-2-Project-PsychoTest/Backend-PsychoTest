package pe.edu.upc.psychotest_platform.analytics.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.CreateStudentAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdateStudentAnalyticsCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.services.StudentAnalyticsCommandService;
import pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories.StudentAnalyticsRepository;
import pe.edu.upc.psychotest_platform.shared.domain.exceptions.ValidationException;

import java.util.Optional;

/**
 * StudentAnalyticsCommandServiceImpl.
 * Implementation of student analytics command service.
 */
@Service
public class StudentAnalyticsCommandServiceImpl implements StudentAnalyticsCommandService {

    private final StudentAnalyticsRepository studentAnalyticsRepository;

    public StudentAnalyticsCommandServiceImpl(StudentAnalyticsRepository studentAnalyticsRepository) {
        this.studentAnalyticsRepository = studentAnalyticsRepository;
    }

    @Override
    public Optional<StudentAnalytics> handle(CreateStudentAnalyticsCommand command) {
        // Verify that analytics doesn't already exist for this user
        if (studentAnalyticsRepository.existsByUserId(command.userId())) {
            throw new ValidationException("Student analytics already exists for user ID: " + command.userId());
        }

        // Create new student analytics with default values
        var studentAnalytics = new StudentAnalytics(
                command.userId(),
                null,  // Will use default constructor values
                null,
                null
        );

        // Save and return
        var savedAnalytics = studentAnalyticsRepository.save(studentAnalytics);
        return Optional.of(savedAnalytics);
    }

    @Override
    public Optional<StudentAnalytics> handle(UpdateStudentAnalyticsCommand command) {
        // Find existing analytics
        var existingAnalytics = studentAnalyticsRepository.findByUserId(command.userId());
        if (existingAnalytics.isEmpty()) {
            throw new ValidationException("Student analytics not found for user ID: " + command.userId());
        }

        var analytics = existingAnalytics.get();

        // Update fields if provided
        if (command.testProgress() != null) {
            analytics.updateTestProgress(command.testProgress());
        }
        if (command.careerRecommendations() != null) {
            analytics.updateCareerRecommendations(command.careerRecommendations());
        }
        if (command.usageMetrics() != null) {
            analytics.updateUsageMetrics(command.usageMetrics());
        }

        // Save and return
        var updatedAnalytics = studentAnalyticsRepository.save(analytics);
        return Optional.of(updatedAnalytics);
    }
}

