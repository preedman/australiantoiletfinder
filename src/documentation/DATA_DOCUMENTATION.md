# Data Documentation

This document describes the data entities located in the `com.reedmanit.australiantoiletfinder.data` package. These entities represent the core domain model of the Australian Toilet Finder application and are mapped to the database using JPA.

## Entities

### Member
The `Member` entity represents a user of the application.

**Key Attributes:**
- `firstname`, `lastname`: Personal details of the member.
- `userid`: Unique identifier for the member (typically used for login or identification).
- **Preferences/Filters:** Contains several boolean flags representing user preferences or requirements (e.g., `parking`, `mlak24`, `babyChange`, `male`, `female`, `unisex`).

**Relationships:**
- `features`: A set of `Feature` entities associated with the member (likely representing specific requirements or historical interactions).
- `favouriteToilets`: A many-to-many relationship with `Toilet`, representing the list of toilets the member has marked as favorites.

---

### Toilet
The `Toilet` entity represents a physical toilet location.

**Key Attributes:**
- `facilityid`: External identifier for the toilet facility.
- `name`: Name of the facility.
- `address1`, `town`, `state`: Geographic location details.
- `latitude`, `longitude`: Precise geographic coordinates.
- **Notes:** Various note fields for specific details (e.g., `addressNote`, `parkingNote`, `accessNote`, `openingHoursNote`).
- `openingHours`: String representation of the facility's operating hours.

**Relationships:**
- `features`: A set of `Feature` entities that describe the specific amenities and accessibility options available at this toilet.
- `favouritedByMembers`: A many-to-many relationship with `Member`, indicating which users have favorited this toilet.

---

### Feature
The `Feature` entity describes specific attributes, amenities, and accessibility options for a `Toilet` or related to a `Member`.

**Key Attributes:**
- **Accessibility:** `accessible`, `parkingAccessible`, `mlak24`, `mlakafterhours`, `adultChange`, `changingPlaces`, `ambulant`, `lhtransfer`, `rhtransfer`.
- **Facilities:** `babyChange`, `babyCareRoom`, `shower`, `drinkingWater`, `dumpPoint`, `dpwashout`, `dpafterhours`.
- **Gender:** `male`, `female`, `allgender`.
- **Requirements:** `keyRequired`, `paymentRequired`.
- **Disposal:** `sharpDisposal`, `sanitaryDisposal`, `mensPadDisposal`.

**Relationships:**
- `toiletId`: The `Toilet` entity this feature set belongs to.
- `memberIdFk`: The `Member` entity this feature set is associated with.
