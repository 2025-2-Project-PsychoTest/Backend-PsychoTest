package pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

/**
 * EmailAddress Value Object.
 * Represents a valid email address.
 */
@Embeddable
public record EmailAddress(String address) {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Default constructor for JPA.
     */
    public EmailAddress() {
        this(null);
    }

    /**
     * Constructor with validation.
     */
    public EmailAddress {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new IllegalArgumentException("Invalid email address format");
        }
    }

    /**
     * Get the domain of the email address.
     * @return the domain
     */
    public String getDomain() {
        if (address == null) return null;
        int atIndex = address.indexOf('@');
        return atIndex > 0 ? address.substring(atIndex + 1) : null;
    }
}

