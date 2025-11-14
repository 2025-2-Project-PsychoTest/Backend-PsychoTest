package pe.edu.upc.psychotest_platform.management.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.psychotest_platform.management.domain.model.valueobjects.Opcion;
import pe.edu.upc.psychotest_platform.shared.domain.model.entities.AuditableModel;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@Getter
public class Question extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 500) // text
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    private List<Opcion> options;

    public Question(String text, List<Opcion> options) {
        this.text = text;
        this.options = options;
    }
}