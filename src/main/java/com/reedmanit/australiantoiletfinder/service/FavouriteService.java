package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Member;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FavouriteService {

    private final MemberRepository memberRepository;
    private final ToiletRepository toiletRepository;

    public FavouriteService(MemberRepository memberRepository, ToiletRepository toiletRepository) {
        this.memberRepository = memberRepository;
        this.toiletRepository = toiletRepository;
    }

    @Transactional
    public void addFavourite(Integer memberId, Integer toiletId) {
        Member member = memberRepository.findByIdWithFavouriteToilets(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found: " + memberId));

        Toilet toilet = toiletRepository.findById(toiletId)
                .orElseThrow(() -> new EntityNotFoundException("Toilet not found: " + toiletId));

        member.addFavouriteToilet(toilet);
        memberRepository.save(member);
    }

    @Transactional
    public void removeFavourite(Integer memberId, Integer toiletId) {
        Member member = memberRepository.findByIdWithFavouriteToilets(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found: " + memberId));

        Toilet toilet = toiletRepository.findById(toiletId)
                .orElseThrow(() -> new EntityNotFoundException("Toilet not found: " + toiletId));

        member.removeFavouriteToilet(toilet);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<Toilet> getFavourites(Integer memberId) {
        Member member = memberRepository.findByIdWithFavouriteToilets(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found: " + memberId));

        return member.getFavouriteToilets().stream().toList();
    }

    @Transactional(readOnly = true)
    public boolean isFavourite(Integer memberId, Integer toiletId) {
        Member member = memberRepository.findByIdWithFavouriteToilets(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found: " + memberId));

        return member.getFavouriteToilets().stream().anyMatch(t -> t.getId().equals(toiletId));
    }

}
