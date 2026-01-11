package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Feature;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    // Find all features for a specific toilet
    Page<Feature> findByToiletId(Toilet toilet, Pageable pageable);

    // Find by the ID of the toilet (useful if you only have the Integer ID)
    Page<Feature> findByToiletId_Id(Integer toiletId, Pageable pageable);

    // Filter features that are accessible
    Page<Feature> findByAccessibleTrue(Pageable pageable);

    // Filter features that provide a baby change room
    Page<Feature> findByBabyCareRoomTrue(Pageable pageable);

    // Filter features that are open after hours (specifically for dump points or MLAK)
    Page<Feature> findByMlak24TrueOrMlakafterhoursTrue(Pageable pageable);

    // Filter features by gender availability
    Page<Feature> findByMaleTrueAndFemaleTrue(Pageable pageable);

    // Filter features that have drinking water
    Page<Feature> findByDrinkingWaterTrue(Pageable pageable);
}
