package pe.edu.upc.psychotest_platform.profiles.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.services.ProfileQueryService;

/**
 * ProfilesContextFacade.
 * Anti-Corruption Layer facade for Profiles context.
 * Provides a simplified interface for other bounded contexts to interact with Profiles.
 */
@Service
public class ProfilesContextFacade {

    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileQueryService profileQueryService) {
        this.profileQueryService = profileQueryService;
    }

    /**
     * Check if a profile exists by its ID.
     * @param profileId the profile id
     * @return true if profile exists, false otherwise
     */
    public boolean existsProfileById(Long profileId) {
        if (profileId == null || profileId <= 0) {
            return false;
        }
        var query = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(query);
        return profile.isPresent();
    }

    /**
     * Get profile full name by ID.
     * @param profileId the profile id
     * @return the full name if profile exists, null otherwise
     */
    public String getProfileFullNameById(Long profileId) {
        if (profileId == null || profileId <= 0) {
            return null;
        }
        var query = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(query);
        return profile.map(p -> p.getFullName()).orElse(null);
    }

    /**
     * Get profile email by ID.
     * @param profileId the profile id
     * @return the email if profile exists, null otherwise
     */
    public String getProfileEmailById(Long profileId) {
        if (profileId == null || profileId <= 0) {
            return null;
        }
        var query = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(query);
        return profile.map(p -> p.getEmail().address()).orElse(null);
    }
}

