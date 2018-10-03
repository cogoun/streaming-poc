package com.cogoun.streaming.task;

import com.cogoun.streaming.domain.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskIndexingRepository extends ElasticsearchRepository<TaskEntity, String> {
}
