package pe.edu.upc.psychotest_platform.analytics.infrastructure.persistence.jpa.repositories;
import pe.edu.upc.psychotest_platform.analytics.domain.model.aggregates.StudentAnalytics;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface StudentAnalyticsRepository extends JpaRepository<StudentAnalytics, Long> {

    Optional<StudentAnalytics> findByUserId(Long userId);
}





