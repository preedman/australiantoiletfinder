package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToiletSearchServiceTest {

    @Mock
    private ToiletRepository toiletRepository;

    @InjectMocks
    private ToiletSearchService toiletSearchService;

    @Test
    void findNearby_distanceMustBePositive() {
        assertThrows(IllegalArgumentException.class,
                () -> toiletSearchService.findNearby(151.0, -33.0, 0.0));

        assertThrows(IllegalArgumentException.class,
                () -> toiletSearchService.findNearby(151.0, -33.0, -1.0));

        verifyNoInteractions(toiletRepository);
    }

    @Test
    void findNearby_callsRepositoryWithExpectedArgs_andReturnsResult() {
        // Given
        double longitude = 151.2093;
        double latitude = -33.8688;
        double distanceKm = 10.0;

        List<Toilet> expected = List.of(new Toilet(), new Toilet());
        when(toiletRepository.findWithinRadiusKm(
                anyDouble(), anyDouble(), anyDouble(),
                anyDouble(), anyDouble(), anyDouble(), anyDouble()
        )).thenReturn(expected);

        // When
        List<Toilet> actual = toiletSearchService.findNearby(longitude, latitude, distanceKm);

        // Then (returned value)
        assertSame(expected, actual);

        // Then (called with correct computed values)
        ArgumentCaptor<Double> latCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> lonCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> radiusCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> minLatCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> maxLatCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> minLonCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> maxLonCaptor = ArgumentCaptor.forClass(Double.class);

        verify(toiletRepository, times(1)).findWithinRadiusKm(
                latCaptor.capture(),
                lonCaptor.capture(),
                radiusCaptor.capture(),
                minLatCaptor.capture(),
                maxLatCaptor.capture(),
                minLonCaptor.capture(),
                maxLonCaptor.capture()
        );

        double expectedLatDelta = distanceKm / 111.32;
        double expectedLonDelta = distanceKm / (111.32 * Math.cos(Math.toRadians(latitude)));

        double expectedMinLat = latitude - expectedLatDelta;
        double expectedMaxLat = latitude + expectedLatDelta;
        double expectedMinLon = longitude - expectedLonDelta;
        double expectedMaxLon = longitude + expectedLonDelta;

        assertEquals(latitude, latCaptor.getValue(), 1e-12);
        assertEquals(longitude, lonCaptor.getValue(), 1e-12);
        assertEquals(distanceKm, radiusCaptor.getValue(), 1e-12);

        assertEquals(expectedMinLat, minLatCaptor.getValue(), 1e-9);
        assertEquals(expectedMaxLat, maxLatCaptor.getValue(), 1e-9);
        assertEquals(expectedMinLon, minLonCaptor.getValue(), 1e-9);
        assertEquals(expectedMaxLon, maxLonCaptor.getValue(), 1e-9);
    }

    @Test
    void findNearby_nearPoles_usesLonDelta180Degrees() {
        // Given: cos(90°) ~ 0 => triggers fallback lonDelta = 180.0
        double longitude = 10.0;
        double latitude = 90.0;
        double distanceKm = 5.0;

        when(toiletRepository.findWithinRadiusKm(
                anyDouble(), anyDouble(), anyDouble(),
                anyDouble(), anyDouble(), anyDouble(), anyDouble()
        )).thenReturn(List.of());

        // When
        toiletSearchService.findNearby(longitude, latitude, distanceKm);

        // Then
        ArgumentCaptor<Double> minLonCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> maxLonCaptor = ArgumentCaptor.forClass(Double.class);

        verify(toiletRepository).findWithinRadiusKm(
                eq(latitude),
                eq(longitude),
                eq(distanceKm),
                anyDouble(),
                anyDouble(),
                minLonCaptor.capture(),
                maxLonCaptor.capture()
        );

        assertEquals(longitude - 180.0, minLonCaptor.getValue(), 1e-12);
        assertEquals(longitude + 180.0, maxLonCaptor.getValue(), 1e-12);
    }

}
