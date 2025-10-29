package org.example.studentlambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Student;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class StudentHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {


    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();
    private final String TABLE_NAME = "StudentTable";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String httpMethod = request.getHttpMethod();
        try {
            switch (httpMethod) {
                case "POST":
                    return createStudent(request);
                case "GET":
                    return getStudent(request);
                default:
                    return buildResponse(400, "Unsupported method");
            }
        } catch (Exception e) {
            return buildResponse(500, e.getMessage());
        }
    }

    private APIGatewayProxyResponseEvent createStudent(APIGatewayProxyRequestEvent request) throws Exception {
        Student student = objectMapper.readValue(request.getBody(), Student.class);
        student.setStudentId(UUID.randomUUID().toString());

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("studentId", AttributeValue.builder().s(student.getStudentId()).build());
        item.put("name", AttributeValue.builder().s(student.getName()).build());
        item.put("email", AttributeValue.builder().s(student.getEmail()).build());
        item.put("department", AttributeValue.builder().s(student.getDepartment()).build());

        dynamoDbClient.putItem(PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build());

        return buildResponse(200, objectMapper.writeValueAsString(student));
    }

    private APIGatewayProxyResponseEvent getStudent(APIGatewayProxyRequestEvent request) throws Exception {
        String id = request.getQueryStringParameters().get("studentId");

        Map<String, AttributeValue> key = Map.of("studentId", AttributeValue.builder().s(id).build());
        GetItemResponse response = dynamoDbClient.getItem(GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build());

        if (!response.hasItem()) {
            return buildResponse(404, "Student not found");
        }

        // ✅ Convert DynamoDB AttributeValue map to a normal map
        Map<String, String> student = new HashMap<>();
        response.item().forEach((k, v) -> student.put(k, v.s()));

        return buildResponse(200, objectMapper.writeValueAsString(student));
    }


    private APIGatewayProxyResponseEvent buildResponse(int statusCode, String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(body)
                .withHeaders(Map.of("Content-Type", "application/json"));
    }


}
