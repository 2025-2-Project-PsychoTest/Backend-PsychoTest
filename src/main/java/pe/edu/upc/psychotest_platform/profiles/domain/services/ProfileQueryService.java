package pe.edu.upc.psychotest_platform.profiles.domain.services;

import pe.edu.upc.psychotest_platform.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * ProfileQueryService.
 * Service interface for profile queries.
 */
public interface ProfileQueryService {

    /**
     * Handle GetAllProfilesQuery.
     * @param query the query
     * @return list of all profiles
     */
    List<Profile> handle(GetAllProfilesQuery query);

    /**
     * Handle GetProfileByIdQuery.
     * @param query the query
     * @return the profile if found
     */
    Optional<Profile> handle(GetProfileByIdQuery query);

    /**
     * Handle GetProfileByEmailQuery.
     * @param query the query
     * @return the profile if found
     */
    Optional<Profile> handle(GetProfileByEmailQuery query);
}

