package pe.edu.upc.psychotest_platform.profiles.domain.services;

import pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.DeleteProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

/**
 * ProfileCommandService.
 * Service interface for profile commands.
 */
public interface ProfileCommandService {

    /**
     * Handle CreateProfileCommand.
     * @param command the create profile command
     * @return the created profile
     */
    Optional<Profile> handle(CreateProfileCommand command);

    /**
     * Handle UpdateProfileCommand.
     * @param command the update profile command
     * @return the updated profile
     */
    Optional<Profile> handle(UpdateProfileCommand command);

    /**
     * Handle DeleteProfileCommand.
     * @param command the delete profile command
     */
    void handle(DeleteProfileCommand command);
}

