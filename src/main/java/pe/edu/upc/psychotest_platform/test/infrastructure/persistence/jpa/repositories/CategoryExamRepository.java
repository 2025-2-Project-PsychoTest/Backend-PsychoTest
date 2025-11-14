package pe.edu.upc.psychotest_platform.test.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.psychotest_platform.test.domain.model.entities.CategoryExam;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.CategoriesExam;

import java.util.Optional;

public interface CategoryExamRepository extends JpaRepository<CategoryExam, Long> {
    boolean existsByName(CategoriesExam name);
    Optional<CategoryExam> findByName(CategoriesExam name);
}
