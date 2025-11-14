package pe.edu.upc.psychotest_platform.test.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Question;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.ExamId;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByExamId(ExamId examId);
}
