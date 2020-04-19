package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.State;

public interface ActivityService {

    Activity create(Activity activity);

    void remove(Activity activity) throws EntityNotFoundException;

    Activity update(Activity activity) throws EntityNotFoundException;

    Activity getActivityById(String activityId) throws EntityNotFoundException;

    List<Activity> getAll();

    List<Activity> getActivities(String userId, State state);

    List<Activity> getAvailableActivitiesByTypeActiviti(String userId, Filter filter);

    List<Activity> getAvailableActivitiesByParticipants(String userId, Filter filter);

    List<Activity> getAvailableActivitiesByRangeCredrits(String userId, Filter filter);

    List<Activity> getAvailableActivitiesFilter(String userId, Filter filter);

    Activity winnerActivity(String activityId, String winner) throws EntityNotFoundException, MatchmakingException;

    Activity loserActivity(String activityId, String loser) throws EntityNotFoundException, MatchmakingException;

}