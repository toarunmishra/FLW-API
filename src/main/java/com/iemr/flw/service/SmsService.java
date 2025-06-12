package com.iemr.flw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
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
            String message = "Hello! Your OTP for providing consent for registration on AMRIT is {#OTP#}. This OTP is valid for 10 minutes. Kindly share it only with {#User Name, Designation#} to complete the process. PSMRI";
            // Build payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("customerId", customerId);
            payload.put("destinationAddress", phoneNumber);
            payload.put("message", message);
            payload.put("sourceAddress", sourceAddress);
            payload.put("messageType", "SERVICE_IMPLICIT");
            payload.put("dltTemplateId", dltTemplateId);
            payload.put("entityId", entityId);
            payload.put("otp", true);
            payload.put("creditFlag", true);
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("User Name", "Sunita");
            metaData.put("Designation", "ASHA");
            payload.put("metaData", metaData);
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            String auth = customerId + ":" + "]Kt9GAp8}$S*@";
            headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));
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
