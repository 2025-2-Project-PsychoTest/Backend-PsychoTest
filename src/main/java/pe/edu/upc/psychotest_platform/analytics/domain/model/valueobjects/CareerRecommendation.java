package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * CareerRecommendation Value Object.
 * Represents a career recommendation with match percentage and additional details.
 */
@Embeddable
public record CareerRecommendation(
        String careerName,
        int matchPercentage,
        String description,
        String faculty
) {

    public CareerRecommendation {
        if (careerName == null || careerName.isBlank()) {
            throw new IllegalArgumentException("Career name cannot be null or blank");
        }
        if (matchPercentage < 0 || matchPercentage > 100) {
            throw new IllegalArgumentException("Match percentage must be between 0 and 100");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (faculty == null || faculty.isBlank()) {
            throw new IllegalArgumentException("Faculty cannot be null or blank");
        }
    }
}
