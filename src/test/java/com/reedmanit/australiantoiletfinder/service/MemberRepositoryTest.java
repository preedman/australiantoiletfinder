package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest

public class MemberRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member sam = new Member();
        sam.setFirstname("Sam");
        sam.setLastname("Reedman");
        sam.setUserid("sam123");
        entityManager.persist(sam);

        Member sally = new Member();
        sally.setFirstname("Sally");
        sally.setLastname("Reedman");
        sally.setUserid("sally456");
        entityManager.persist(sally);

        Member alex = new Member();
        alex.setFirstname("Alex");
        alex.setLastname("Smith");
        alex.setUserid("alex_smith");
        entityManager.persist(alex);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void findByUserid_returnsMatchingMember() {
        var found = memberRepository.findByUserid("sam123");

        assertThat(found).isPresent();
        assertThat(found.get().getFirstname()).isEqualTo("Sam");
        assertThat(found.get().getLastname()).isEqualTo("Reedman");
    }

    @Test
    void findByUserid_returnsEmptyWhenNotFound() {
        var found = memberRepository.findByUserid("does-not-exist");
        assertThat(found).isNotPresent();
    }

    @Test
    void existsByUserid_returnsTrueWhenExistsFalseWhenNot() {
        assertThat(memberRepository.existsByUserid("alex_smith")).isTrue();
        assertThat(memberRepository.existsByUserid("missing")).isFalse();
    }

    @Test
    void findByLastnameIgnoreCase_matchesRegardlessOfCase() {
        List<Member> found = memberRepository.findByLastnameIgnoreCase("REEDMAN");

        assertThat(found).hasSize(2);
        assertThat(found).extracting(Member::getUserid)
                .containsExactlyInAnyOrder("sam123", "sally456");
    }

    @Test
    void findByFirstnameIgnoreCaseAndLastnameIgnoreCase_matchesFullNameIgnoringCase() {
        List<Member> found = memberRepository.findByFirstnameIgnoreCaseAndLastnameIgnoreCase("sAm", "rEeDmAn");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getUserid()).isEqualTo("sam123");
    }

    @Test
    void findByUseridContainingIgnoreCase_matchesSubstringIgnoringCase() {
        List<Member> found = memberRepository.findByUseridContainingIgnoreCase("SMI");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getUserid()).isEqualTo("alex_smith");
    }
}
