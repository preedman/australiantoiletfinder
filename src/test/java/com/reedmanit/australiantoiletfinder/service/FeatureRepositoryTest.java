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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest

public class FeatureRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FeatureRepository featureRepository;

    private Toilet toiletA;
    private Toilet toiletB;

    @BeforeEach
    void setUp() {
        toiletA = new Toilet();
        toiletA.setName("Toilet A");
        toiletA.setTown("Brisbane");
        toiletA.setState("QLD");
        toiletA.setLongitude(153.0210f);
        entityManager.persist(toiletA);

        toiletB = new Toilet();
        toiletB.setName("Toilet B");
        toiletB.setTown("Sydney");
        toiletB.setState("NSW");
        toiletB.setLongitude(151.2093f);
        entityManager.persist(toiletB);

        // Feature 1: Toilet A, accessible + drinking water
        Feature f1 = new Feature();
        f1.setToiletId(toiletA);
        f1.setAccessible(true);
        f1.setDrinkingWater(true);
        entityManager.persist(f1);

        // Feature 2: Toilet A, baby care room + both genders
        Feature f2 = new Feature();
        f2.setToiletId(toiletA);
        f2.setBabyCareRoom(true);
        f2.setMale(true);
        f2.setFemale(true);
        entityManager.persist(f2);

        // Feature 3: Toilet B, after hours MLAK
        Feature f3 = new Feature();
        f3.setToiletId(toiletB);
        f3.setMlakafterhours(true);
        entityManager.persist(f3);

        // Feature 4: Toilet B, 24h MLAK
        Feature f4 = new Feature();
        f4.setToiletId(toiletB);
        f4.setMlak24(true);
        entityManager.persist(f4);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void findByToiletId_returnsFeaturesForThatToilet() {
        Page<Feature> page = featureRepository.findByToiletId(toiletA, PageRequest.of(0, 10));

        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent())
                .allMatch(f -> f.getToiletId() != null && f.getToiletId().getName().equals("Toilet A"));
    }

    @Test
    void findByToiletId_Id_returnsFeaturesForThatToiletId() {
        Integer toiletId = toiletB.getId();

        Page<Feature> page = featureRepository.findByToiletId_Id(toiletId, PageRequest.of(0, 10));

        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent())
                .allMatch(f -> f.getToiletId() != null && f.getToiletId().getId().equals(toiletId));
    }

    @Test
    void findByAccessibleTrue_returnsOnlyAccessibleFeatures() {
        Page<Feature> page = featureRepository.findByAccessibleTrue(Pageable.unpaged());

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getAccessible()).isTrue();
        assertThat(page.getContent().get(0).getToiletId().getName()).isEqualTo("Toilet A");
    }

    @Test
    void findByBabyCareRoomTrue_returnsOnlyBabyCareRoomFeatures() {
        Page<Feature> page = featureRepository.findByBabyCareRoomTrue(Pageable.unpaged());

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getBabyCareRoom()).isTrue();
        assertThat(page.getContent().get(0).getToiletId().getName()).isEqualTo("Toilet A");
    }

    @Test
    void findByMlak24TrueOrMlakafterhoursTrue_returnsBothTypes() {
        Page<Feature> page = featureRepository.findByMlak24TrueOrMlakafterhoursTrue(Pageable.unpaged());

        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent())
                .anyMatch(f -> Boolean.TRUE.equals(f.getMlak24()))
                .anyMatch(f -> Boolean.TRUE.equals(f.getMlakafterhours()));
    }

    @Test
    void findByMaleTrueAndFemaleTrue_returnsOnlyFeaturesWithBothGenders() {
        Page<Feature> page = featureRepository.findByMaleTrueAndFemaleTrue(Pageable.unpaged());

        assertThat(page.getContent()).hasSize(1);
        Feature found = page.getContent().get(0);
        assertThat(found.getMale()).isTrue();
        assertThat(found.getFemale()).isTrue();
        assertThat(found.getToiletId().getName()).isEqualTo("Toilet A");
    }

    @Test
    void findByDrinkingWaterTrue_returnsOnlyDrinkingWaterFeatures() {
        Page<Feature> page = featureRepository.findByDrinkingWaterTrue(Pageable.unpaged());

        assertThat(page.getContent()).hasSize(1);
        Feature found = page.getContent().get(0);
        assertThat(found.getDrinkingWater()).isTrue();
        assertThat(found.getToiletId().getName()).isEqualTo("Toilet A");
    }
}
