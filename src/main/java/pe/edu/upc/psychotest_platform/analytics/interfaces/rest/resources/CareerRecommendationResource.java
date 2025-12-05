package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

/**
 * CareerRecommendationResource.
 * Resource representing a career recommendation.
 */
public record CareerRecommendationResource(
        String careerName,
        int matchPercentage,
        String description,
        String faculty
) {
}

