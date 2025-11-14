package pe.edu.upc.psychotest_platform.test.domain.model.commands;

public record CreateAnswerCommand(String correctAnswer, String explication, int points) {
}
