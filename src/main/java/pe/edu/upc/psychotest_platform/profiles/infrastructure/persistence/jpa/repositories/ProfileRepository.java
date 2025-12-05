package pe.edu.upc.psychotest_platform.profiles.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.psychotest_platform.profiles.domain.model.valueobjects.EmailAddress;

import java.util.Optional;

/**
 * ProfileRepository.
 * JPA repository for Profile aggregate.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Find profile by email address.
     * @param email the email address
     * @return the profile if found
     */
    Optional<Profile> findByEmail(EmailAddress email);

    /**
     * Check if profile exists by email.
     * @param email the email address
     * @return true if exists
     */
    boolean existsByEmail(EmailAddress email);
}

