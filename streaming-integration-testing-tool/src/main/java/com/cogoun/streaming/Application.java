package com.cogoun.streaming;

import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.tests.EmulateHighLoad;
import com.cogoun.streaming.tests.LoadTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private static final String ENDPOINT_URL = "http://localhost:8092/collaboration";

    public static void main(String args[]) {

        LoadTest highLoadTest = new EmulateHighLoad(100000, 200);
        highLoadTest.test(ENDPOINT_URL);
    }
}
