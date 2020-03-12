package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;

public interface ActivityService {

	Activity create(Activity activity) throws EntityExistsException;

    void remove(String activityId) throws EntityNotFoundException;

    Activity update(Activity activity) throws EntityNotFoundException;

    Activity getActivityById(String activityId) throws EntityNotFoundException;

    List<Activity> getAll();

}