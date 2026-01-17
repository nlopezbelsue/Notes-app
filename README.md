# 📝 Notes App

A full-stack notes application built with **Spring Boot**, **PostgreSQL**, and **React**.

---

## 🚀 Features

- Create, edit, archive and delete notes
- Create categories and assign them to notes
- Persistent storage using PostgreSQL (Dockerized)
- SPA frontend built with React

---

## 🛠 Tech Stack

### Backend
- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- PostgreSQL 15
- Maven Wrapper

### Frontend
- Node.js 18
- React
- Vite

### Infrastructure
- Docker
- Docker Compose

---

## 🚀 How to Run the Application

You can start the entire application with a single command using the provided bash script.

### ▶️ Quick Start (Recommended)

1. Make the script executable:
   ```bash
   chmod +x run.sh
   ```

2. Run the application:
   ```bash
   ./run.sh
   ```

This script will automatically:

- 🐘 Start PostgreSQL using Docker Compose  
- ☕ Start the Spring Boot backend  
- 🌐 Start the frontend development server  

---

## ⚙️ Manual Execution (Optional)

If you prefer to run each component manually, follow the steps below.

### 1️⃣ Start the Database

From the project root directory:

```bash
docker-compose up -d
```

### 2️⃣ Start the Backend

```bash
cd backend/notes
./mvnw spring-boot:run
```

### 3️⃣ Start the Frontend

```bash
cd frontend
npm install
npm run dev
```

## 🛠 Backend Configuration

- **ORM:** Spring Data JPA / Hibernate  
- **Database:** PostgreSQL  
- **Persistence:** Fully managed by JPA (no in-memory storage)  
- **Schema Management:** Automatically created and updated on application startup  

---

## 🐘 Database

- PostgreSQL runs inside a **Docker container**  
- Data is persisted using **Docker volumes**  
- ❌ No local PostgreSQL installation is required
