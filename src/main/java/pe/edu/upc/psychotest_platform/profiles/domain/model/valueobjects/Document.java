package pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Document Value Object.
 * Represents an identification document.
 */
@Embeddable
public record Document(
        String documentType,
        String documentNumber
) {
    /**
     * Default constructor for JPA.
     */
    public Document() {
        this(null, null);
    }

    /**
     * Constructor with validation.
     */
    public Document {
        if (documentType == null || documentType.isBlank()) {
            throw new IllegalArgumentException("Document type cannot be null or empty");
        }
        if (documentNumber == null || documentNumber.isBlank()) {
            throw new IllegalArgumentException("Document number cannot be null or empty");
        }
    }

    /**
     * Check if the document is a DNI.
     * @return true if the document is a DNI
     */
    public boolean isDNI() {
        return "DNI".equalsIgnoreCase(documentType);
    }

    /**
     * Check if the document is a passport.
     * @return true if the document is a passport
     */
    public boolean isPassport() {
        return "PASSPORT".equalsIgnoreCase(documentType);
    }
}

