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
| Field         | Value                |
| ------------- | -------------------- |
| Table name    | `StudentTable`       |
| Partition key | `studentId (String)` |


Create Lambda Function

Go to AWS Lambda → Create function

Choose: Author from scratch
Function name: studentapplication
Runtime: Java 17
Role: Create new role with basic Lambda permissions
Click Create function
Then:
Upload the generated JAR (student-lambda-api-1.0-SNAPSHOT-shaded.jar)
Set Handler to:  com.tushar.studentlambda.StudentHandler::handleRequest


Grant DynamoDB Access to Lambda

Go to:
IAM → Roles → studentapplication-role → Add permissions   ------>>> AmazonDynamoDBFullAccess

Create API Gateway

Go to API Gateway → Create API → REST API
Create a resource: /students
Add two methods:
POST → Integration type: Lambda Function → studentapplication
GET → Integration type: Lambda Function → studentapplication
Deploy API:
Actions → Deploy API → New Stage → dev
Copy the Invoke URL, e.g.:  https://8altgejrbh.example-api.us-east-1.amazonaws.com/dev

Now test in postman :- 
save student details   ------>>>>> POST  \
https://8algejrb.example-api.us-east-1.amazonaws.com/dev/students  \
{
  "name": "Tushar",\
  "email": "tushar@example.com",\
  "department": "Computer Science"\
}


get student details   --------->>> GET\
https://8algejrb.example-api.us-east-1.amazonaws.com/dev/students?studentId=07ba63c0-af60-4bd7-90af-ea43d9167f15\







