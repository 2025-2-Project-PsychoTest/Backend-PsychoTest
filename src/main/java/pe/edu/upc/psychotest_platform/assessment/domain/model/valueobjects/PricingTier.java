package pe.edu.upc.psychotest_platform.assessment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * PricingTier Value Object.
 * Represents the pricing tier of a test (free or paid).
 */
@Embeddable
public record PricingTier(String tier, Double price) {

    /**
     * Default constructor for JPA.
     */
    public PricingTier() {
        this(null, null);
    }

    /**
     * Constructor with validation.
     */
    public PricingTier {
        if (tier == null || tier.isBlank()) {
            throw new IllegalArgumentException("Pricing tier cannot be null or empty");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must be zero or positive");
        }
    }

    /**
     * Predefined pricing tiers.
     */
    public static PricingTier FREE = new PricingTier("FREE", 0.0);

    public static PricingTier paid(Double price) {
        return new PricingTier("PAID", price);
    }

    /**
     * Check if test is free.
     */
    public boolean isFree() {
        return "FREE".equals(tier) && price == 0.0;
    }

    /**
     * Check if test is paid.
     */
    public boolean isPaid() {
        return "PAID".equals(tier) && price > 0.0;
    }
}

