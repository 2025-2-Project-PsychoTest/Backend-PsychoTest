package pe.edu.upc.psychotest_platform.assessment.application.internal.commandservices;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.AddQuestionToTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.CreateTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.DeleteTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.UpdateTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.entities.Question;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.PricingTier;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.QuestionType;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestType;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestCommandService;
import pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories.TestRepository;

@Service
public class TestCommandServiceImpl implements TestCommandService {

    private final TestRepository testRepository;

    public TestCommandServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    @Transactional
    public Optional<Test> handle(CreateTestCommand command) {
        // Create value objects
        var testType = new TestType(command.testType());

        PricingTier pricingTier;
        if ("FREE".equals(command.pricingTier())) {
            pricingTier = PricingTier.FREE;
        } else {
            pricingTier = PricingTier.paid(command.price());
        }

        // Create and save test with correct parameter order
        var test = new Test(
                command.title(),
                command.description(),
                testType,
                pricingTier,
                command.durationMinutes(),
                command.createdByPsychologistId()
        );

        var savedTest = testRepository.save(test);
        return Optional.of(savedTest);
    }

    @Override
    @Transactional
    public Optional<Test> activateTest(Long testId) {
        Optional<Test> testOpt = testRepository.findById(testId);
        if (testOpt.isEmpty()) {
            throw new IllegalArgumentException("Test not found with ID: " + testId);
        }
        Test test = testOpt.get();
        test.activate();
        Test updatedTest = testRepository.save(test);
        return Optional.of(updatedTest);
    }

    @Override
    @Transactional
    public Optional<Test> deactivateTest(Long testId) {
        Optional<Test> testOpt = testRepository.findById(testId);
        if (testOpt.isEmpty()) {
            throw new IllegalArgumentException("Test not found with ID: " + testId);
        }
        Test test = testOpt.get();
        test.deactivate();
        Test updatedTest = testRepository.save(test);
        return Optional.of(updatedTest);
    }

    @Override
    @Transactional
    public Optional<Test> handle(UpdateTestCommand command) {
        var testOpt = testRepository.findById(command.testId());
        if (testOpt.isEmpty()) {
            throw new IllegalArgumentException("Test not found with ID: " + command.testId());
        }

        var test = testOpt.get();
        test.updateInfo(command.title(), command.description(), command.durationMinutes());

        var updatedTest = testRepository.save(test);
        return Optional.of(updatedTest);
    }

    @Override
    @Transactional
    public void handle(DeleteTestCommand command) {
        if (!testRepository.existsById(command.testId())) {
            throw new IllegalArgumentException("Test not found with ID: " + command.testId());
        }

        testRepository.deleteById(command.testId());
    }

    @Override
    @Transactional
    public Optional<Test> handle(AddQuestionToTestCommand command) {
        var testOpt = testRepository.findById(command.testId());
        if (testOpt.isEmpty()) {
            throw new IllegalArgumentException("Test not found with ID: " + command.testId());
        }

        var test = testOpt.get();
        var questionType = new QuestionType(command.questionType());
        var question = new Question(
                command.questionText(),
                questionType,
                command.order(),
                command.points()
        );

        test.addQuestion(question);
        var updatedTest = testRepository.save(test);
        return Optional.of(updatedTest);
    }
}