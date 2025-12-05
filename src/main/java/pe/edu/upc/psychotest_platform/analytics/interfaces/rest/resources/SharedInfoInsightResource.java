package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

/**
 * SharedInfoInsightResource.
 * Resource representing shared information insights.
 */
public record SharedInfoInsightResource(
        int postsPublished,
        int totalViews,
        int testsValidated,
        int studentsMonitored,
        double averageViewsPerPost
) {
}

