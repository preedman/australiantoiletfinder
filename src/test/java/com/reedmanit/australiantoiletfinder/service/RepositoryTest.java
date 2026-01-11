package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Feature;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ToiletRepository toiletRepository;

    @Autowired
    private FeatureRepository featureRepository;

    private Toilet brisbaneToilet;

    @BeforeEach
    void setUp() {
        // Create a test Toilet
        brisbaneToilet = new Toilet();
        brisbaneToilet.setName("City Hall Toilet");
        brisbaneToilet.setTown("Brisbane");
        brisbaneToilet.setState("QLD");
        brisbaneToilet.setLongitude(153.0210f);
        entityManager.persist(brisbaneToilet);

        // Create a test Feature for that toilet
        Feature accessibleFeature = new Feature();
        accessibleFeature.setToiletId(brisbaneToilet);
        accessibleFeature.setAccessible(true);
        accessibleFeature.setBabyChange(true);
        entityManager.persist(accessibleFeature);

        entityManager.flush();
    }

    @Test
    void whenFindByTown_thenReturnToiletPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Toilet> found = toiletRepository.findByTownIgnoreCase("brisbane", pageable);

        assertThat(found.getContent()).hasSize(1);
        assertThat(found.getContent()).hasSize(1);
        assertThat(found.getContent().get(0).getName()).isEqualTo("City Hall Toilet");
    }

    @Test
    void whenFindByNameContaining_thenReturnMatchedToilets() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Toilet> found = toiletRepository.findByNameContainingIgnoreCase("Hall", pageable);

        assertThat(found.getContent()).hasSize(1);
        assertThat(found.getContent().get(0).getName()).contains("Hall");
    }

    @Test
    void whenFindByTownAndShower_thenReturnMatches() {
        // Add a shower to the existing feature for testing
        Feature feature = featureRepository.findByToiletId(brisbaneToilet, Pageable.unpaged()).getContent().get(0);
        feature.setShower(true);
        entityManager.persist(feature);
        entityManager.flush();

        Pageable pageable = PageRequest.of(0, 10);
        Page<Toilet> found = toiletRepository.findByTownIgnoreCaseAndFeatures_ShowerTrue("Brisbane", pageable);

        assertThat(found.getContent()).hasSize(1);
    }

    @Test
    void whenFindByBabyCareRoom_thenReturnFeaturePage() {
        // Update feature to have baby care room
        Feature feature = featureRepository.findByToiletId(brisbaneToilet, Pageable.unpaged()).getContent().get(0);
        feature.setBabyCareRoom(true);
        entityManager.persist(feature);
        entityManager.flush();

        Page<Feature> features = featureRepository.findByBabyCareRoomTrue(PageRequest.of(0, 10));

        assertThat(features.getContent()).isNotEmpty();
        assertThat(features.getContent().get(0).getBabyCareRoom()).isTrue();
    }

    @Test
    void whenFindByToiletId_thenReturnFeatures() {
        // Test using the Toilet entity object
        Page<Feature> featuresByEntity = featureRepository.findByToiletId(brisbaneToilet, PageRequest.of(0, 10));

        assertThat(featuresByEntity.getContent()).hasSize(1);
        assertThat(featuresByEntity.getContent().get(0).getToiletId().getName()).isEqualTo("City Hall Toilet");

        // Test using the Toilet ID (Integer)
        Integer toiletId = brisbaneToilet.getId();
        Page<Feature> featuresById = featureRepository.findByToiletId_Id(toiletId, PageRequest.of(0, 10));

        assertThat(featuresById.getContent()).hasSize(1);
        assertThat(featuresById.getContent().get(0).getAccessible()).isTrue();
    }
}
