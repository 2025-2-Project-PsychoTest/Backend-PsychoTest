package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * SharedInfoInsight Value Object.
 * Represents insights about information shared by psychologists including posts and engagement metrics.
 */
@Embeddable
public record SharedInfoInsight(
        int postsPublished,
        int totalViews,
        int testsValidated,
        int studentsMonitored
) {

    public SharedInfoInsight {
        if (postsPublished < 0) {
            throw new IllegalArgumentException("Posts published cannot be negative");
        }
        if (totalViews < 0) {
            throw new IllegalArgumentException("Total views cannot be negative");
        }
        if (testsValidated < 0) {
            throw new IllegalArgumentException("Tests validated cannot be negative");
        }
        if (studentsMonitored < 0) {
            throw new IllegalArgumentException("Students monitored cannot be negative");
        }
    }

    /**
     * Default constructor for empty shared info insight.
     */
    public SharedInfoInsight() {
        this(0, 0, 0, 0);
    }

    /**
     * Gets the average views per post.
     * @return average views per post
     */
    public double getAverageViewsPerPost() {
        if (postsPublished == 0) return 0.0;
        return (double) totalViews / postsPublished;
    }
}
