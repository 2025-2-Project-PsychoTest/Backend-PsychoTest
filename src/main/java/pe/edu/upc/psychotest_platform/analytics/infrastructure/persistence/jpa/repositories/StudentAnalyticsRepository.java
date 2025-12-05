package pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * StudentAnalyticsRepository.
 * JPA repository for student analytics persistence operations.
 */
@Repository
public interface StudentAnalyticsRepository extends JpaRepository<StudentAnalytics, Long> {

    /**
     * Find student analytics by user ID.
     * @param userId the user ID
     * @return optional student analytics
     */
    Optional<StudentAnalytics> findByUserId(Long userId);

    /**
     * Check if student analytics exists by user ID.
     * @param userId the user ID
     * @return true if exists, false otherwise
     */
    boolean existsByUserId(Long userId);
}





