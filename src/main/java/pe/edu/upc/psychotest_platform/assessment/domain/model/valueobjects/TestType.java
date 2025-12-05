package pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Represents the type/category of a psychological test.
 * TestType Value Object.
 */
@Embeddable
public record TestType(String type) {

    public static final TestType CAREER_GUIDANCE = new TestType("CAREER_GUIDANCE");
    public static final TestType INTEREST = new TestType("INTEREST");
    public static final TestType APTITUDE = new TestType("APTITUDE");
    public static final TestType PERSONALITY = new TestType("PERSONALITY");
    public static final TestType VOCATIONAL = new TestType("VOCATIONAL");

    /**
     * Constructor with validation.
     * Allows a null value so JPA can instantiate without triggering validation.
     */
    public TestType {
        if (type != null && type.isBlank()) {
            throw new IllegalArgumentException("Test type cannot be null or empty");
        }
        if (type != null) {
            type = type.trim();
        }
    }

    /**
     * Default constructor for JPA.
     */
    public TestType() {
        this((String) null);
    }

    @Override
    public String toString() {
        return type;
    }
}