package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.domain.Collaboration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborationRepository extends CrudRepository<Collaboration, String> {
}
