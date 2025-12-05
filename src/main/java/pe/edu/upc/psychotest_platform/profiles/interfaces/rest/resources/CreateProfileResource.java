package pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * CreateProfileResource.
 * Resource for creating a profile.
 */
public record CreateProfileResource(
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

