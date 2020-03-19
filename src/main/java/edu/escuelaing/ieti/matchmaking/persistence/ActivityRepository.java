package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

    Activity save(Activity activity);

    Optional<Activity> findById(String activityId);

    void delete(Activity activity);

    List<Activity> findAll();

    boolean existsById(String id); 

}
