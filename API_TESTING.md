# Task Tracking System API Example Requests

This document provides example HTTP requests for testing the main API endpoints of your Task Tracking System backend. You can use these with curl, Postman, or any HTTP client.

---

## 1. User Registration

**POST** `/api/auth/register`
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "email": "alice@example.com",
    "password": "password123",
    "roles": ["USER"]
  }'
```

---

## 2. User Login

**POST** `/api/auth/login`
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "password": "password123"
  }'
```
**Response:**
`Bearer <JWT_TOKEN>`

---

## 3. Get User Profile

**GET** `/api/auth/profile`
```bash
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

---

## 4. Update User Profile

**PUT** `/api/auth/profile`
```bash
curl -X PUT http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "email": "alice_new@example.com",
    "password": "newpassword123"
  }'
```

---

## 5. Create a Task

**POST** `/api/tasks`
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Finish backend",
    "description": "Complete the backend implementation",
    "dueDate": "2024-06-30",
    "status": "OPEN"
  }'
```

---

## 6. Assign a Task

**POST** `/api/tasks/{taskId}/assign/{userId}`
```bash
curl -X POST http://localhost:8080/api/tasks/1/assign/2 \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

---

## 7. Get All Tasks

**GET** `/api/tasks`
```bash
curl -X GET http://localhost:8080/api/tasks \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

---

## 8. Create a Team

**POST** `/api/teams`
```bash
curl -X POST http://localhost:8080/api/teams \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dev Team",
    "memberIds": [1,2]
  }'
```

---

## 9. Add a Comment to a Task

**POST** `/api/comments`
```bash
curl -X POST http://localhost:8080/api/comments \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "content": "This task is urgent!",
    "authorId": 1,
    "taskId": 1
  }'
```

---

## 10. Upload an Attachment to a Task

**POST** `/api/attachments/upload/{taskId}`
```bash
curl -X POST http://localhost:8080/api/attachments/upload/1 \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -F "file=@/path/to/your/file.pdf"
```

---

## 11. Get Attachments for a Task

**GET** `/api/attachments/task/{taskId}`
```bash
curl -X GET http://localhost:8080/api/attachments/task/1 \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

---

**Tip:**
- Replace `<JWT_TOKEN>` with the token you get from the login endpoint.
- Replace IDs (`1`, `2`, etc.) with actual IDs from your database. 