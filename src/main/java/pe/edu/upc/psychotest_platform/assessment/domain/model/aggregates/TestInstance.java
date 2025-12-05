package pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestStatus;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TestInstance Aggregate Root.
 * Represents an instance of a test being taken by a student.
 */
@Entity
@Table(name = "test_instances")
public class TestInstance extends AuditableAbstractAggregateRoot<TestInstance> {

    @Column(name = "test_id", nullable = false)
    private Long testId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "status", column = @Column(name = "status", nullable = false))
    })
    private TestStatus status;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "completion_time")
    private LocalDateTime completionTime;

    @Column(name = "score")
    private Double score;

    @Column(name = "max_score")
    private Double maxScore;

    @ElementCollection
    @CollectionTable(name = "test_instance_answers", joinColumns = @JoinColumn(name = "test_instance_id"))
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer_id")
    private Map<Long, Long> answers;

    @ElementCollection
    @CollectionTable(name = "test_instance_shared_with", joinColumns = @JoinColumn(name = "test_instance_id"))
    @Column(name = "psychologist_id")
    private Set<Long> sharedWithPsychologists;

    @Column(name = "validated_by_psychologist_id")
    private Long validatedByPsychologistId;

    @Column(name = "validation_notes", length = 2000)
    private String validationNotes;

    /**
     * Default constructor for JPA.
     */
    protected TestInstance() {
        this.answers = new HashMap<>();
        this.sharedWithPsychologists = new HashSet<>();
        this.status = TestStatus.NOT_STARTED;
    }

    /**
     * Constructor with parameters.
     */
    public TestInstance(Long testId, Long studentId) {
        this();
        if (testId == null || testId <= 0) {
            throw new IllegalArgumentException("Test ID must be valid");
        }
        if (studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be valid");
        }
        this.testId = testId;
        this.studentId = studentId;
    }

    /**
     * Start the test.
     */
    public void start() {
        if (this.status.isCompleted()) {
            throw new IllegalStateException("Cannot start a completed test");
        }
        this.status = TestStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }

    /**
     * Submit an answer to a question.
     */
    public void submitAnswer(Long questionId, Long answerId) {
        if (questionId == null || answerId == null) {
            throw new IllegalArgumentException("Question ID and Answer ID cannot be null");
        }
        if (!TestStatus.IN_PROGRESS.equals(this.status)) {
            throw new IllegalStateException("Can only submit answers when test is in progress");
        }
        this.answers.put(questionId, answerId);
    }

    /**
     * Complete the test.
     */
    public void complete(Double score, Double maxScore) {
        if (!TestStatus.IN_PROGRESS.equals(this.status)) {
            throw new IllegalStateException("Can only complete a test that is in progress");
        }
        this.status = TestStatus.COMPLETED;
        this.completionTime = LocalDateTime.now();
        this.score = score;
        this.maxScore = maxScore;
    }

    /**
     * Share test with a psychologist.
     */
    public void shareWithPsychologist(Long psychologistId) {
        if (psychologistId == null || psychologistId <= 0) {
            throw new IllegalArgumentException("Psychologist ID must be valid");
        }
        if (!this.status.isCompleted()) {
            throw new IllegalStateException("Can only share completed tests");
        }
        this.sharedWithPsychologists.add(psychologistId);
        if (this.status.equals(TestStatus.COMPLETED)) {
            this.status = TestStatus.SHARED;
        }
    }

    /**
     * Validate test by psychologist.
     */
    public void validateByPsychologist(Long psychologistId, String notes) {
        if (psychologistId == null || psychologistId <= 0) {
            throw new IllegalArgumentException("Psychologist ID must be valid");
        }
        if (!this.status.isCompleted()) {
            throw new IllegalStateException("Can only validate completed tests");
        }
        this.status = TestStatus.VALIDATED;
        this.validatedByPsychologistId = psychologistId;
        this.validationNotes = notes;
    }

    /**
     * Calculate progress percentage.
     */
    public Double getProgressPercentage(Integer totalQuestions) {
        if (totalQuestions == null || totalQuestions == 0) {
            return 0.0;
        }
        return (double) this.answers.size() / totalQuestions * 100.0;
    }

    /**
     * Get score percentage.
     */
    public Double getScorePercentage() {
        if (maxScore == null || maxScore == 0 || score == null) {
            return 0.0;
        }
        return (score / maxScore) * 100.0;
    }

    // Getters

    public Long getTestId() {
        return testId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public TestStatus getStatus() {
        return status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public Double getScore() {
        return score;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public Map<Long, Long> getAnswers() {
        return new HashMap<>(answers);
    }

    public Set<Long> getSharedWithPsychologists() {
        return new HashSet<>(sharedWithPsychologists);
    }

    public Long getValidatedByPsychologistId() {
        return validatedByPsychologistId;
    }

    public String getValidationNotes() {
        return validationNotes;
    }
}

