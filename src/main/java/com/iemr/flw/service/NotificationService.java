package com.iemr.flw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    @Value("${notificationurl}")
    private String NOTIFICATION_URL;

    public String sendNotification(String  auth) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.ALL));
        headers.setBearerAuth(auth);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("appType", "flw");
        requestBody.put("topic", "All");
        requestBody.put("title", "Hello");
        requestBody.put("body", "body");

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("additionalProp1", "string");
        dataMap.put("additionalProp2", "string");
        dataMap.put("additionalProp3", "string");

        requestBody.put("data", dataMap);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(NOTIFICATION_URL, entity, String.class);
        return response.getBody();
    }
}
