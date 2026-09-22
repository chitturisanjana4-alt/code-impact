package com.codeimpact.backend;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@CrossOrigin
@RestController
public class FailureController {

    private final Map<String, List<String>> reverseDependencies = Map.of(
            "Frontend", List.of(),
            "API Gateway", List.of("Frontend"),
            "Authentication", List.of("API Gateway"),
            "Payment Service", List.of("API Gateway"),
            "Database", List.of("Authentication", "Payment Service")
    );

    @GetMapping("/api/failure")
    public Map<String, Object> simulateFailure(
            @RequestParam String service) {

        List<String> affectedServices = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(service);

        while (!queue.isEmpty()) {
            String currentService = queue.poll();

            for (String dependent : reverseDependencies.getOrDefault(
                    currentService, List.of())) {

                if (!affectedServices.contains(dependent)) {
                    affectedServices.add(dependent);
                    queue.add(dependent);
                }
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("failedService", service);
        response.put("affectedServices", affectedServices);
        response.put("algorithm", "BFS");

        return response;
    }
}


