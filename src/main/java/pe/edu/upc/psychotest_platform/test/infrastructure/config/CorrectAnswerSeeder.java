package pe.edu.upc.psychotest_platform.test.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.SeedCorrectsAnswerCommand;
import pe.edu.upc.psychotest_platform.test.domain.services.CorrectAnswerCommandService;

@Component
public class CorrectAnswerSeeder {
    private final CorrectAnswerCommandService correctAnswerCommandService;
    public CorrectAnswerSeeder(CorrectAnswerCommandService correctAnswerCommandService) {
        this.correctAnswerCommandService = correctAnswerCommandService;
    }

    @PostConstruct
    public  void runSeed() {
        correctAnswerCommandService.handle(new SeedCorrectsAnswerCommand());
    }
}
