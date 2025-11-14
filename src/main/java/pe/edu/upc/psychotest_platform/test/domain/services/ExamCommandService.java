package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.CreateExamCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.UpdateExamCommand;

import java.util.Optional;

public interface ExamCommandService {
    Long handle(CreateExamCommand command);
    Optional<Exam> handle(UpdateExamCommand command);
    void handle(DeleteExamCommand command);
}
