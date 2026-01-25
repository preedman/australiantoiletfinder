package com.reedmanit.australiantoiletfinder.service;

import com.reedmanit.australiantoiletfinder.data.Toilet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletSearchService {

    private final ToiletRepository toiletRepository;

    public ToiletSearchService(ToiletRepository toiletRepository) {
        this.toiletRepository = toiletRepository;
    }

    public List<Toilet> findNearby(double longitude, double latitude, double distanceKm) {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("distanceKm must be > 0");
        }

        // Approx degrees per km:
        // 1 degree latitude ~ 111.32 km
        double latDelta = distanceKm / 111.32;

        // Longitude degrees shrink by cos(latitude)
        double latRad = Math.toRadians(latitude);
        double cosLat = Math.cos(latRad);
        // avoid division by ~0 near poles
        double lonDelta = (cosLat < 1e-12) ? 180.0 : distanceKm / (111.32 * cosLat);

        double minLat = latitude - latDelta;
        double maxLat = latitude + latDelta;
        double minLon = longitude - lonDelta;
        double maxLon = longitude + lonDelta;

        return toiletRepository.findWithinRadiusKm(
                latitude, longitude, distanceKm,
                minLat, maxLat, minLon, maxLon
        );
    }
}
