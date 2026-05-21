ColourBlocks

Simple game for moving blocks

The project contains 

    1. Passwords are hashed with BCrypt before storage.
    2. JWT-based stateless auth with a filter and protected routes.
    3. Input validation on AuthRequest (@NotBlank).
    4. Centralized errors via GlobalExceptionHandler and ApiErrorResponse.
    5. /add endpoint response returns only username (not the password hash) and add the username and hasged password to the         db
    6. ddl-auto=none — schema is not auto-altered at startup appropriate since i'll manage DB schema myself.

Core platform

    Java 17
    Maven (build)
    Spring Boot 4.0.3
    
Backend framework
    
    Spring Web MVC — REST API (/login, /add)
    Spring Security — auth, JWT filter, BCrypt
    Spring Data JPA — database access
    Hibernate — ORM (JPA provider)
    Jakarta Validation — request validation (@NotBlank, @Valid)
    Spring Boot Actuator — health endpoint (/actuator/health)
    
Database
    
    MariaDB — production/local DB (planner on localhost:3306)
    MariaDB JDBC driver — DB connectivity
    H2 — test dependency only (in-memory DB for tests)
    
Security & auth

    JWT (JJWT 0.11.5) — token create/parse (jjwt-api, jjwt-impl, jjwt-jackson)
    BCrypt — password hashing (via Spring Security’s BCryptPasswordEncoder)
    
Dev & test

    Spring Boot DevTools — hot reload in development
    JUnit 5 — tests (spring-boot-starter-*-test)
    
Runtime (included with Spring Boot)

    Embedded Tomcat — HTTP server (default port 8080)
    
API style

    REST + JSON request/response bodies
