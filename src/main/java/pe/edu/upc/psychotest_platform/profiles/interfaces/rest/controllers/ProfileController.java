package pe.edu.upc.psychotest_platform.profiles.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.psychotest_platform.profiles.domain.model.commands.DeleteProfileCommand;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.edu.upc.psychotest_platform.profiles.domain.services.ProfileCommandService;
import pe.edu.upc.psychotest_platform.profiles.domain.services.ProfileQueryService;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.assemblers.ProfileAssembler;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources.CreateProfileResource;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources.ProfileResource;
import pe.edu.upc.psychotest_platform.profiles.interfaces.rest.resources.UpdateProfileResource;

import java.util.List;

/**
 * ProfileController.
 * REST controller for Profile management.
 */
@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profiles", description = "Profile management endpoints")
public class ProfileController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService,
                             ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Create a new profile.
     * @param resource the create profile resource
     * @return the created profile resource
     */
    @PostMapping
    @Operation(summary = "Create a new profile", description = "Create a new user profile with personal information")
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        try {
            var command = ProfileAssembler.toCommandFromResource(resource);
            var profile = profileCommandService.handle(command);

            if (profile.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            var profileResource = ProfileAssembler.toResourceFromEntity(profile.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(profileResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get all profiles.
     * @return list of all profiles
     */
    @GetMapping
    @Operation(summary = "Get all profiles", description = "Retrieve a list of all user profiles")
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var query = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(query);
        var profileResources = profiles.stream()
                .map(ProfileAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(profileResources);
    }

    /**
     * Get profile by ID.
     * @param id the profile id
     * @return the profile resource
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get profile by ID", description = "Retrieve a specific profile by its ID")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long id) {
        var query = new GetProfileByIdQuery(id);
        var profile = profileQueryService.handle(query);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var profileResource = ProfileAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Update a profile.
     * @param id the profile id
     * @param resource the update profile resource
     * @return the updated profile resource
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update profile", description = "Update an existing profile")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable Long id,
                                                          @RequestBody UpdateProfileResource resource) {
        try {
            var command = ProfileAssembler.toCommandFromResource(id, resource);
            var profile = profileCommandService.handle(command);

            if (profile.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var profileResource = ProfileAssembler.toResourceFromEntity(profile.get());
            return ResponseEntity.ok(profileResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a profile.
     * @param id the profile id
     * @return no content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete profile", description = "Delete an existing profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        try {
            var command = new DeleteProfileCommand(id);
            profileCommandService.handle(command);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

