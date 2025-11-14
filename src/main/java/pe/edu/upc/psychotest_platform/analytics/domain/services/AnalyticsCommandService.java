package pe.edu.upc.psychotest_platform.analytics.domain.services;

import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdateAnalyticsPreferencesCommand;

import java.util.Optional;

public interface AnalyticsCommandService {

    Optional<Void> handle(UpdateAnalyticsPreferencesCommand command);
}
