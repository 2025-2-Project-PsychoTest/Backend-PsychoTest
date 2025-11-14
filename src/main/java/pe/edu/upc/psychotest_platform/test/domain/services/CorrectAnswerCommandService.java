package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.commands.SeedCorrectsAnswerCommand;

public interface CorrectAnswerCommandService {
    void handle(SeedCorrectsAnswerCommand command);
}
