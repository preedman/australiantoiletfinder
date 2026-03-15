package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    // No extra method required for paging all toilets:
    // Page<Toilet> findAll(Pageable pageable) is inherited.

    /**
     * PostgreSQL native query:
     *  - Bounding box pre-filter (fast)
     *  - Great-circle distance (km) using acos/cos/sin + radians()
     *  - LEAST/GREATEST clamp prevents floating-point rounding errors in acos()
     */
    @Query(value = """
        SELECT *
        FROM toilet t
        WHERE t.latitude IS NOT NULL
          AND t.longitude IS NOT NULL
          AND t.latitude BETWEEN :minLat AND :maxLat
          AND t.longitude BETWEEN :minLon AND :maxLon
          AND (
              6371.0 * acos(
                  least(1.0, greatest(-1.0,
                      cos(radians(:lat)) * cos(radians(t.latitude)) *
                      cos(radians(t.longitude) - radians(:lon)) +
                      sin(radians(:lat)) * sin(radians(t.latitude))
                  ))
              )
          ) <= :radiusKm
        ORDER BY (
              6371.0 * acos(
                  least(1.0, greatest(-1.0,
                      cos(radians(:lat)) * cos(radians(t.latitude)) *
                      cos(radians(t.longitude) - radians(:lon)) +
                      sin(radians(:lat)) * sin(radians(t.latitude))
                  ))
              )
          ) ASC
        """, nativeQuery = true)
    List<Toilet> findWithinRadiusKm(
            @Param("lat") double lat,
            @Param("lon") double lon,
            @Param("radiusKm") double radiusKm,
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLon") double minLon,
            @Param("maxLon") double maxLon
    );
}

