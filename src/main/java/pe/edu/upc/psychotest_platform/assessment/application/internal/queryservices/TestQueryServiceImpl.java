package pe.edu.upc.psychotest_platform.assessment.application.internal.queryservices;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.psychotest_platform.assessment.infrastructure.persistence.jpa.repositories.TestRepository;
import pe.edu.upc.psychotest_platform.assessment.domain.services.TestQueryService;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestType;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetTestByIdQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.queries.GetAllTestsQuery;
import pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates.Test;

@Service
public class TestQueryServiceImpl implements TestQueryService {

    private final TestRepository testRepository;

    public TestQueryServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getFreeTests() {
        return testRepository.findByPricingTier_Tier("FREE");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByType(String testType) {
        TestType type = new TestType(testType);
        return testRepository.findByTestType(type);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getAllActiveTests() {
        return testRepository.findByIsActiveTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Test> handle(GetTestByIdQuery query) {
        return testRepository.findById(query.testId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> handle(GetAllTestsQuery query) {
        return testRepository.findAll();
    }
}