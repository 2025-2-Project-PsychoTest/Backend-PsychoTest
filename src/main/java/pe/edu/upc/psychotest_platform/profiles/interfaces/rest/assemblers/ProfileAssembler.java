package pe.edu.upc.psychotest_platform.profiles.interfaces.rest.assemblers;

import pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.UpdateProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources.CreateProfileResource;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources.ProfileResource;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources.UpdateProfileResource;

/**
 * ProfileAssembler.
 * Assembler to convert between Profile entity and resources.
 */
public class ProfileAssembler {

    /**
     * Convert Profile entity to ProfileResource.
     * @param entity the profile entity
     * @return the profile resource
     */
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getName().firstName(),
                entity.getName().lastName(),
                entity.getFullName(),
                entity.getDocument().documentType(),
                entity.getDocument().documentNumber(),
                entity.getBirthDate(),
                entity.getAge(),
                entity.getEmail().address(),
                entity.getAddress().street(),
                entity.getAddress().city(),
                entity.getAddress().zipCode(),
                entity.getAddress().country(),
                entity.getAddress().getFullAddress()
        );
    }

    /**
     * Convert CreateProfileResource to CreateProfileCommand.
     * @param resource the create profile resource
     * @return the create profile command
     */
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.documentType(),
                resource.documentNumber(),
                resource.birthDate(),
                resource.email(),
                resource.street(),
                resource.city(),
                resource.zipCode(),
                resource.country()
        );
    }

    /**
     * Convert UpdateProfileResource to UpdateProfileCommand.
     * @param profileId the profile id
     * @param resource the update profile resource
     * @return the update profile command
     */
    public static UpdateProfileCommand toCommandFromResource(Long profileId, UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                profileId,
                resource.firstName(),
                resource.lastName(),
                resource.documentType(),
                resource.documentNumber(),
                resource.birthDate(),
                resource.email(),
                resource.street(),
                resource.city(),
                resource.zipCode(),
                resource.country()
        );
    }
}

