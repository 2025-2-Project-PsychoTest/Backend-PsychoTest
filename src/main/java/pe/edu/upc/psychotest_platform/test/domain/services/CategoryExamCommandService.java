package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.commands.SeedCategoriesExamCommand;

public interface CategoryExamCommandService {
    void handle(SeedCategoriesExamCommand command);
}
