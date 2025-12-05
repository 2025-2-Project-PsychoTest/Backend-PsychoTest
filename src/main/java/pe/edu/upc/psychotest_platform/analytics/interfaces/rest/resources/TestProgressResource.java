package pe.edu.upc.psychotest_platform.analytics.interfaces.rest.resources;

        /**
         * Recurso que representa el progreso de pruebas.
         */
        public record TestProgressResource(
            double completionPercentage,
            double inProgressTests,
            int totalTests,
            double averageScore,
            double completedTests
        ) {}
