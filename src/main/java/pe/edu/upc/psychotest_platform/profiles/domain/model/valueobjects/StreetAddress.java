package pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * StreetAddress Value Object.
 * Represents a complete street address.
 */
@Embeddable
public record StreetAddress(
        String street,
        String city,
        String zipCode,
        String country
) {
    /**
     * Default constructor for JPA.
     */
    public StreetAddress() {
        this(null, null, null, null);
    }

    /**
     * Constructor with validation.
     */
    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or empty");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
    }

    /**
     * Get the full address as a string.
     * @return the full address
     */
    public String getFullAddress() {
        return String.format("%s, %s, %s, %s", street, city, zipCode, country);
    }
}

