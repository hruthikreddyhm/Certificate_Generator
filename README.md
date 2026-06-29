# Certificate Generator Backend

Spring Boot backend for managing candidates and generating downloadable certificates as PDF files.

## Overview

This service stores candidate records in PostgreSQL and generates certificates for eligible candidates. A certificate can be requested by candidate ID or email. If the candidate score is below the eligibility threshold, the API returns an error response.

## Features

- Create, update, fetch, list, and delete candidate records
- Generate and download certificate PDFs
- Eligibility validation based on candidate score
- Reuse existing certificate records when available
- Centralized exception handling with JSON error responses

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- iText 7 for PDF generation

## Prerequisites

- JDK 21
- Maven 3.9+ or the included Maven wrapper
- PostgreSQL running locally

## Database Configuration

The application reads database settings from `src/main/resources/application.properties`.

Default values:

- Database URL: `jdbc:postgresql://localhost:5432/jdbcvtu`
- Username: `postgres`
- Password: `root`
- Server port: `8084`

Create the database before starting the app:

```sql
CREATE DATABASE jdbcvtu;
```

If your local PostgreSQL credentials differ, update `src/main/resources/application.properties` before running.

## Run the Application

Using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Or with Maven:

```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8084`.

## API Endpoints

### Admin candidate management

Base path: `/admin/candidates`

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/admin/candidates` | Create a candidate |
| PUT | `/admin/candidates/{id}` | Update a candidate |
| GET | `/admin/candidates/{id}` | Get a candidate by ID |
| GET | `/admin/candidates` | List all candidates |
| DELETE | `/admin/candidates/{id}` | Delete a candidate |

### Candidate actions

Base path: `/candidate`

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/candidate/{id}` | Get candidate details by ID |
| GET | `/candidate/certificates/{input}` | Download a certificate by candidate ID or email |

## Sample Candidate Payload

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "score": 85,
  "courseName": "Java Full Stack",
  "organizationName": "Example Org",
  "coordinatorName": "Jane Smith"
}
```

## Certificate Rules

- A candidate must have a score of at least 60 to be eligible.
- Certificate download accepts either a numeric candidate ID or an email address.
- The response is returned as a PDF attachment named `certificate.pdf`.

## Error Handling

The application returns JSON error responses for common failures such as:

- candidate not found
- candidate not eligible
- unexpected server errors

## Project Structure

- `controller/` - REST endpoints
- `services/` - business logic
- `repository/` - JPA repositories
- `entity/` - database entities
- `dto/` - request models
- `exception/` - custom exceptions and global handler
- `util/` - PDF generation helper

## Notes

- CORS is configured for `http://localhost:5173`, which matches a local frontend running on Vite.
- The `certificates/` folder contains sample response data and generated PDF files.
