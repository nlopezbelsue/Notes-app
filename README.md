Notes Application

This repository contains a full‑stack Notes application with a Spring Boot backend, a JavaScript frontend, and a PostgreSQL database running in Docker.

Requirements

To run this project you need the following tools installed:

Backend

Java JDK 17

Spring Boot 3.x (via Maven Wrapper)

Frontend

Node.js 18.x

npm 9.x

Database

Docker 24.x or later

Docker Compose v2

PostgreSQL 15 (runs inside Docker, no local installation required)

Operating System

Linux or macOS

Project Structure
root/
├── docker-compose.yml
├── run.sh
├── backend/
│   └── notes/
│       └── mvnw
└── frontend/
How to Run the Application

The application can be started with one command using the provided bash script.

1. Make the script executable
chmod +x run.sh
2. Run the application
./run.sh

This script will:

Start PostgreSQL using Docker Compose

Start the Spring Boot backend

Start the frontend development server

Manual Execution (Optional)

If you prefer to run everything manually:

Start the database

From the project root:

docker-compose up -d
Start the backend
cd backend/notes
./mvnw spring-boot:run
Start the frontend
cd frontend
npm install
npm run dev
Backend Configuration

ORM: Spring Data JPA / Hibernate

Database: PostgreSQL

Persistence is fully managed by JPA (no in‑memory storage)

Schema is automatically created/updated on startup

Database

PostgreSQL runs in a Docker container

Data is persisted using Docker volumes

No local PostgreSQL installation is required

Ports
Service	Port
Backend	8080
Frontend	5173
PostgreSQL	5432
Notes

The application uses an ORM with a relational database as required

Docker ensures database persistence across restarts

Maven Wrapper is used to avoid requiring a local Maven installation