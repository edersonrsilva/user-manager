# Junie Command
Analyze the project structure and tech stack and create a .junie/guidelines.md file with concise, well-structured information to help new developers. Include guidance on organizing the structure, running tests, executing scripts, and following best practices. Keep the content short, clear, and practical.

# User Manager - Developer Guidelines

## Project Overview
User Manager is a Spring Boot application that provides REST API endpoints for managing user data. It implements CRUD operations (Create, Read, Update, Delete) for user entities.

## Tech Stack
- **Java 21**: Core programming language
- **Spring Boot 3.4.4**: Application framework
- **Spring Data JPA**: Data access layer
- **H2 Database**: In-memory database for development
- **Swagger/OpenAPI**: API documentation
- **JUnit 5 & Mockito**: Testing framework
- **Gradle**: Build tool
- **Lombok**: Reduces boilerplate code

## Project Structure
```
src/
├── main/
│   ├── java/com/example/usermanager/
│   │   ├── aspect/         # AOP aspects (e.g., logging)
│   │   ├── config/         # Configuration classes
│   │   ├── controller/     # REST controllers
│   │   ├── exception/      # Custom exceptions and handlers
│   │   ├── model/          # Entity classes
│   │   ├── repository/     # Data access interfaces
│   │   ├── service/        # Business logic interfaces and implementations
│   │   └── UserManagerApplication.java  # Application entry point
│   └── resources/
│       └── application.yml  # Application configuration
└── test/
    └── java/com/example/usermanager/
        ├── controller/     # Controller tests
        ├── service/        # Service tests
        └── UserManagerApplicationTests.java  # Integration tests
```

## Setup Instructions
1. Ensure Java 21 is installed
2. Clone the repository
3. Build the project: `./gradlew build`

## Running the Application
1. Start the application: `./gradlew bootRun`
2. Access the API at: http://localhost:9090/api/users
3. Access Swagger UI at: http://localhost:9090/swagger-ui.html

## Testing Guidelines
- Unit tests should be written for all services and controllers
- Follow the Arrange-Act-Assert pattern
- Use Mockito to mock dependencies
- Test both success and failure scenarios
- Run tests with: `./gradlew test`

## Best Practices
- Follow the standard Spring Boot project structure
- Use dependency injection for loose coupling
- Implement proper exception handling
- Document API endpoints with OpenAPI annotations
- Use appropriate HTTP status codes
- Write comprehensive tests
- Keep controllers thin and put business logic in services
- Use DTOs to separate API models from entity models when needed