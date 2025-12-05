package pe.edu.upc.psychotest_platform.assessment.interfaces.rest.assemblers;

import java.util.stream.Collectors;
import java.util.List;

import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.TestSummaryResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.TestResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.QuestionResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.CreateTestResource;
import pe.edu.upc.psychotest_platform.assessment.domain.model.entities.Question;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.CreateTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.UpdateTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.commands.AddQuestionToTestCommand;
import pe.edu.upc.psychotest_platform.assessment.domain.model.entities.Answer;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.AddQuestionResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.AnswerResource;
import pe.edu.upc.psychotest_platform.assessment.interfaces.rest.resources.UpdateTestResource;

public class TestAssembler {

    /**
     * Convert list of Question entities to list of QuestionResource.
     *
     * @param entities the question entities
     * @return the question resources
     */
    public static List<QuestionResource> toQuestionResourceList(List<Question> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(TestAssembler::toQuestionResource)
                .collect(Collectors.toList());
    }

    /**
     * Convert Question entity to QuestionResource.
     *
     * @param entity the question entity
     * @return the question resource
     */
    public static QuestionResource toQuestionResource(Question entity) {
        if (entity == null) {
            return null;
        }
        // Ajustar el orden de parámetros si el constructor de QuestionResource es distinto
        return new QuestionResource(
                entity.getId(),
                entity.getQuestionText(),
                entity.getQuestionType() != null ? entity.getQuestionType().type() : null,
                entity.getOrder(),
                entity.getPoints()
        );
    }

    /**
     * Convert CreateTestResource to CreateTestCommand.
     *
     * @param resource the create test resource
     * @return the create test command
     */
    public static CreateTestCommand toCommandFromResource(CreateTestResource resource) {
        if (resource == null) {
            return null;
        }
        return new CreateTestCommand(
                resource.createdByPsychologistId(),
                resource.durationMinutes(),
                resource.price(),
                resource.pricingTier(),
                resource.testType(),
                resource.description(),
                resource.title()
        );
    }

    /**
     * Convert Test entity to TestSummaryResource.
     *
     * @param entity the test entity
     * @return the test summary resource
     */
    public static TestSummaryResource toSummaryResourceFromEntity(Test entity) {
        if (entity == null) {
            return null;
        }
        return new TestSummaryResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTestType() != null ? entity.getTestType().type() : null,
                entity.getPricingTier() != null ? entity.getPricingTier().tier() : null,
                entity.getPricingTier() != null ? entity.getPricingTier().price() : null,
                entity.getDurationMinutes(),
                entity.getTotalQuestions(),
                entity.getIsActive()
        );
    }

    /**
     * Convert Test entity to TestResource.
     *
     * @param entity the test entity
     * @return the test resource
     */
    public static TestResource toResourceFromEntity(Test entity) {
        if (entity == null) {
            return null;
        }
        // TestResource order: Long id, String title, String description, String testType,
        // String pricingTier, Double price, Integer durationMinutes, Integer totalQuestions,
        // Boolean isActive, Long createdByPsychologistId, List<QuestionResource> questions
        return new TestResource(
                entity.getId(),                                                     // Long id
                entity.getTitle(),                                                  // String title
                entity.getDescription(),                                            // String description
                entity.getTestType() != null ? entity.getTestType().type() : null, // String testType
                entity.getPricingTier() != null ? entity.getPricingTier().tier() : null, // String pricingTier
                entity.getPricingTier() != null ? entity.getPricingTier().price() : null, // Double price
                entity.getDurationMinutes(),                                        // Integer durationMinutes
                entity.getTotalQuestions(),                                         // Integer totalQuestions
                entity.getIsActive(),                                               // Boolean isActive
                entity.getCreatedByPsychologistId(),                               // Long createdByPsychologistId
                toQuestionResourceList(entity.getQuestions())                      // List<QuestionResource> questions
        );
    }

    /**
     * Convert UpdateTestResource to UpdateTestCommand.
     * @param testId the test id
     * @param resource the update test resource
     * @return the update test command
     */
    public static UpdateTestCommand toCommandFromResource(Long testId, UpdateTestResource resource) {
        return new UpdateTestCommand(
                testId,
                resource.title(),
                resource.description(),
                resource.durationMinutes()
        );
    }

    /**
     * Convert AddQuestionResource to AddQuestionToTestCommand.
     * @param testId the test id
     * @param resource the add question resource
     * @return the add question command
     */
    public static AddQuestionToTestCommand toCommandFromResource(Long testId, AddQuestionResource resource) {
        return new AddQuestionToTestCommand(
                testId,
                resource.questionText(),
                resource.questionType(),
                resource.order(),
                resource.points()
        );
    }

    /**
     * Convert Answer entity to AnswerResource.
     * @param entity the answer entity
     * @return the answer resource
     */
    public static AnswerResource toAnswerResource(Answer entity) {
        if (entity == null) {
            return null;
        }
        // AnswerResource order: Integer value, Boolean isCorrect, String answerText, Long id
        return new AnswerResource(
                entity.getValue(),      // Integer
                entity.getIsCorrect(),  // Boolean
                entity.getAnswerText(), // String
                entity.getId()          // Long
        );
    }

    /**
     * Convert list of Answer entities to list of AnswerResource.
     * @param entities the answer entities
     * @return the answer resources
     */
    public static List<AnswerResource> toAnswerResourceList(List<Answer> entities) {
        return entities.stream()
                .map(TestAssembler::toAnswerResource)
                .collect(Collectors.toList());
    }
}