package pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * PersonName Value Object.
 * Represents a person's full name.
 */
@Embeddable
public record PersonName(
        String firstName,
        String lastName
) {
    /**
     * Default constructor for JPA.
     */
    public PersonName() {
        this(null, null);
    }

    /**
     * Constructor with validation.
     */
    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
    }

    /**
     * Get the full name.
     * @return the full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}

