package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Answer;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.CreateAnswerCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.DeleteAnswerCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.UpdateAnswerCommand;

import java.util.Optional;

public interface AnswerCommandService {

    Long handle(Long questionId, CreateAnswerCommand command);
    Optional<Answer> handle(UpdateAnswerCommand command);
    void handle(DeleteAnswerCommand command);
}
