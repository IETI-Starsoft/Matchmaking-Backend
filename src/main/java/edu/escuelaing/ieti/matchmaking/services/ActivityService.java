package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.State;

public interface ActivityService {

	Activity create(Activity activity);

    void remove(Activity activity) throws EntityNotFoundException;

    Activity update(Activity activity) throws EntityNotFoundException;

    Activity getActivityById(String activityId) throws EntityNotFoundException;

    List<Activity> getAll();

    List<Activity> getActivities(String userId,State state);

}