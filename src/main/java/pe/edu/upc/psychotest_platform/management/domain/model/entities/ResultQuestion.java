package pe.edu.upc.psychotest_platform.management.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.psychotest_platform.management.domain.model.valueobjects.Opcion;
import pe.edu.upc.psychotest_platform.shared.domain.model.entities.AuditableModel;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "result_questions")
@NoArgsConstructor
@Getter
public class ResultQuestion extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //
    private UUID id;

    @Column(nullable = false, length = 500)
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "result_question_options", joinColumns = @JoinColumn(name = "result_question_id"))
    private List<Opcion> options;

    public ResultQuestion(String text, List<Opcion> selectedOptions) {
        this.text = text;
        this.options = selectedOptions;
    }
}