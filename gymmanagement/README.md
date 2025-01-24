# Gym Management API - README

## Project Overview

This project implements a Gym Management System using Spring Boot. The API allows gym owners to manage classes, members, and bookings for their gym. It includes the following key features:

### GYMClass Creation:
Gym owners can create classes with specific details such as name, dates, times, and capacity.

### Booking Management:
Members can book spots for classes, and booking records can be searched by members and date range.

## Table of Contents

1. [Project Setup](#project-setup)
2. [Technologies Used](#technologies-used)
3. [API Endpoints](#api-endpoints)
4. [Testing](#testing)
5. [How to Run the Application](#how-to-run-the-application)
6. [Assumptions](#assumptions)
7. [Folder Structure](#folder-structure)
8. [Final Note](#final-note)

## Project Setup

Extract the Zip folder and import the project from existing sources using an IDE (IntelliJ).

**Dependencies:**
1. Java 17 or higher
2. Spring Boot 2.x
3. Maven (for dependency management)

**Build the project:**

Run: `mvn clean install`

**Run the application:**

Run the following command to start the Spring Boot application:
`mvn spring-boot:run`

**Access the API:**

Once the application is running, you can access the API at `http://localhost:8080`.

## Technologies Used:
* Java: For backend development
* Spring Boot: To build the RESTful API
* JUnit: For unit testing
* Maven: For project dependency management
* Postman (or curl) for testing API endpoints

## API Endpoints

1. **Create GymClass Endpoint:**
   `POST /api/classes` <br/>
   Request Body:
   ```json
   {
     "name": "Pilates",
     "startDate": "2025-01-25",
     "endDate": "2025-01-30",
     "startTime": "10:00",
     "duration": 60,
     "capacity": 20
   }
   ```
Success Response:<br/>
Status: 200 OK<br/>
Body: "Gym class created successfully."

2. **Create Booking Endpoint:** `POST /api/bookings` 
   <br/>Description: Allows members to book spots for a specific gym class. 
   <br/>Request Body:
```json
{
"memberName": "John Doe",
"className": "Pilates",
"participationDate": "2025-01-26"
}
```
Success Response:<br/>
Status: 200 OK<br/>
Body: "Booking created successfully"<br/>

3. Search Bookings Endpoint: `GET /api/bookings/search`
<br/>
Description: Search bookings based on the member’s name or a date range.
<br/>
Query Parameters:
```
memberName: (optional) Filter by member’s name (e.g., John Doe).
startDate: (optional) Start date of the range (Format: YYYY-MM-DD).
endDate: (optional) End date of the range (Format: YYYY-MM-DD).
```
Example Request:

GET /api/bookings/search?memberName=John Doe&startDate=2025-01-25&endDate=2025-01-30
Response:

Success (200 OK):
```json
[
{
"memberName": "John Doe",
"className": "Pilates",
"participationDate": "2025-01-26"
}
]
```

## How to Run the Application
Navigate to project folder in IDE and Build the project:

`mvn clean install`

Run the application:


`mvn spring-boot:run`

Access the API: The application will be running at http://localhost:8080.

Testing the API: You can test the endpoints using Postman or curl.

For example:

Create Gym Class:
Use POST with the URL http://localhost:8080/api/classes and the request body in JSON format.

Create Booking:
Use POST with the URL http://localhost:8080/api/bookings and the request body in JSON format.

Search Bookings:
Use GET with query parameters like http://localhost:8080/api/bookings/search?memberName=John Doe.

## Testing
Unit Tests: All major components (Controllers, Services) have been tested using JUnit.
<br/><br/>
**Test Coverage:** The test cases cover scenarios like:
Class creation and validation booking creation and validation (ensuring class capacity is not exceeded)
Search functionality (search by member and/or date range)<br/>
<br/>To run the tests, use:`mvn test`

## Assumptions
The application assumes that no authentication or authorization is required, and anyone can create classes or bookings.
Data is stored in memory (in-memory arrays). For production, persistence should be added (e.g., using a database).

## Folder Structure
Here’s an overview of the project folder structure:<br/>
<br/>
src/<br/>
├── main/<br/>
│   ├── java/<br/>
│   │   ├── com.example.gymmanagement/<br/>
│   │   │   ├── controller/<br/>
│   │   │   │   ├── ClassController.java<br/>
│   │   │   │   ├── BookingController.java<br/>
│   │   │   ├── service/<br/>
│   │   │   │   ├── ClassService.java<br/>
│   │   │   │   ├── BookingService.java<br/>
│   │   │   ├── model/<br/>
│   │   │   │   ├── ClassEntity.java<br/>
│   │   │   │   ├── BookingEntity.java<br/>
│   │   │   ├── dto/<br/>
│   │   │   │   ├── ClassRequest.java<br/>
│   │   │   │   ├── BookingRequest.java<br/>
│   │   │   │   ├── BookingSearchRequest.java<br/>
│   │   │   ├── GymManagementApplication.java<br/>
├── test/<br/>
│   ├── java/<br/>
│   │   ├── com.example.gymmanagement/<br/>
│   │   │   ├── controller/<br/>
│   │   │   │   ├── ClassControllerTest.java<br/>
│   │   │   │   ├── BookingControllerTest.java<br/>
│   │   │   ├── service/<br/>
│   │   │   │   ├── ClassServiceTest.java<br/>
│   │   │   │   ├── BookingServiceTest.java<br/>

controller/: Contains API controllers for handling HTTP requests.<br/>
service/: Contains business logic for managing classes and bookings.<br/>
model/: Contains entities for the class and booking data.<br/>
dto/: Contains data transfer objects for request and response handling.

## Final Notes
This Gym Management API is a simple system built with Spring Boot. It includes functionalities for creating gym classes, booking spots, and searching for bookings. The project can be extended by adding persistent storage like a database, authentication, and more advanced features for a complete solution.
