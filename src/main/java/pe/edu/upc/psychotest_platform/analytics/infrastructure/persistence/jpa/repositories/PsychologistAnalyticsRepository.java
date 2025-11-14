package pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.PsychologistAnalytics;

import java.util.Optional;

@Repository
public interface PsychologistAnalyticsRepository extends JpaRepository<PsychologistAnalytics, Long> {

    Optional<PsychologistAnalytics> findByUserId(Long userId);
}
