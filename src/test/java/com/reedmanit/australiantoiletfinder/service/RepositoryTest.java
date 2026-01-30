package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Feature;
import com.reedmanit.australiantoiletfinder.data.Member;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ToiletRepository toiletRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Toilet brisbaneToilet;
    private Member member;

    @BeforeEach
    void setUp() {
        // Create a test Toilet
        brisbaneToilet = new Toilet();
        brisbaneToilet.setName("City Hall Toilet");
        brisbaneToilet.setTown("Brisbane");
        brisbaneToilet.setState("QLD");
        brisbaneToilet.setLongitude(153.0210f);
        entityManager.persist(brisbaneToilet);

        // Create a test Member
        member = new Member();
        member.setFirstname("Sam");
        member.setLastname("Reedman");
        member.setUserid("sam123");
        entityManager.persist(member);

        // Create a test Feature for that toilet (and link it to the member)
        Feature accessibleFeature = new Feature();
        accessibleFeature.setToiletId(brisbaneToilet);
        accessibleFeature.setAccessible(true);
        accessibleFeature.setBabyChange(true);
        accessibleFeature.setMemberIdFk(member);
        entityManager.persist(accessibleFeature);

        entityManager.flush();
        entityManager.clear();
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

    @Test
    void whenFindMemberByUserid_thenReturnMember() {
        Optional<Member> found = memberRepository.findByUserid("sam123");

        assertThat(found).isPresent();
        assertThat(found.get().getUserid()).isEqualTo("sam123");
    }

    @Test
    void whenExistsByUserid_thenReturnTrue() {
        boolean exists = memberRepository.existsByUserid("sam123");

        assertThat(exists).isTrue();
    }

    @Test
    void whenFindByIdWithFeatures_thenFeaturesAreFetched() {
        Integer memberId = member.getId();

        Optional<Member> found = memberRepository.findByIdWithFeatures(memberId);

        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(memberId);
        assertThat(found.get().getFeatures()).isNotNull();
        assertThat(found.get().getFeatures()).hasSize(1);
    }

    @Test
    void whenFindFeaturesByMemberId_thenReturnFeatures() {
        Integer memberId = member.getId();

        List<Feature> features = memberRepository.findFeaturesByMemberId(memberId);

        assertThat(features).hasSize(1);
        assertThat(features.get(0).getMemberIdFk()).isNotNull();
        assertThat(features.get(0).getMemberIdFk().getId()).isEqualTo(memberId);
    }
}
