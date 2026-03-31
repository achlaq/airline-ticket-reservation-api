# Airline Ticket Reservation API

This project is a backend application built with Spring Boot, providing a RESTful API for managing an airline ticket reservation system.

## Tech Stack

*   **Java:** 17
*   **Spring Boot:** 3.x
*   **Database:** PostgreSQL
*   **Database Migration:** Flyway
*   **Build Tool:** Maven

## Prerequisites

Before you begin, ensure you have the following installed:

*   JDK 17 or later
*   Maven 3.6 or later
*   PostgreSQL

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/achlaq/airline-ticket-reservation-api.git
cd airline-ticket-reservation-api
```

### 2. Configure the Database

1.  Create a new PostgreSQL database.
    ```sql
    CREATE DATABASE flightdb;
    ```
2.  Update the `src/main/resources/application.properties` file with your database credentials.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/flightdb
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    ```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

The application will be accessible at `http://localhost:8899`.