package com.iemr.flw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    @Value("${airtel.api.url}")
    private String apiUrl;

    @Value("${airtel.api.customerId}")
    private String customerId;

    @Value("${airtel.api.sourceAddress}")
    private String sourceAddress;

    @Value("${airtel.api.dltTemplateId}")
    private String dltTemplateId;

    @Value("${airtel.api.entityId}")
    private String entityId;

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendSms(String phoneNumber, String applicationId) {
        try {
            // Build payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("customerId", customerId);
            payload.put("destinationAddress", phoneNumber);
            payload.put("message", "Dear Citizen, Your application ID: {#var#} has been submitted for benefit disbursement. You'll be notified once processed. Regards PSMRIAM.");
            payload.put("sourceAddress", sourceAddress);
            payload.put("messageType", "SERVICE_IMPLICIT");
            payload.put("dltTemplateId", dltTemplateId);
            payload.put("entityId", entityId);
            payload.put("otp", true);

            Map<String, Object> metaData = new HashMap<>();
            metaData.put("var", applicationId);
            payload.put("metaData", metaData);

            // Set headers
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            // Call API
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

            // Return response
            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending SMS: " + e.getMessage();
        }
    }


}
