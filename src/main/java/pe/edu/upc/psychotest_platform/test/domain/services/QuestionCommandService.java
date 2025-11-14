package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Question;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.CreateQuestionCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.DeleteQuestionCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.UpdateQuestionCommand;

import java.util.Optional;

public interface QuestionCommandService {
    Long handle(Long examId, CreateQuestionCommand command);
    Optional<Question> handle(UpdateQuestionCommand command);
    void handle(DeleteQuestionCommand command);
}
