package com.cogoun.streaming.task;

import com.cogoun.streaming.domain.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ElasticsearchRepository<Task, String> {
}
