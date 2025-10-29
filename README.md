# StudentManagmentApplicationUsingAwsLambda


📘 Student Lambda API — Serverless Java Project

A Serverless CRUD API built using AWS Lambda, API Gateway, and DynamoDB in Java.
This project demonstrates how to deploy Java functions to AWS Lambda and expose them as REST APIs using API Gateway — without running any servers.

🏗️ Project Overview

| Component         | Technology                         |
| ----------------- | ---------------------------------- |
| Language          | Java 17+                           |
| Build Tool        | Maven                              |
| AWS Services      | Lambda, API Gateway, DynamoDB, IAM |
| API Type          | REST                               |
| IDE (recommended) | IntelliJ IDEA                      |
| Deployment        | AWS Console (manual)               |


⚙️ Features

✅ Create a new student record (POST)
✅ Retrieve student details by ID (GET)
✅ Store data in DynamoDB
✅ Serverless — no EC2 or servers needed
✅ IAM Role-based access for secure DynamoDB actions



🧩 API Endpoints

| Method | Endpoint                   | Description               |
| ------ | -------------------------- | ------------------------- |
| `POST` | `/students`                | Create a new student      |
| `GET`  | `/students?studentId={id}` | Get student details by ID |


Example JSON (POST request)

{
  "name": "Tushar",\
  "email": "tushar@example.com",\
  "department": "Computer Science"\
}


Example Response (POST)

{
  "studentId": "d222cb7b-1358-4e6f-bffe-5e3977112366",\
  "name": "xyz",\
  "email": "xyz@example.com",\
  "department": "Computer Science"\
}

DynamoDB Table Configuration 

Create DynamoDB Table

Go to AWS Console → DynamoDB → Tables → Create table


