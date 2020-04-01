package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    boolean existsById(String id); 
}
