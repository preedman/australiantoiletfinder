package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Member;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ToiletRepository toiletRepository;

    @InjectMocks
    private FavouriteService favouriteService;

    @Test
    void addFavourite_addsToMembersFavourites_andSavesMember() {
        Integer memberId = 1;
        Integer toiletId = 10;

        Member member = new Member();
        member.setId(memberId);

        Toilet toilet = new Toilet();
        toilet.setId(toiletId);
        toilet.setName("Toilet A");

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.of(member));
        when(toiletRepository.findById(toiletId)).thenReturn(Optional.of(toilet));

        favouriteService.addFavourite(memberId, toiletId);

        assertThat(member.getFavouriteToilets())
                .extracting(Toilet::getId)
                .contains(toiletId);

        verify(memberRepository).save(member);
        verifyNoMoreInteractions(memberRepository);
        verify(toiletRepository).findById(toiletId);
        verifyNoMoreInteractions(toiletRepository);
    }

    @Test
    void addFavourite_throwsWhenMemberMissing() {
        Integer memberId = 1;
        Integer toiletId = 10;

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> favouriteService.addFavourite(memberId, toiletId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Member not found");

        verify(memberRepository).findByIdWithFavouriteToilets(memberId);
        verifyNoMoreInteractions(memberRepository);
        verifyNoInteractions(toiletRepository);
    }

    @Test
    void addFavourite_throwsWhenToiletMissing_andDoesNotSave() {
        Integer memberId = 1;
        Integer toiletId = 10;

        Member member = new Member();
        member.setId(memberId);

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.of(member));
        when(toiletRepository.findById(toiletId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> favouriteService.addFavourite(memberId, toiletId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Toilet not found");

        verify(memberRepository).findByIdWithFavouriteToilets(memberId);
        verify(toiletRepository).findById(toiletId);
        verify(memberRepository, never()).save(any());
    }

    @Test
    void removeFavourite_removesFromMembersFavourites_andSavesMember() {
        Integer memberId = 1;
        Integer toiletId = 10;

        Member member = new Member();
        member.setId(memberId);

        Toilet toilet = new Toilet();
        toilet.setId(toiletId);
        toilet.setName("Toilet A");

        member.addFavouriteToilet(toilet);

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.of(member));
        when(toiletRepository.findById(toiletId)).thenReturn(Optional.of(toilet));

        favouriteService.removeFavourite(memberId, toiletId);

        assertThat(member.getFavouriteToilets())
                .extracting(Toilet::getId)
                .doesNotContain(toiletId);

        verify(memberRepository).save(member);
    }

    @Test
    void getFavourites_returnsMembersFavouriteToilets() {
        Integer memberId = 1;

        Member member = new Member();
        member.setId(memberId);

        Toilet toilet1 = new Toilet();
        toilet1.setId(10);
        toilet1.setName("Toilet A");

        Toilet toilet2 = new Toilet();
        toilet2.setId(11);
        toilet2.setName("Toilet B");

        member.addFavouriteToilet(toilet1);
        member.addFavouriteToilet(toilet2);

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.of(member));

        List<Toilet> favourites = favouriteService.getFavourites(memberId);

        assertThat(favourites)
                .extracting(Toilet::getId)
                .containsExactlyInAnyOrder(10, 11);

        verify(memberRepository).findByIdWithFavouriteToilets(memberId);
        verifyNoMoreInteractions(memberRepository);
        verifyNoInteractions(toiletRepository);
    }

    @Test
    void isFavourite_returnsTrueWhenToiletIsInFavourites() {
        Integer memberId = 1;
        Integer toiletId = 10;

        Member member = new Member();
        member.setId(memberId);

        Toilet toilet = new Toilet();
        toilet.setId(toiletId);
        toilet.setName("Toilet A");

        member.addFavouriteToilet(toilet);

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.of(member));

        boolean result = favouriteService.isFavourite(memberId, toiletId);

        assertThat(result).isTrue();
        verify(memberRepository).findByIdWithFavouriteToilets(memberId);
        verifyNoMoreInteractions(memberRepository);
        verifyNoInteractions(toiletRepository);
    }

    @Test
    void isFavourite_returnsFalseWhenToiletIsNotInFavourites() {
        Integer memberId = 1;

        Member member = new Member();
        member.setId(memberId);

        Toilet toilet = new Toilet();
        toilet.setId(99);
        toilet.setName("Other Toilet");

        member.addFavouriteToilet(toilet);

        when(memberRepository.findByIdWithFavouriteToilets(memberId)).thenReturn(Optional.of(member));

        boolean result = favouriteService.isFavourite(memberId, 10);

        assertThat(result).isFalse();
        verify(memberRepository).findByIdWithFavouriteToilets(memberId);
        verifyNoMoreInteractions(memberRepository);
        verifyNoInteractions(toiletRepository);
    }
}
