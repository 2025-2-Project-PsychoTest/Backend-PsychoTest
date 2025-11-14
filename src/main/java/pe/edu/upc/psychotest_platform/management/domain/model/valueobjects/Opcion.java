package pe.edu.upc.psychotest_platform.management.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Opcion(
        String optionText,
        boolean isCorrect,
        boolean isSelected
) {
    // Constructor
    public static Opcion forQuestion(String optionText, boolean isCorrect) {
        return new Opcion(optionText, isCorrect, false);
    }

    // Constructor
    public static Opcion forResult(String optionText, boolean isCorrect, boolean isSelected) {
        return new Opcion(optionText, isCorrect, isSelected);
    }
}