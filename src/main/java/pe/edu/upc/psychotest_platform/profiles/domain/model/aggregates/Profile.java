package pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.Document;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.PersonName;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.StreetAddress;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;
import java.time.Period;

/**
 * Profile Aggregate Root.
 * Represents a user profile with personal information.
 */
@Entity
@Table(name = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false))
    })
    private PersonName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "documentType", column = @Column(name = "document_type", nullable = false)),
            @AttributeOverride(name = "documentNumber", column = @Column(name = "document_number", nullable = false, unique = true))
    })
    private Document document;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email", nullable = false, unique = true))
    })
    private EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private StreetAddress address;

    /**
     * Default constructor for JPA.
     */
    protected Profile() {
    }

    /**
     * Constructor from CreateProfileCommand.
     * @param command the command containing profile data
     */
    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.document = new Document(command.documentType(), command.documentNumber());
        this.birthDate = command.birthDate();
        this.email = new EmailAddress(command.email());
        this.address = new StreetAddress(command.street(), command.city(), command.zipCode(), command.country());

        validateBirthDate();
        validateAge();
    }

    /**
     * Update profile information.
     */
    public void updateProfile(PersonName name, Document document, LocalDate birthDate,
                              EmailAddress email, StreetAddress address) {
        if (name != null) {
            this.name = name;
        }
        if (document != null) {
            this.document = document;
        }
        if (birthDate != null) {
            this.birthDate = birthDate;
            validateBirthDate();
            validateAge();
        }
        if (email != null) {
            this.email = email;
        }
        if (address != null) {
            this.address = address;
        }
    }

    /**
     * Validate that birth date is in the past.
     */
    private void validateBirthDate() {
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
    }

    /**
     * Validate that age is between 0 and 100.
     */
    private void validateAge() {
        int age = getAge();
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Age must be between 0 and 100 years");
        }
    }

    /**
     * Calculate age from birth date.
     * @return the age in years
     */
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    /**
     * Get the full name.
     * @return the full name
     */
    public String getFullName() {
        return name.getFullName();
    }

    // Getters

    public PersonName getName() {
        return name;
    }

    public Document getDocument() {
        return document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public StreetAddress getAddress() {
        return address;
    }
}

