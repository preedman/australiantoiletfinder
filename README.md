# Australian Toilet Finder

This project is a Spring Boot application designed to help users find toilets across Australia. It provides a web-based interface to list and view details of various public toilets, including their features, opening hours, and specific notes related to accessibility, parking, and more.

## Project Status

The first version (v0.0.1) of this project has been **successfully deployed**. It is now in an active maintenance and feature enhancement phase.

## Features (Implemented)

-   **List View:** A comprehensive list of toilets with pagination.
-   **Nearby Search:** Find toilets within a specified radius based on geographic coordinates.
-   **Features View:** Detailed information about specific toilet features (e.g., adult change, baby change, etc.).
-   **Notes View:** Specific notes for each toilet, such as access, parking, and opening hours.
-   **User Authentication:** Basic logout functionality integrated with Spring Security.

## Technologies Used

-   **Java 17+**
-   **Spring Boot** (Spring Web, Spring Data JPA, Spring Security)
-   **JSP (Jakarta Server Pages)** for the web frontend.
-   **Bootstrap 5** for responsive styling.
-   **H2 Database** for local development.
-   **PostgreSQL** for production deployment.
-   **Maven** for project management and builds.

## Getting Started

### Prerequisites

-   Java 17 or higher
-   Maven 3.6+

### Local Development

1.  Clone the repository.
2.  Run the application using Maven:
    ```bash
    ./mvnw spring-boot:run
    ```
3.  Access the application at `http://localhost:8080`.
4.  The application uses an H2 in-memory database by default for local development.
