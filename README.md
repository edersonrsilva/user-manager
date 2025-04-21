# Junie Command
Write/update a README.md about the project

# User Manager

## Overview
User Manager is a Spring Boot application that provides a RESTful API for managing user data. It implements CRUD operations (Create, Read, Update, Delete) for user entities with fields such as name, address, phone number, and email.

## Features
- Create new users
- Retrieve user information (single user or all users)
- Update existing user details
- Delete users
- API documentation with Swagger/OpenAPI
- Logging aspect for monitoring API calls
- Exception handling for proper error responses

## Tech Stack
- **Java 21**: Core programming language
- **Spring Boot 3.4.4**: Application framework
- **Spring Data JPA**: Data access layer
- **H2 Database**: In-memory database for development
- **Swagger/OpenAPI**: API documentation
- **JUnit 5 & Mockito**: Testing framework
- **Gradle**: Build tool
- **Lombok**: Reduces boilerplate code
- **Spring AOP**: For cross-cutting concerns like logging

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

## Getting Started

### Prerequisites
- Java 21 or higher
- Gradle (or use the included Gradle wrapper)

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/yourusername/user-manager.git
   cd user-manager
   ```

2. Build the project:
   ```
   ./gradlew build
   ```

### Running the Application
1. Start the application:
   ```
   ./gradlew bootRun
   ```

2. The application will be available at:
   - API: http://localhost:9090/api/users
   - Swagger UI: http://localhost:9090/swagger-ui.html
   - API Docs: http://localhost:9090/api-docs

## API Endpoints

| Method | URL                  | Description                 |
|--------|----------------------|-----------------------------|
| GET    | /api/users           | Get all users               |
| GET    | /api/users/{id}      | Get a specific user by ID   |
| POST   | /api/users           | Create a new user           |
| PUT    | /api/users/{id}      | Update an existing user     |
| DELETE | /api/users/{id}      | Delete a user               |

### Example Requests

#### Create a User
```bash
curl -X POST http://localhost:9090/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","address":"123 Main St","number":"555-1234","email":"john@example.com"}'
```

#### Get All Users
```bash
curl -X GET http://localhost:9090/api/users
```

## Testing
Run the tests with:
```
./gradlew test
```

## Development Guidelines
- Follow the standard Spring Boot project structure
- Use dependency injection for loose coupling
- Implement proper exception handling
- Document API endpoints with OpenAPI annotations
- Use appropriate HTTP status codes
- Write comprehensive tests
- Keep controllers thin and put business logic in services
- Use DTOs to separate API models from entity models when needed

## License
This project is licensed under the MIT License - see the LICENSE file for details.