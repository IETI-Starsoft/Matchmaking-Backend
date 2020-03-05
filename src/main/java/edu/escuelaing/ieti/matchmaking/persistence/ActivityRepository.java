package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;

import java.util.List;

public interface ActivityRepository {

    Activity create(Activity activity) throws MatchmakingException;

    Activity update(Activity activity) throws MatchmakingException;

    Activity getById(String activityId) throws MatchmakingException;

    void remove(String activityId) throws MatchmakingException;

    List<Activity> getAll();

}
