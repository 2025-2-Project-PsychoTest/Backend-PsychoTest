package pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * UpdateProfileResource.
 * Resource for updating a profile.
 */
public record UpdateProfileResource(
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

