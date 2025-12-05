package pe.edu.upc.psychotest_platform.assessment.domain.services;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.*;

import java.util.Optional;

/**
 * TestInstanceCommandService.
 * Service interface for test instance commands.
 */
public interface TestInstanceCommandService {

    /**
     * Handle StartTestInstanceCommand.
     * @param command the start test command
     * @return the created test instance
     */
    Optional<TestInstance> handle(StartTestInstanceCommand command);

    /**
     * Handle SubmitAnswerCommand.
     * @param command the submit answer command
     * @return the updated test instance
     */
    Optional<TestInstance> handle(SubmitAnswerCommand command);

    /**
     * Handle CompleteTestInstanceCommand.
     * @param command the complete test command
     * @return the completed test instance
     */
    Optional<TestInstance> handle(CompleteTestInstanceCommand command);

    /**
     * Handle ShareTestWithPsychologistCommand.
     * @param command the share test command
     * @return the updated test instance
     */
    Optional<TestInstance> handle(ShareTestWithPsychologistCommand command);

    /**
     * Handle ValidateTestCommand.
     * @param command the validate test command
     * @return the validated test instance
     */
    Optional<TestInstance> handle(ValidateTestCommand command);
}

