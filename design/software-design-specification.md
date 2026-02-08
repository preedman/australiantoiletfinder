# Software Design Specification (SDS)

## 3. Architecture Overview (High-Level)

### 3.1 System Context (C4: Context)

- **Primary users:** Web app users (public visitors)
- **System:** Toilet Finder Web Application
- **External systems (optional, as used):**
  - Browser geolocation (client-side)
  - Map provider (if you embed maps)
- **Data store:** Relational DB (via JPA)

**Diagram (PlantUML C4-style):**

plantuml @startuml title C4 - System Context (Level 1)
actor "User" as user rectangle "Toilet Finder Web App
(Spring Boot + Spring MVC + JSP)" as system database "Relational Database" as db cloud "Browser Geolocation API" as geo
user --> system : Search / Filter / View details (HTTP) system --> db : Read toilet + feature data (JPA) system <-- geo : User location (lat/lon) (optional)
@enduml



### 3.2 Container View (C4: Containers)

- **Web container:** Spring Boot app hosting:
  - Spring MVC controllers
  - Service layer (business logic)
  - Spring Data JPA repositories
  - JSP views (rendered server-side)
- **Database container:** relational database storing toilet locations and features

plantuml @startuml title C4 - Container View (Level 2)
rectangle "Spring Boot Application" as app { [Spring MVC Controllers] as controllers [Service Layer] as services [Spring Data JPA Repositories] as repos [JSP Views] as jsp }
database "Relational Database" as db actor "User Browser" as browser
browser --> controllers : HTTP GET/POST controllers --> services services --> repos repos --> db
controllers --> jsp : Model -> JSP render jsp --> browser : HTML response
@enduml

### 3.3 Architectural Style & Patterns

- **Pattern:** Layered architecture: **Controller → Service → Repository → DB**
- **Presentation:** Server-side rendered **JSP**
- **Data access:** Spring Data JPA repositories + targeted custom queries when required
- **Validation:** Bean Validation for request params/forms (where applicable)
- **Error handling:** friendly error pages + consistent validation messaging
- **Pagination:** all list/search results paginated (define defaults + max page size)

---

## 5. Module / Component Design

### 5.1 Web Layer (Spring MVC + JSP)

**Responsibilities**

- Endpoints for: search by location, filter by features, view details
- Validate inputs (lat/lon, radius, feature flags, IDs)
- Build view models for JSP pages

**Suggested pages**

- `search.jsp` (search form)
- `results.jsp` (search results + filters + paging)
- `detail.jsp` (toilet details)
- `error.jsp` (general error)

### 5.2 Service Layer




**Responsibilities**

- Orchestrate queries and business rules (geo bounding box, filter criteria, paging)
- Define transaction boundaries (read-only for search flows)

### 5.3 Persistence Layer (Spring Data JPA)

**Responsibilities**

- Encapsulate DB access and query definitions
- Provide paging/sorting queries
- Provide specialized geo-radius search query (if used)

---

## 7. UI Requirements (JSP)

### 7.1 Key pages for core flows

**Search by location**

- Inputs: latitude, longitude, radius (km)
- Output: list of matching toilets + paging
- States: no results, invalid location, location unavailable

**Filter by features**

- Inputs: feature checkboxes/toggles
- Output: filtered list + paging
- States: no results, filters cleared

**View details**

- Input: toilet ID
- Output: location + features + relevant metadata
- States: not found

---

## 8. Key Runtime Flows (Sequence Diagrams)

### Flow 1: Search by location

plantuml @startuml title Sequence - Search by Location (Radius)

actor User participant "Browser (JSP UI)" as Browser participant "SearchController" as C participant "ToiletSearchService" as S participant "ToiletRepository" as R database "DB" as DB

User -> Browser : Enter location + radius
Submit Browser -> C : GET/POST /search/results?lat&lon&radius&page C -> C : Validate inputs (lat/lon range, radius) C -> S : searchWithinRadius(lat, lon, radius, pageable) S -> S : Compute bounding box (minLat/maxLat/minLon/maxLon) S -> R : findWithinRadiusKm(lat, lon, radius,
minLat,maxLat,minLon,maxLon) R -> DB : Execute query DB --> R : Matching toilets R --> S : List/Page S --> C : Results model C --> Browser : Render results.jsp (HTML)
@enduml

### Flow 2: Filter by features

plantuml @startuml title Sequence - Filter by Features
actor User participant "Browser (JSP UI)" as Browser participant "Filter/Search Controller" as C participant "ToiletSearchService" as S participant "ToiletRepository" as R database "DB" as DB
User -> Browser : Select feature filters
Submit Browser -> C : GET /toilets?accessible=true&...
&page&sort C -> C : Validate filter params + paging C -> S : filterToilets(criteria, pageable) S -> R : Query by criteria (paged) R -> DB : Execute query DB --> R : Page of toilets R --> S : Page S --> C : View model C --> Browser : Render results.jsp (HTML)
@enduml

### Flow 3: View details

plantuml @startuml title Sequence - View Toilet Details
actor User participant "Browser (JSP UI)" as Browser participant "ToiletController" as C participant "ToiletSearchService" as S participant "ToiletRepository" as R database "DB" as DB
User -> Browser : Click a toilet Browser -> C : GET /toilets/{id} C -> S : getToiletDetails(id) S -> R : findById(id) (and load related features as needed) R -> DB : Query toilet (+ features) DB --> R : Toilet record R --> S : Toilet S --> C : Detail view model C --> Browser : Render detail.jsp (HTML)
@enduml
