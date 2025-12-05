package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.assemblers;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.*;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.*;

/**
 * TestInstanceAssembler.
 * Assembler to convert between TestInstance entity and resources.
 */
public class TestInstanceAssembler {

    /**
     * Convert TestInstance entity to TestInstanceResource.
     * @param entity the test instance entity
     * @return the test instance resource
     */
    public static TestInstanceResource toResourceFromEntity(TestInstance entity) {
        return new TestInstanceResource(
                entity.getId(),
                entity.getTestId(),
                entity.getStudentId(),
                entity.getStatus().status(),
                entity.getStartTime(),
                entity.getCompletionTime(),
                entity.getScore(),
                entity.getMaxScore(),
                entity.getScorePercentage(),
                entity.getAnswers(),
                entity.getSharedWithPsychologists(),
                entity.getValidatedByPsychologistId(),
                entity.getValidationNotes()
        );
    }

    /**
     * Convert StartTestResource to StartTestInstanceCommand.
     * @param resource the start test resource
     * @return the start test instance command
     */
    public static StartTestInstanceCommand toCommandFromResource(StartTestResource resource) {
        return new StartTestInstanceCommand(
                resource.testId(),
                resource.studentId()
        );
    }

    /**
     * Convert SubmitAnswerResource to SubmitAnswerCommand.
     * @param testInstanceId the test instance id
     * @param resource the submit answer resource
     * @return the submit answer command
     */
    public static SubmitAnswerCommand toCommandFromResource(Long testInstanceId, SubmitAnswerResource resource) {
        return new SubmitAnswerCommand(
                testInstanceId,
                resource.questionId(),
                resource.answerId()
        );
    }

    /**
     * Convert testInstanceId to CompleteTestInstanceCommand.
     * @param testInstanceId the test instance id
     * @return the complete test instance command
     */
    public static CompleteTestInstanceCommand toCompleteCommand(Long testInstanceId) {
        return new CompleteTestInstanceCommand(testInstanceId);
    }

    /**
     * Convert ShareTestResource to ShareTestWithPsychologistCommand.
     * @param testInstanceId the test instance id
     * @param resource the share test resource
     * @return the share test with psychologist command
     */
    public static ShareTestWithPsychologistCommand toCommandFromResource(Long testInstanceId, ShareTestResource resource) {
        return new ShareTestWithPsychologistCommand(
                testInstanceId,
                resource.psychologistId()
        );
    }

    /**
     * Convert ValidateTestResource to ValidateTestCommand.
     * @param testInstanceId the test instance id
     * @param resource the validate test resource
     * @return the validate test command
     */
    public static ValidateTestCommand toCommandFromResource(Long testInstanceId, ValidateTestResource resource) {
        return new ValidateTestCommand(
                testInstanceId,
                resource.psychologistId(),
                resource.validationNotes()
        );
    }
}

