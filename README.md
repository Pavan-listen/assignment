# FealtyX - Student Management REST API with Ollama AI Integration

This project is a Spring Boot / Go / Python-based REST API that supports CRUD operations for student data management. It also includes integration with the **Ollama AI API** to generate intelligent summaries based on student information.

## Features

- Create, Read, Update, and Delete student records
- Fetch all students or by ID
- AI-generated summaries of student data using Ollama
- Secure and scalable RESTful architecture
- Built using [Java Spring Boot] / [Go] / [Python FastAPI or Flask]

---

## Technologies Used

- Java 17 / Go 1.x / Python 3.10+
- Spring Boot / Go HTTP / FastAPI
- Maven or Gradle (for Java)
- PostgreSQL / MySQL / SQLite
- Ollama (LLM inference API)

---

## API Endpoints

### Student Management

| Method | Endpoint        | Description             |
|--------|------------------|-------------------------|
| GET    | `/students`      | Get all students        |
| GET    | `/students/{id}` | Get student by ID       |
| POST   | `/students`      | Add new student         |
| PUT    | `/students/{id}` | Update student info     |
| DELETE | `/students/{id}` | Delete student by ID    |

### AI Summary

| Method | Endpoint                  | Description                        |
|--------|----------------------------|------------------------------------|
| GET    | `/students/{id}/summary`   | Generate AI summary via Ollama     |

---

## Getting Started

### Prerequisites

- Java JDK / Go / Python
- IDE (IntelliJ / VSCode)
- Docker (optional for DB)
- Ollama installed or API token available

### Setup (Java Example)

```bash
git clone https://github.com/your-username/fealtyx-student-api.git
cd fealtyx-student-api
./mvnw clean install
./mvnw spring-boot:run
