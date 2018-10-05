package com.cogoun.streaming;

import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.tests.EmulateHighLoad;
import com.cogoun.streaming.tests.LoadTest;
import com.cogoun.streaming.tests.TestActuators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private static final String ENDPOINT_URL = "http://localhost:8092/collaboration";

    public static void main(String args[]) {

//        LoadTest highLoadTest = new EmulateHighLoad(100000, 200);
//        highLoadTest.test(ENDPOINT_URL);

        TestActuators testActuators = new TestActuators(
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
        );

        Arrays.stream(testActuators.test()).forEach(r -> log.info(r));
    }
}
