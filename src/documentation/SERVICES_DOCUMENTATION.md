# Services Documentation

This document describes the services and repositories located in the `com.reedmanit.australiantoiletfinder.service` package.

## Services

### FavouriteService
The `FavouriteService` manages the association between members and their favorite toilets.

**Key Methods:**
- `addFavourite(Integer memberId, Integer toiletId)`: Adds a toilet to a member's list of favorites. Throws `EntityNotFoundException` if the member or toilet does not exist.
- `removeFavourite(Integer memberId, Integer toiletId)`: Removes a toilet from a member's list of favorites.
- `getFavourites(Integer memberId)`: Returns a list of all favorite toilets for a given member.
- `isFavourite(Integer memberId, Integer toiletId)`: Checks if a specific toilet is in a member's favorite list.

### ToiletSearchService
The `ToiletSearchService` provides functionality for spatial searching of toilets based on geographic coordinates.

**Key Methods:**
- `findNearby(double longitude, double latitude, double distanceKm)`: Searches for toilets within a specified radius (in kilometers) from a given longitude and latitude. It uses a bounding box pre-filter followed by a great-circle distance calculation for accuracy.

---

## Repositories

The project uses Spring Data JPA repositories for data access.

### MemberRepository
Handles persistence operations for `Member` entities.

**Key Features:**
- Standard CRUD operations via `JpaRepository`.
- `findByUserid(String userid)`: Retrieves a member by their unique user ID.
- `findByIdWithFavouriteToilets(Integer id)`: Custom query to fetch a member along with their favorite toilets using a join fetch to avoid N+1 problems.
- Search methods for members by first name, last name, and user ID (partial match).

### ToiletRepository
Handles persistence operations for `Toilet` entities.

**Key Features:**
- Paginated search methods:
    - `findByTownIgnoreCase`
    - `findByStateIgnoreCase`
    - `findByNameContainingIgnoreCase`
    - `findByFeatures_AccessibleTrue`
    - `findByFeatures_BabyChangeTrue`
- `findWithinRadiusKm(...)`: A native PostgreSQL query that performs spatial filtering. It uses a bounding box and the Haversine formula (or similar great-circle distance) to find toilets within a radius and orders them by distance.

### FeatureRepository
Handles persistence operations for `Feature` entities, which represent specific attributes of a toilet (e.g., accessibility, gender, drinking water).

**Key Features:**
- `findByToiletId(Toilet toilet, Pageable pageable)`: Finds features for a specific toilet entity.
- `findByToiletId_Id(Integer toiletId, Pageable pageable)`: Finds features using a toilet's ID.
- Specialized filter methods for features:
    - `findByAccessibleTrue`
    - `findByBabyCareRoomTrue`
    - `findByMlak24TrueOrMlakafterhoursTrue`
    - `findByMaleTrueAndFemaleTrue`
    - `findByDrinkingWaterTrue`
