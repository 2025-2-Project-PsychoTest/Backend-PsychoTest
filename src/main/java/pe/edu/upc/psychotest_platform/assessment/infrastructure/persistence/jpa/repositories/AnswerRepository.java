package pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories;

import java.util.List;

import pe.edu.upc.psychotest_platform.assessment.domain.model.entities.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for Answer entity.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    /**
     * Find correct answers.
     *
     * @return list of correct answers
     */
    List<Answer> findByIsCorrectTrue();

    /**
     * Find answers by answer text containing keyword.
     *
     * @param keyword the keyword to search
     * @return list of answers
     */
    List<Answer> findByAnswerTextContaining(String keyword);
}