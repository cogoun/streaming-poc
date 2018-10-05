package com.cogoun.streaming.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class TestActuators {

    private static final Logger LOG = LoggerFactory.getLogger(TestActuators.class);

    private String[] actuatorUrls;

    public TestActuators(String... actuatorUrls) {
        this.actuatorUrls = actuatorUrls;
    }

    public String[] test() {
        String[] results = new String[actuatorUrls.length];
        RestTemplate restTemplate = new RestTemplate();
        for (int i=0; i<actuatorUrls.length; i++) {
            ResponseEntity<String> response = null;
            try {
                response = restTemplate.getForEntity(actuatorUrls[i], String.class);
                results[i] = actuatorUrls[i] + ": " + response.getStatusCode().name() + ", " + response.getStatusCode().getReasonPhrase();
            } catch (RestClientException e) {
                results[i] = actuatorUrls[i] + ": " + e.getMessage();
            }
        }

        return results;
    }
}
