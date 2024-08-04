package com.example.github_resume_builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProjectDescription(String projectTitle) {
        // Update URL based on the correct API endpoint
        String url = "https://api.gemini.com/v1/projects/description";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.set("Content-Type", "application/json");

        // Construct the request body according to API specifications
        String requestBody = "{\"title\": \"" + projectTitle + "\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                // Log and handle non-successful responses
                throw new RuntimeException("Failed to fetch project description. Status code: " + response.getStatusCode() + ", Response body: " + response.getBody());
            }
        } catch (Exception e) {
            // Log and handle exceptions
            e.printStackTrace();
            throw new RuntimeException("Error during API call", e);
        }
    }
}
