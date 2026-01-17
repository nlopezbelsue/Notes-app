#!/usr/bin/env bash

set -e

echo "Starting Notes App..."

# 1. Start PostgreSQL with Docker
echo "Starting PostgreSQL container..."
docker compose up -d

# 2. Start backend
echo "Starting Spring Boot backend..."
cd backend/notes
./mvnw spring-boot:run &
BACKEND_PID=$!
cd ../../

# 3. Start frontend
echo "Starting frontend..."
cd frontend
npm install
npm run dev &
FRONTEND_PID=$!
cd ..

echo ""
echo "Application is running!"
echo "Backend:  http://localhost:8080"
echo "Frontend: http://localhost:5173"
echo ""
echo "Press Ctrl+C to stop everything."

# 4. Wait for Ctrl+C
trap "echo 'Stopping app...'; kill $BACKEND_PID $FRONTEND_PID; docker compose down" SIGINT
wait
