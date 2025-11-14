package pe.edu.upc.psychotest_platform.analytics.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SharedInfoInsight(int postsPublished, int totalViews) {

    public SharedInfoInsight {
        if (postsPublished < 0) {
            throw new IllegalArgumentException("Posts published cannot be negative");
        }
        if (totalViews < 0) {
            throw new IllegalArgumentException("Total views cannot be negative");
        }
    }
}
