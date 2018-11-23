package com.cogoun.streaming.tests.load;

import org.junit.Test;

public class HighLoadIntegrationTest {

    private static final String ENDPOINT_URL = "http://localhost:8092/collaboration";

    @Test
    public void testHighLoad() {
        LoadTest highLoadTest = new EmulateHighLoad(1000, 200);
        highLoadTest.test(ENDPOINT_URL);
    }
}
