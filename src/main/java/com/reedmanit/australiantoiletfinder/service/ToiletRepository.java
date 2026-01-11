package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToiletRepository extends JpaRepository<Toilet, Integer> {

    // Paginated search by town
    Page<Toilet> findByTownIgnoreCase(String town, Pageable pageable);

    // Paginated search by state
    Page<Toilet> findByStateIgnoreCase(String state, Pageable pageable);

    // Paginated fuzzy search by name
    Page<Toilet> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Paginated search for accessible toilets
    Page<Toilet> findByFeatures_AccessibleTrue(Pageable pageable);

    // Paginated search for baby change facilities
    Page<Toilet> findByFeatures_BabyChangeTrue(Pageable pageable);

    // Paginated search for toilets in a town with a shower
    Page<Toilet> findByTownIgnoreCaseAndFeatures_ShowerTrue(String town, Pageable pageable);

    // Single result doesn't usually need paging
    Toilet findByFacilityid(Integer facilityid);
}

