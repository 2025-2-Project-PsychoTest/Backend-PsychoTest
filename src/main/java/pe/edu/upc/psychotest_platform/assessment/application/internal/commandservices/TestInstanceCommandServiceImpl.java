package pe.edu.upc.psychotest_platform.assessment.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.*;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestInstanceCommandService;
import pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories.TestInstanceRepository;
import pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories.TestRepository;

import java.util.Optional;

/**
 * TestInstanceCommandServiceImpl.
 * Implementation of TestInstanceCommandService.
 */
@Service
public class TestInstanceCommandServiceImpl implements TestInstanceCommandService {

    private final TestInstanceRepository testInstanceRepository;
    private final TestRepository testRepository;

    public TestInstanceCommandServiceImpl(TestInstanceRepository testInstanceRepository,
                                          TestRepository testRepository) {
        this.testInstanceRepository = testInstanceRepository;
        this.testRepository = testRepository;
    }

    @Override
    @Transactional
    public Optional<TestInstance> handle(StartTestInstanceCommand command) {
        // Verify test exists and is active
        var testOpt = testRepository.findById(command.testId());
        if (testOpt.isEmpty()) {
            throw new IllegalArgumentException("Test not found with ID: " + command.testId());
        }

        var test = testOpt.get();
        if (!test.getIsActive()) {
            throw new IllegalStateException("Cannot start an inactive test");
        }

        // Check if student already has an instance for this test
        var existingInstance = testInstanceRepository.findByStudentIdAndTestId(
                command.studentId(), command.testId());
        if (existingInstance.isPresent()) {
            throw new IllegalStateException("Student already has an instance of this test");
        }

        // Create and start test instance
        var testInstance = new TestInstance(command.testId(), command.studentId());
        testInstance.start();

        var savedInstance = testInstanceRepository.save(testInstance);
        return Optional.of(savedInstance);
    }

    @Override
    @Transactional
    public Optional<TestInstance> handle(SubmitAnswerCommand command) {
        var instanceOpt = testInstanceRepository.findById(command.testInstanceId());
        if (instanceOpt.isEmpty()) {
            throw new IllegalArgumentException("Test instance not found with ID: " + command.testInstanceId());
        }

        var testInstance = instanceOpt.get();
        testInstance.submitAnswer(command.questionId(), command.answerId());

        var updatedInstance = testInstanceRepository.save(testInstance);
        return Optional.of(updatedInstance);
    }

    @Override
    @Transactional
    public Optional<TestInstance> handle(CompleteTestInstanceCommand command) {
        var instanceOpt = testInstanceRepository.findById(command.testInstanceId());
        if (instanceOpt.isEmpty()) {
            throw new IllegalArgumentException("Test instance not found with ID: " + command.testInstanceId());
        }

        var testInstance = instanceOpt.get();

        // Get the test to calculate score
        var testOpt = testRepository.findById(testInstance.getTestId());
        if (testOpt.isEmpty()) {
            throw new IllegalArgumentException("Test not found");
        }

        var test = testOpt.get();

        // Calculate score (simplified - in real scenario would check correct answers)
        double maxScore = test.getQuestions().stream()
                .mapToInt(q -> q.getPoints())
                .sum();

        // For now, calculate based on answered questions
        double score = testInstance.getAnswers().size() * 1.0; // Placeholder logic

        testInstance.complete(score, maxScore);

        var updatedInstance = testInstanceRepository.save(testInstance);
        return Optional.of(updatedInstance);
    }

    @Override
    @Transactional
    public Optional<TestInstance> handle(ShareTestWithPsychologistCommand command) {
        var instanceOpt = testInstanceRepository.findById(command.testInstanceId());
        if (instanceOpt.isEmpty()) {
            throw new IllegalArgumentException("Test instance not found with ID: " + command.testInstanceId());
        }

        var testInstance = instanceOpt.get();
        testInstance.shareWithPsychologist(command.psychologistId());

        var updatedInstance = testInstanceRepository.save(testInstance);
        return Optional.of(updatedInstance);
    }

    @Override
    @Transactional
    public Optional<TestInstance> handle(ValidateTestCommand command) {
        var instanceOpt = testInstanceRepository.findById(command.testInstanceId());
        if (instanceOpt.isEmpty()) {
            throw new IllegalArgumentException("Test instance not found with ID: " + command.testInstanceId());
        }

        var testInstance = instanceOpt.get();
        testInstance.validateByPsychologist(command.psychologistId(), command.validationNotes());

        var updatedInstance = testInstanceRepository.save(testInstance);
        return Optional.of(updatedInstance);
    }
}

