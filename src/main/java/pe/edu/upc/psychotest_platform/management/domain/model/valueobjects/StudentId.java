package pe.edu.upc.psychotest_platform.management.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public record StudentId(
        @Column(name = "student_id") UUID studentId
) {
    // Constructor
    public StudentId {
        if (studentId == null) {
            throw new IllegalArgumentException("Student UUID cannot be null");
        }
    }
}