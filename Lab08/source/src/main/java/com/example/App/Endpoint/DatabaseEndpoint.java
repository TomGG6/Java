package com.example.App.Endpoint;

import com.example.App.Services.DatabaseService;
import com.techprimers.spring_boot_soap_example.GetPersonsRequest;
import com.techprimers.spring_boot_soap_example.GetPersonsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;

@Endpoint
public class DatabaseEndpoint {

    private static final String NAMESPACE_URI = "http://techprimers.com/spring-boot-soap-example";

    private DatabaseService databaseService;

    @Autowired
    public DatabaseEndpoint(DatabaseService databaseService) {
        this.databaseService =databaseService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonsRequest")
    @ResponsePayload
    public GetPersonsResponse getPersonsRequest(@RequestPayload GetPersonsRequest request) throws SQLException {
        GetPersonsResponse response = new GetPersonsResponse();
        response.setData(databaseService.getPersons());
        return response;
    }
}