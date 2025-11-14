package pe.edu.upc.psychotest_platform.analytics.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.analytics.domain.model.commands.UpdateAnalyticsPreferencesCommand;
import pe.edu.upc.psychotest_platform.analytics.domain.services.AnalyticsCommandService;

import java.util.Optional;

@Service
public class AnalyticsCommandServiceImpl implements AnalyticsCommandService {

    @Override
    public Optional<Void> handle(UpdateAnalyticsPreferencesCommand command) {
        // Implementation for updating preferences, e.g., save to database
        // For now, just return empty
        return Optional.empty();
    }
}
