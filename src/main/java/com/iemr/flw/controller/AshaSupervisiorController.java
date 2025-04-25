package com.iemr.flw.controller;

import com.iemr.flw.service.AshaSuperVisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

@RestController
@RequestMapping(value = "/ashaSuperVisor")
public class AshaSupervisiorController {

    @Autowired
    private AshaSuperVisorService ashaSuperVisorService;

    @RequestMapping(value = "getAllAsha", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllAsha(@Param("serviceProviderID") Integer serviceProviderID,@Param("vilageName")String vilageName) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (!ashaSuperVisorService.getAllAsha(serviceProviderID,vilageName).isEmpty()) {
                response.put("statusCode", 200);
                response.put("message", "Success");
                response.put("data", ashaSuperVisorService.getAllAsha(serviceProviderID,vilageName));
            } else {
                response.put("statusCode", 201);
                response.put("message", "Data not found");
            }

        } catch (Exception e) {
            response.put("statusCode", 500);
            response.put("error", e.getMessage());

        }
        return ResponseEntity.ok(response);
    }

}
