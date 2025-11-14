package pe.edu.upc.psychotest_platform.test.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Answer;
import pe.edu.upc.psychotest_platform.test.domain.model.valueobjects.QuestionId;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(QuestionId questionId);
}
