package com.cogoun.streaming.tests.load;

import com.cogoun.streaming.domain.Collaboration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class EmulateHighLoad implements LoadTest {

    private static final Logger LOG = LoggerFactory.getLogger(EmulateHighLoad.class);

    private long testSize;
    private long delayInMilliseconds;

    public EmulateHighLoad(long testSize, long delayInMilliseconds) {
        this.testSize = testSize;
        this.delayInMilliseconds = delayInMilliseconds;
    }

    @Override
    public void test(String url) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i< testSize; i++) {
            restTemplate.postForObject(url,
                    RandomCollaborationBuilder.build(i),
                    Collaboration.class);
            try {
                Thread.sleep(delayInMilliseconds);
            } catch (InterruptedException e) {
                LOG.warn(e.getMessage());
            }
        }
    }
}
