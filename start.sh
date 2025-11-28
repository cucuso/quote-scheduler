#!/bin/bash

echo "Starting Booking Scheduler Application..."
echo "=========================================="
echo ""
echo "Building application..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "Build successful! Starting application..."
    echo ""
    echo "Access points:"
    echo "  - Booking Form: http://localhost:8080/form"
    echo "  - Calendar View: http://localhost:8080/calendar/1"
    echo "  - H2 Console: http://localhost:8080/h2-console"
    echo ""
    echo "Press Ctrl+C to stop the application"
    echo "=========================================="
    echo ""

    java -jar target/scheduler-1.0-SNAPSHOT.jar
else
    echo "Build failed! Please check the error messages above."
    exit 1
fi