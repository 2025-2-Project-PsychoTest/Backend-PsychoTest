package pe.edu.upc.psychotest_platform.test.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.psychotest_platform.test.domain.model.commands.CreateExamCommand;
import pe.edu.upc.psychotest_platform.test.domain.model.entities.CategoryExam;


@Entity
@Table(name ="exams")
@Getter
public class Exam extends AuditableAbstractAggregateRoot<Exam> {

    //String description, String categoryExam, int rating, int numberQuestions

    @Column(name = "exam_name", nullable = false, length = 80)
    private String description;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_exam_id", nullable = false)
    private CategoryExam categoryExam;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "number_questions", nullable = false)
    private int numberQuestions;



    protected Exam(){}


    public Exam(String description, CategoryExam categoryExam, int rating, int numberQuestions) {
        this.description = description;
        this.categoryExam = categoryExam;
        this.rating = rating;
        this.numberQuestions = numberQuestions;
    }

    public Exam(CreateExamCommand command) {
        this.description = command.description();
        this.categoryExam = CategoryExam.toCategoryExamFromName(command.categoryExam());
        this.rating = command.rating();
        this.numberQuestions = command.numberQuestions();

    }

    public Exam updateInformation(String description, CategoryExam categoryExam, int rating, int numberQuestions) {
        this.description = description;
        this.categoryExam = categoryExam;
        this.rating = rating;
        this.numberQuestions = numberQuestions;
        return this;
    }


}
