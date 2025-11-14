package pe.edu.upc.psychotest_platform.test.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.CreateQuestionCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.ExamId;

@Entity
@Table(name = "questions")
@Getter
public class Question extends AuditableAbstractAggregateRoot {

    @Column(name = "question_text", nullable = false, length = 80)
    private String pregunta;

    @Column(name = "opcion_A", nullable = false, length = 80)
    private String opcionA;

    @Column(name = "opcion_B", nullable = false, length = 80)
    private String opcionB;

    @Column(name = "opcion_C", nullable = false, length = 80)
    private String opcionC;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name= "examId", column = @Column(name = "exam_id", nullable = false))
            }
    )
    private ExamId examId;

    public Question(){}

    public Question(String pregunta, String opcionA, String opcionB, String opcionC, ExamId examId) {
        this.pregunta = pregunta;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.examId = examId;
    }

    public Question(Long examIdParam, CreateQuestionCommand command){
        this.pregunta = command.pregunta();
        this.opcionA = command.opcionA();
        this.opcionB = command.opcionB();
        this.opcionC = command.opcionC();
        this.examId = new ExamId(examIdParam);
    }

    public Question updateInformation(String pregunta, String opcionA, String opcionB, String opcionC, Long examId) {
        this.pregunta = pregunta;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.examId = new ExamId(examId);
        return this;
    }



}
