package pe.edu.upc.psychotest_platform.test.domain.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.CategoriesExam;

@Entity
@Table(name = "categories_exam")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With

public class CategoryExam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private CategoriesExam name;


    public CategoryExam(CategoriesExam name) {
        this.name = name;
    }
    public String getStringName() {
        return name.name();
    }
    public static CategoryExam getDefaultCategoryExam() {
        return new CategoryExam(CategoriesExam.PREMIUN);
    }
    public static CategoryExam toCategoryExamFromName(String name) {
        return new CategoryExam(CategoriesExam.valueOf(name));
    }



}
