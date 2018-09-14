package com.cogoun.streaming.domain;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Collaboration")
public class Collaboration implements Serializable {

    private long id;
    private String subject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Collaboration{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }
}
