package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Feature;
import com.reedmanit.australiantoiletfinder.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByUserid(String userid);

    boolean existsByUserid(String userid);

    List<Member> findByLastnameIgnoreCase(String lastname);

    List<Member> findByFirstnameIgnoreCaseAndLastnameIgnoreCase(String firstname, String lastname);

    List<Member> findByUseridContainingIgnoreCase(String useridPart);

    /**
    @Query("""
            select distinct m
            from Member m
            left join fetch m.features f
            where m.id = :id
            """)
    Optional<Member> findByIdWithFeatures(@Param("id") Integer id);

    @Query("""
            select f
            from Member m
            join m.features f
            where m.id = :memberId
            """)
    List<Feature> findFeaturesByMemberId(@Param("memberId") Integer memberId);
    */

    @Query("""
            select distinct m
            from Member m
            left join fetch m.favouriteToilets
            where m.id = :id
            """)
    Optional<Member> findByIdWithFavouriteToilets(@Param("id") Integer id);

    // ... existing code ...
}
