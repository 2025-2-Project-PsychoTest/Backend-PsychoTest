package pe.edu.upc.psychotest_platform.test.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.CreateAnswerCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.entities.CorrectAnswer;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.QuestionId;

@Entity
@Table(name = "answers")
@Getter
public class Answer extends AuditableAbstractAggregateRoot {

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "correct_answer_id", nullable = false)
    private CorrectAnswer correctAnswer;

    @Column(name = "explication", nullable = false, length = 80)
    private String explication;

    @Column(name = "points", nullable = false)
    private int points;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name= "questionId", column = @Column(name = "question_id", nullable = false))
            }
    )
    private QuestionId questionId;

    protected Answer(){}

    public Answer(CorrectAnswer correctAnswer, String explication, int points, QuestionId questionId) {
        this.correctAnswer = correctAnswer;
        this.explication = explication;
        this.points = points;
        this.questionId =questionId;
    }

    public Answer(Long questionIdParam, CreateAnswerCommand command){
        this.correctAnswer = CorrectAnswer.toCorrectAnswerFromName(command.correctAnswer());
        this.explication = command.explication();
        this.points = command.points();
        this.questionId = new QuestionId(questionIdParam);
    }

    public Answer updateInformation(CorrectAnswer correctAnswer, String explication, int points, Long questionId) {
        this.correctAnswer=correctAnswer;
        this.explication = explication;
        this.points = points;
        this.questionId = new QuestionId(questionId);
        return this;
    }


}
