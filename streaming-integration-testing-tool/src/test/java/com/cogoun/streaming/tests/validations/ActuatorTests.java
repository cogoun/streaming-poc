package com.cogoun.streaming.tests.validations;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ActuatorTests {

    private static final Logger LOG = LoggerFactory.getLogger(ActuatorTests.class);

    private String[] actuatorUrls = {
            "http://localhost:8081/actuator/health",
            "http://localhost:8083/actuator/health",
            "http://localhost:8084/actuator/health",
            "http://localhost:8085/actuator/health",
            "http://localhost:8086/actuator/health",
            "http://localhost:8087/actuator/health",
            "http://localhost:8088/actuator/health",
            "http://localhost:8089/actuator/health",
            "http://localhost:8090/actuator/health",
            "http://localhost:8091/actuator/health",
            "http://localhost:8092/actuator/health"
    };

    @Test
    public void testHealthEndpoints() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i=0; i<actuatorUrls.length; i++) {
            ResponseEntity<String> response = null;
            try {
                response = restTemplate.getForEntity(actuatorUrls[i], String.class);
                String httpStatusName = response.getStatusCode().name();
                assertEquals(actuatorUrls[i], HttpStatus.OK.name(), httpStatusName);
            } catch (RestClientException e) {
                fail(e.getMessage());
            }
        }
    }
}
