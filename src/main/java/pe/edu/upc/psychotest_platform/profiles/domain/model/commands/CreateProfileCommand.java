package pe.edu.upc.psychotest_platform.profiles.domain.model.commands;

import java.time.LocalDate;

/**
 * CreateProfileCommand.
 * Command to create a new profile.
 */
public record CreateProfileCommand(
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

