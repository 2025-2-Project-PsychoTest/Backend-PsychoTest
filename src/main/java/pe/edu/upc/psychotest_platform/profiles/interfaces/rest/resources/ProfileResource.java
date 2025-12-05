package pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * ProfileResource.
 * Resource representing a profile.
 */
public record ProfileResource(
        Long id,
        String firstName,
        String lastName,
        String fullName,
        String documentType,
        String documentNumber,
        LocalDate birthDate,
        Integer age,
        String email,
        String street,
        String city,
        String zipCode,
        String country,
        String fullAddress
) {
}

