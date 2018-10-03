package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.domain.Collaboration;
import com.cogoun.streaming.event.CollaborationEvent;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("Collaboration")
public class CollaborationEntity extends Collaboration implements Serializable {

    @Override
    public String toString() {
        return super.toString();
    }
}
