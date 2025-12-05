package pe.edu.upc.psychotest_platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.psychotest_platform.profiles.domain.services.ProfileQueryService;
import pe.edu.upc.psychotest_platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

/**
 * ProfileQueryServiceImpl.
 * Implementation of ProfileQueryService.
 */
@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        var email = new EmailAddress(query.email());
        return profileRepository.findByEmail(email);
    }
}

