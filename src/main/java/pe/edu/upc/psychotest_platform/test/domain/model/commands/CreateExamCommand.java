package pe.edu.upc.psychotest_platform.test.domain.model.commands;

public record CreateExamCommand(String description, String categoryExam, int rating, int numberQuestions) {
}
