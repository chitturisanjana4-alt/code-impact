package com.codeimpact.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin
@RestController
public class RecoveryController {

    @GetMapping("/api/recovery")
    public Map<String, Object> recoverSystem() {

        List<String> recoveryOrder = Arrays.asList(
                "Database",
                "Authentication",
                "Payment Service",
                "API Gateway",
                "Frontend"
        );

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("status", "RECOVERY_STARTED");
        response.put("recoveryOrder", recoveryOrder);
        response.put("algorithm", "Dependency-based Recovery");

        return response;
    }
}

