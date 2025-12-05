package pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PsychologistAnalyticsRepository.
 * JPA repository for psychologist analytics persistence operations.
 */
@Repository
public interface PsychologistAnalyticsRepository extends JpaRepository<PsychologistAnalytics, Long> {

    /**
     * Find psychologist analytics by user ID.
     * @param userId the user ID
     * @return optional psychologist analytics
     */
    Optional<PsychologistAnalytics> findByUserId(Long userId);

    /**
     * Check if psychologist analytics exists by user ID.
     * @param userId the user ID
     * @return true if exists, false otherwise
     */
    boolean existsByUserId(Long userId);
}
