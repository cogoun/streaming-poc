package com.cogoun.streaming.task;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface TaskIndexingRepository extends ElasticsearchRepository<TaskEntity, String> {
}
