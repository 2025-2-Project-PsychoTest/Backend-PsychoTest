package pe.edu.upc.psychotest_platform.test.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.CorrectsAnswer;

@Entity
@Table(name = "correct_answer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With

public class CorrectAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private CorrectsAnswer name;


    public CorrectAnswer(CorrectsAnswer name) {
        this.name = name;
    }
    public String getStringName() {
        return name.name();
    }
    public static CorrectAnswer getDefaultCorrectAnswer() {
        return new CorrectAnswer(CorrectsAnswer.A);
    }
    public static CorrectAnswer toCorrectAnswerFromName(String name) {
        return new CorrectAnswer(CorrectsAnswer.valueOf(name));
    }



}