package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CareerRecommendation(String careerName, int matchPercentage) {

    public CareerRecommendation {
        if (careerName == null || careerName.isBlank()) {
            throw new IllegalArgumentException("Career name cannot be null or blank");
        }
        if (matchPercentage < 0 || matchPercentage > 100) {
            throw new IllegalArgumentException("Match percentage must be between 0 and 100");
        }
    }
}
