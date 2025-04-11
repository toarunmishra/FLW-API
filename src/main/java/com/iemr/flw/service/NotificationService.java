package com.iemr.flw.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.flw.domain.identity.BenHealthIDDetails;
import com.iemr.flw.mapper.InputMapper;
import com.iemr.flw.utils.config.ConfigProperties;
import com.iemr.flw.utils.http.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    @Value("${notificationurl}")
    private String NOTIFICATION_URL;

    public String sendNotification(String auth,String appType, String topic, String title, String body, String redirect) {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        headers.add("AUTHORIZATION", auth);
        headers.add("Cookie", "Jwttoken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5OTAwMDAwMDAxIiwidXNlcklkIjoiMTIwNSIsImlhdCI6MTc0NDI4NjE1NiwiZXhwIjoxNzQ0MzcyNTU2fQ.1fDRuSNGrcle_Okwi9a_eyCnaPb_h4evDRx8ubHRdYg");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("appType", appType);
        requestBody.put("topic", topic);
        requestBody.put("title", title);
        requestBody.put("body", body);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("NotificationTypeId", "flw");
        requestBody.put("data", dataMap);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(requestBody);  // this is proper JSON

        HttpEntity<Object> request = new HttpEntity<Object>(jsonRequest, headers);


        ResponseEntity<String> response = restTemplate.exchange(NOTIFICATION_URL, HttpMethod.POST, request,
                String.class);
        return response.getBody();


    }
}
