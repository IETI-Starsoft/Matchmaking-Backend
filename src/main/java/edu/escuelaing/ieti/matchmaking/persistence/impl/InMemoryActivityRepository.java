package edu.escuelaing.ieti.matchmaking.persistence.impl;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.persistence.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryActivityRepository implements ActivityRepository {

    private static Map<String, Activity> activityMap = new ConcurrentHashMap<>();

    @Override
    public Activity create(Activity activity) throws MatchmakingException {
        String activityId = activity.getId();
        if (activityMap.containsKey(activityId)) {
            throw new MatchmakingException(MatchmakingException.ActivityAlreadyExist);
        }
        activityMap.put(activityId, activity);
        return activity;
    }

    @Override
    public Activity update(Activity activity) throws MatchmakingException {
        String activityId = activity.getId();
        if (!activityMap.containsKey(activityId)){
            throw new MatchmakingException(MatchmakingException.ActivityNotFound);
        }
        activityMap.put(activityId, activity);
        return activity;
    }

    @Override
    public Activity getById(String activityId) throws MatchmakingException {
        Activity activityFound = activityMap.get(activityId);
        if (activityFound == null){
            throw new MatchmakingException(MatchmakingException.ActivityNotFound);
        }
        return activityFound;
    }

    @Override
    public void remove(String activityId) throws MatchmakingException {
        if (!activityMap.containsKey(activityId)){
            throw new MatchmakingException(MatchmakingException.ActivityNotFound);
        }
        activityMap.remove(activityId);
    }

    @Override
    public List<Activity> getAll() {
        Set<Map.Entry<String, Activity>> entrySet = activityMap.entrySet();
        List<Activity> activitys = new ArrayList<>();
        for (Map.Entry<String, Activity> entry : entrySet) {
            activitys.add(entry.getValue());
        }
        return activitys;
    }
}
