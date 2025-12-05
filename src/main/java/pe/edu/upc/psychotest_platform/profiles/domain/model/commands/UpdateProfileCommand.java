package pe.edu.upc.psychotest_platform.profiles.domain.model.commands;

import java.time.LocalDate;

/**
 * UpdateProfileCommand.
 * Command to update an existing profile.
 */
public record UpdateProfileCommand(
        Long profileId,
        String firstName,
        String lastName,
        String documentType,
        String documentNumber,
        LocalDate birthDate,
        String email,
        String street,
        String city,
        String zipCode,
        String country
) {
}

