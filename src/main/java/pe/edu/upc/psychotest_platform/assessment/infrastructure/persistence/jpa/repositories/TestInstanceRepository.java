package pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestStatus;

import java.util.List;
import java.util.Optional;

/**
 * TestInstanceRepository.
 * JPA repository for TestInstance aggregate.
 */
@Repository
public interface TestInstanceRepository extends JpaRepository<TestInstance, Long> {

    /**
     * Find all test instances for a student.
     * @param studentId the student id
     * @return list of test instances
     */
    List<TestInstance> findByStudentId(Long studentId);

    /**
     * Find test instances by student and status.
     * @param studentId the student id
     * @param status the test status
     * @return list of test instances
     */
    List<TestInstance> findByStudentIdAndStatus(Long studentId, TestStatus status);

    /**
     * Find test instances shared with a psychologist.
     * @param psychologistId the psychologist id
     * @return list of shared test instances
     */
    @Query("SELECT ti FROM TestInstance ti WHERE :psychologistId MEMBER OF ti.sharedWithPsychologists")
    List<TestInstance> findSharedWithPsychologist(@Param("psychologistId") Long psychologistId);

    /**
     * Find test instance by student and test.
     * @param studentId the student id
     * @param testId the test id
     * @return optional test instance
     */
    Optional<TestInstance> findByStudentIdAndTestId(Long studentId, Long testId);

    /**
     * Find validated test instances.
     * @param psychologistId the psychologist id who validated
     * @return list of validated test instances
     */
    List<TestInstance> findByValidatedByPsychologistId(Long psychologistId);

    /**
     * Count completed tests for a student.
     * @param studentId the student id
     * @param status the test status
     * @return count of tests
     */
    Long countByStudentIdAndStatus(Long studentId, TestStatus status);
}

