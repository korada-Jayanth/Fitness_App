🏋️‍♂️ Fitness Tracker – Microservices with Spring Boot, Eureka & WebClient

A modular Fitness Tracking Application built using Spring Boot Microservices.
The system includes independent services that handle users, fitness activities, and a central Eureka discovery server.
Services communicate with each other using Spring WebClient, ensuring fast, non-blocking inter-service communication.

📦 Microservices Overview
🔹 1. Eureka Server (eureka_server)

Acts as the Service Registry for the ecosystem.

Built using Spring Cloud Netflix Eureka Server

All services auto-register at runtime

Provides a UI dashboard for instance visibility

Tech Stack:
Spring Boot 3.5.x, Spring Cloud 2025.x, Java 17

🔹 2. User Service (userservice)

Manages all User-related operations.

Features:

Register new users

Update user info

Fetch user details

Validations via Spring Validation

Persists data in PostgreSQL

Registers with Eureka as a client

Tech Stack:
Spring Boot 3.3.x, Spring Data JPA, PostgreSQL, Spring Web, Eureka Client, Java 17

🔹 3. Activity Service (activityservice)

Responsible for tracking fitness activities like running, walking, cycling, etc.

Features:

Add new activity

Get activities of a user

Stores data in MongoDB

Uses WebClient to call User Service

Auto-registers with Eureka

Tech Stack:
Spring Boot 3.3.x, Spring Data MongoDB, Spring WebFlux, Eureka Client, Java 17

🧱 Architecture Diagram
                       +-----------------------+
                       |      Eureka Server    |
                       |   (Service Registry)  |
                       +-----------+-----------+
                                   |
                -----------------------------------------
                |                                       |
      +-----------------------+              +-----------------------+
      |      User Service     | <--WebClient |   Activity Service    |
      | (PostgreSQL, JPA)     |              | (MongoDB, Reactive)   |
      +-----------------------+              +-----------------------+


WebClient ensures reactive & efficient communication

User & Activity services are loosely coupled

Each service has its own database (microservice best practice)

⚙️ Running the Project

Make sure you have PostgreSQL and MongoDB running locally.

1️⃣ Start Eureka Server
cd eureka_server
mvn spring-boot:run


Eureka Dashboard → http://localhost:8761

2️⃣ Start User Service
cd userservice
mvn spring-boot:run

3️⃣ Start Activity Service
cd activityservice
mvn spring-boot:run


Activity Service will automatically discover User Service via Eureka.

🌐 API Endpoints
User Service
POST   /users
GET    /users/{id}
PUT    /users/{id}
DELETE /users/{id}

Activity Service
POST /activities
GET  /activities/user/{userId}

🗄 Databases
User Service: PostgreSQL

Tables managed by Hibernate (auto-DDL)

Activity Service: MongoDB

Collections created automatically

🔗 Inter-Service Communication (WebClient Example)
WebClient webClient = WebClient.builder()
        .baseUrl("http://USERSERVICE/users")
        .build();


Eureka resolves the service name → no hardcoded URLs or ports.

🚀 Future Enhancements

API Gateway (Spring Cloud Gateway)

JWT Authentication

Docker Compose for all services

Common logging using Sleuth/Zipkin

Monitoring with Actuator + Prometheus

📄 License

This project is open-source and free to use.
