# Task Tracking System Backend: Architecture & Design

---

## 1. Technology Stack
- **Language:** Java
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Database:** MySQL (Spring Data JPA)
- **Security:** Spring Security with JWT
- **File Uploads:** Disk storage, static serving
- **API Style:** RESTful

---

## 2. High-Level Architecture

```
[Client]
   |
   v
[Controller Layer]  <--- REST API endpoints
   |
   v
[Service Layer]     <--- Business logic
   |
   v
[Repository Layer]  <--- Data access (JPA)
   |
   v
[Database]          <--- MySQL
```

---

## 3. Main Packages & Classes

- **model/**: User, Task, Team, Comment, Attachment
- **repository/**: JPA repositories for each model
- **dto/**: Data Transfer Objects for API
- **service/**: Business logic interfaces and implementations
- **Controller/**: REST API endpoints
- **security/**: JWT, authentication, security config
- **util/**: File upload utility
- **exception/**: Global exception handler

---

## 4. Class-Level Architecture Diagram

```mermaid
classDiagram
    class User {
        Long id
        String username
        String email
        String password
        Set~String~ roles
        Set~Team~ teams
        Set~Task~ tasks
    }
    class Task {
        Long id
        String title
        String description
        LocalDate dueDate
        Status status
        User assignee
        Team project
        List~Comment~ comments
        List~Attachment~ attachments
    }
    class Team {
        Long id
        String name
        Set~User~ members
        Set~Task~ tasks
    }
    class Comment {
        Long id
        String content
        User author
        Task task
        LocalDateTime timestamp
    }
    class Attachment {
        Long id
        String filename
        String url
        Task task
    }

    User "1" -- "0..*" Task : assigned
    User "1" -- "0..*" Team : member
    Team "1" -- "0..*" Task : contains
    Task "1" -- "0..*" Comment : has
    Task "1" -- "0..*" Attachment : has
    Comment "1" -- "1" User : author
    Attachment "1" -- "1" Task : belongs to
```

---

## 5. Detailed Sequence Diagram: User Registration & Task Assignment

### a) User Registration
```mermaid
sequenceDiagram
    participant Client
    participant AuthController
    participant UserService
    participant UserRepository
    participant Database

    Client->>AuthController: POST /api/auth/register (UserDTO)
    AuthController->>UserService: register(UserDTO)
    UserService->>UserRepository: save(User)
    UserRepository->>Database: INSERT User
    Database-->>UserRepository: User saved
    UserRepository-->>UserService: User
    UserService-->>AuthController: User
    AuthController-->>Client: 200 OK (User)
```

### b) Task Assignment
```mermaid
sequenceDiagram
    participant Client
    participant TaskController
    participant TaskService
    participant TaskRepository
    participant UserRepository
    participant Database

    Client->>TaskController: POST /api/tasks/{taskId}/assign/{userId}
    TaskController->>TaskService: assignTask(taskId, userId)
    TaskService->>TaskRepository: findById(taskId)
    TaskRepository->>Database: SELECT Task
    Database-->>TaskRepository: Task
    TaskService->>UserRepository: findById(userId)
    UserRepository->>Database: SELECT User
    Database-->>UserRepository: User
    TaskService->>TaskRepository: save(Task)
    TaskRepository->>Database: UPDATE Task
    Database-->>TaskRepository: Task updated
    TaskRepository-->>TaskService: Task
    TaskService-->>TaskController: Task
    TaskController-->>Client: 200 OK (Task)
```

---

## 6. Security & Authentication Flow
- Registration: User registers with username, email, password (hashed), roles
- Login: User logs in, receives JWT
- JWT: Sent in Authorization header for protected endpoints
- Spring Security: Validates JWT, loads user details, enforces access

---

## 7. File Uploads
- Attachments uploaded via `/api/attachments/upload/{taskId}`
- Files saved to `uploads/` directory
- URLs stored in DB, served as static resources

---

## 8. Error Handling & Validation
- GlobalExceptionHandler returns clean error messages
- DTOs can use validation annotations for request validation

---

## 9. Example API Usage
See `API_TESTING.md` for ready-to-use curl/Postman requests for all endpoints.

---

## 10. Project Setup
- Configure MySQL in `application.properties`
- Run `mvn clean install` to build
- Start with `mvn spring-boot:run`
- Use the API as documented 
