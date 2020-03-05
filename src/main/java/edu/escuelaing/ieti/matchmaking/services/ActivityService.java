package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;

public interface ActivityService {

	Activity create(Activity activity) throws MatchmakingException;

    void remove(String activityId) throws MatchmakingException;

    Activity update(Activity activity) throws MatchmakingException;

    Activity getActivityById(String activityId) throws MatchmakingException;

    List<Activity> getAll();

}