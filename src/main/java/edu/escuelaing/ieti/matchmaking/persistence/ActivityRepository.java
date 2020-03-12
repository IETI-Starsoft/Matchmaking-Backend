package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;

import java.util.List;

public interface ActivityRepository {

    Activity create(Activity activity) throws EntityExistsException;

    Activity update(Activity activity) throws EntityNotFoundException;

    Activity getById(String activityId) throws EntityNotFoundException;

    void remove(String activityId) throws EntityNotFoundException;

    List<Activity> getAll();

}
