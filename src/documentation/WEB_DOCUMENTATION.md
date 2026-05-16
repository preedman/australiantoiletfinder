# Web Layer Documentation

This document describes the web controllers located in the `com.reedmanit.australiantoiletfinder.web` package. These controllers handle HTTP requests and manage the flow between the user interface and the service layer.

## Controllers

### ToiletController
The `ToiletController` is the primary controller for managing toilet-related web pages and searches. It is mapped to the root path (`/`).

**Dependencies:**
- `ToiletRepository`: For direct data access to toilet entities.
- `ToiletSearchService`: For spatial search functionality.

**Endpoints:**

#### 1. List Toilets
- **Path:** `/` or `/listToilets`
- **Method:** `GET`
- **Functionality:** Retrieves a paginated list of all toilets.
- **Parameters:**
    - `pageable`: Spring Data `Pageable` object (default size: 10).
- **View:** `toilets/list`
- **Model Attributes:**
    - `toiletPage`: `Page<Toilet>` containing the results.
    - `pageable`: The pagination settings used.

#### 2. Find Nearby Toilets
- **Path:** `/nearby`
- **Method:** `GET`
- **Functionality:** Searches for toilets within a specified radius from a given geographic location. Performs manual pagination on the result list.
- **Parameters:**
    - `lat` (double): Latitude of the search center.
    - `lon` (double): Longitude of the search center.
    - `radius` (double, default: 10.0): Search radius in kilometers.
    - `pageable`: Spring Data `Pageable` object (default size: 10).
- **View:** `toilets/list`
- **Model Attributes:**
    - `toiletPage`: `Page<Toilet>` containing the nearby results (paginated).
    - `pageable`: The pagination settings used.
    - `lat`, `lon`: The coordinates used for the search.

#### 3. Toilet Features
- **Path:** `/{id}/features`
- **Method:** `GET`
- **Functionality:** Displays the features and amenities for a specific toilet. It identifies the "best" feature record (active first, then latest start date).
- **Parameters:**
    - `id` (path variable): The unique identifier of the toilet.
    - `lat`, `lon` (optional): Coordinates to maintain context.
    - `page`, `size` (optional): Pagination parameters to maintain context.
- **View:** `toilets/features`
- **Model Attributes:**
    - `toilet`: The `Toilet` entity.
    - `feature`: The selected `Feature` record.
    - `lat`, `lon`, `page`, `size`: Context parameters for navigation.

#### 4. Toilet Notes
- **Path:** `/{id}/notes`
- **Method:** `GET`
- **Functionality:** Displays additional notes and details for a specific toilet.
- **Parameters:**
    - `id` (path variable): The unique identifier of the toilet.
    - `lat`, `lon` (optional): Coordinates to maintain context.
    - `page`, `size` (optional): Pagination parameters to maintain context.
- **View:** `toilets/toiletnotes`
- **Model Attributes:**
    - `toilet`: The `Toilet` entity.
    - `lat`, `lon`, `page`, `size`: Context parameters for navigation.
