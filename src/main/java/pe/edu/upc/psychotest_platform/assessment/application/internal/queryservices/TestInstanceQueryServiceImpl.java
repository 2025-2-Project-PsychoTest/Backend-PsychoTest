package pe.edu.upc.psychotest_platform.assessment.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.TestInstance;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestStatus;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestInstanceQueryService;
import pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories.TestInstanceRepository;

import java.util.List;
import java.util.Optional;

/**
 * TestInstanceQueryServiceImpl.
 * Implementation of TestInstanceQueryService.
 */
@Service
public class TestInstanceQueryServiceImpl implements TestInstanceQueryService {

    private final TestInstanceRepository testInstanceRepository;

    public TestInstanceQueryServiceImpl(TestInstanceRepository testInstanceRepository) {
        this.testInstanceRepository = testInstanceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TestInstance> handle(GetTestInstanceByIdQuery query) {
        return testInstanceRepository.findById(query.testInstanceId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestInstance> handle(GetTestInstancesByStudentIdQuery query) {
        return testInstanceRepository.findByStudentId(query.studentId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestInstance> handle(GetSharedTestsForPsychologistQuery query) {
        return testInstanceRepository.findSharedWithPsychologist(query.psychologistId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestInstance> getCompletedTestsByStudentId(Long studentId) {
        return testInstanceRepository.findByStudentIdAndStatus(studentId, TestStatus.COMPLETED);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestInstance> getInProgressTestsByStudentId(Long studentId) {
        return testInstanceRepository.findByStudentIdAndStatus(studentId, TestStatus.IN_PROGRESS);
    }
}

