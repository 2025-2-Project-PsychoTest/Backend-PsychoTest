package pe.edu.upc.psychotest_platform.assessment.domain.services;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.AddQuestionToTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.CreateTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.DeleteTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.UpdateTestCommand;

import java.util.Optional;

/**
 * TestCommandService.
 * Service interface for test commands.
 */
public interface TestCommandService {

    /**
     * Handle CreateTestCommand.
     * @param command the create test command
     * @return the created test
     */
    Optional<Test> handle(CreateTestCommand command);

    /**
     * Handle UpdateTestCommand.
     * @param command the update test command
     * @return the updated test
     */
    Optional<Test> handle(UpdateTestCommand command);

    /**
     * Handle DeleteTestCommand.
     * @param command the delete test command
     */
    void handle(DeleteTestCommand command);

    /**
     * Handle AddQuestionToTestCommand.
     * @param command the add question command
     * @return the updated test
     */
    Optional<Test> handle(AddQuestionToTestCommand command);

    /**
     * Activate a test.
     * @param testId the test id
     * @return the updated test
     */
    Optional<Test> activateTest(Long testId);

    /**
     * Deactivate a test.
     * @param testId the test id
     * @return the updated test
     */
    Optional<Test> deactivateTest(Long testId);
}

