package pe.edu.upc.psychotest_platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.DeleteProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.UpdateProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.Document;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.PersonName;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.StreetAddress;
import pe.edu.upc.psychotest_platform.profiles.domain.services.ProfileCommandService;
import pe.edu.upc.psychotest_platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

/**
 * ProfileCommandServiceImpl.
 * Implementation of ProfileCommandService.
 */
@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public Optional<Profile> handle(CreateProfileCommand command) {
        // Validate unique email
        var email = new EmailAddress(command.email());
        if (profileRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("A profile with this email already exists");
        }

        // Create and save profile
        var profile = new Profile(command);
        var savedProfile = profileRepository.save(profile);
        return Optional.of(savedProfile);
    }

    @Override
    @Transactional
    public Optional<Profile> handle(UpdateProfileCommand command) {
        // Find existing profile
        var profileOpt = profileRepository.findById(command.profileId());
        if (profileOpt.isEmpty()) {
            throw new IllegalArgumentException("Profile not found with ID: " + command.profileId());
        }

        var profile = profileOpt.get();

        // Check email uniqueness if changed
        var newEmail = new EmailAddress(command.email());
        if (!profile.getEmail().equals(newEmail)) {
            if (profileRepository.existsByEmail(newEmail)) {
                throw new IllegalArgumentException("A profile with this email already exists");
            }
        }

        // Update profile
        var name = new PersonName(command.firstName(), command.lastName());
        var document = new Document(command.documentType(), command.documentNumber());
        var address = new StreetAddress(command.street(), command.city(), command.zipCode(), command.country());

        profile.updateProfile(name, document, command.birthDate(), newEmail, address);

        var updatedProfile = profileRepository.save(profile);
        return Optional.of(updatedProfile);
    }

    @Override
    @Transactional
    public void handle(DeleteProfileCommand command) {
        // Verify profile exists
        if (!profileRepository.existsById(command.profileId())) {
            throw new IllegalArgumentException("Profile not found with ID: " + command.profileId());
        }

        // Delete profile
        profileRepository.deleteById(command.profileId());
    }
}


