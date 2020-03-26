package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;

public interface ActivityService {

	Activity create(Activity activity);

    void remove(Activity activity) throws EntityNotFoundException;

    Activity update(Activity activity) throws EntityNotFoundException;

    Activity getActivityById(String activityId) throws EntityNotFoundException;

    List<Activity> getAll();

}