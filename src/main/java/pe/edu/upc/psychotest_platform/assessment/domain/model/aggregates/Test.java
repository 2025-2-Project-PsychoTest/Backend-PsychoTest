package pe.edu.upc.psychotest_platform.assessment.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.assessment.domain.model.entities.Question;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.PricingTier;
import pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects.TestType;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * Test Aggregate Root.
 * Represents a psychological test template in the catalog.
 */
@Entity
@Table(name = "tests")
public class Test extends AuditableAbstractAggregateRoot<Test> {

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 1000)
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "test_type", nullable = false))
    })
    private TestType testType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tier", column = @Column(name = "pricing_tier")),
            @AttributeOverride(name = "price", column = @Column(name = "price"))
    })
    private PricingTier pricingTier;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_by_psychologist_id")
    private Long createdByPsychologistId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    @OrderBy("order ASC")
    private List<Question> questions;

    /**
     * Default constructor for JPA.
     */
    protected Test() {
        this.questions = new ArrayList<>();
        this.isActive = true;
    }

    /**
     * Constructor with parameters.
     */
    public Test(String title, String description, TestType testType, PricingTier pricingTier,
                Integer durationMinutes, Long createdByPsychologistId) {
        this();
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (testType == null) {
            throw new IllegalArgumentException("Test type cannot be null");
        }
        if (pricingTier == null) {
            throw new IllegalArgumentException("Pricing tier cannot be null");
        }
        this.title = title;
        this.description = description;
        this.testType = testType;
        this.pricingTier = pricingTier;
        this.durationMinutes = durationMinutes;
        this.createdByPsychologistId = createdByPsychologistId;
        this.totalQuestions = 0;
    }

    /**
     * Add a question to the test.
     */
    public void addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        this.questions.add(question);
        this.totalQuestions = this.questions.size();
    }

    /**
     * Remove a question from the test.
     */
    public void removeQuestion(Question question) {
        this.questions.remove(question);
        this.totalQuestions = this.questions.size();
    }

    /**
     * Activate the test.
     */
    public void activate() {
        this.isActive = true;
    }

    /**
     * Deactivate the test.
     */
    public void deactivate() {
        this.isActive = false;
    }

    /**
     * Update test information.
     */
    public void updateInfo(String title, String description, Integer durationMinutes) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
        if (description != null) {
            this.description = description;
        }
        if (durationMinutes != null && durationMinutes > 0) {
            this.durationMinutes = durationMinutes;
        }
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TestType getTestType() {
        return testType;
    }

    public PricingTier getPricingTier() {
        return pricingTier;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Long getCreatedByPsychologistId() {
        return createdByPsychologistId;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }
}

