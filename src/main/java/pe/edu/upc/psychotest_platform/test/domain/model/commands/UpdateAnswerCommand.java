package pe.edu.upc.psychotest_platform.test.domain.model.commands;

public record UpdateAnswerCommand (Long answerId, String correctAnswer,
                                   String explication, int points,
                                   Long questionId){
}
