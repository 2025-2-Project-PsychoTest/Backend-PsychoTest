package pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestType;

/**
 * JPA repository for Test aggregate.
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    /**
     * Find active tests by type.
     *
     * @param testType the test type
     * @return list of active tests of that type
     */
    List<Test> findByIsActiveTrueAndTestType(TestType testType);

    /**
     * Find tests by pricing tier.
     *
     * @param tier the pricing tier name
     * @return list of tests with the given pricing tier
     */
    List<Test> findByPricingTier_Tier(String tier);

    /**
     * Find tests created by a psychologist.
     *
     * @param psychologistId the psychologist id
     * @return list of tests created by the psychologist
     */
    List<Test> findByCreatedByPsychologistId(Long psychologistId);

    /**
     * Find tests by type.
     *
     * @param testType the test type
     * @return list of tests of that type
     */
    List<Test> findByTestType(TestType testType);

    /**
     * Find all active tests.
     *
     * @return list of active tests
     */
    List<Test> findByIsActiveTrue();
}