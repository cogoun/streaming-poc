package com.cogoun.streaming.task;

import com.cogoun.streaming.domain.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<TaskEntity, String> {
}
