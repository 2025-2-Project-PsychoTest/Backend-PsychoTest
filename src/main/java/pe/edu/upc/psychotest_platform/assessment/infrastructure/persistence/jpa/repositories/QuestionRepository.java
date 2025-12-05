package pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.psychotest_platform.assessment.domain.model.entities.Question;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.QuestionType;

import java.util.List;

/**
 * QuestionRepository.
 * JPA repository for Question entity.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /**
     * Find questions by type.
     * @param questionType the question type
     * @return list of questions
     */
    List<Question> findByQuestionType(QuestionType questionType);

    /**
     * Find questions ordered by order field.
     * @return list of questions ordered
     */
    List<Question> findAllByOrderByOrderAsc();
}

