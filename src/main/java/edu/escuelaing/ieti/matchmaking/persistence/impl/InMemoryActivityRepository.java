package edu.escuelaing.ieti.matchmaking.persistence.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
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
    public Activity create(Activity activity) throws EntityExistsException {
        String activityId = activity.getId();
        if (activityMap.containsKey(activityId)) {
            throw new EntityExistsException(Activity.class, "Activity", activity.toString());
        }
        activityMap.put(activityId, activity);
        return activity;
    }

    @Override
    public Activity update(Activity activity) throws EntityNotFoundException {
        String activityId = activity.getId();
        if (!activityMap.containsKey(activityId)){
            throw new EntityNotFoundException(Activity.class, "Activity", activity.toString());
        }
        activityMap.put(activityId, activity);
        return activity;
    }

    @Override
    public Activity getById(String activityId) throws EntityNotFoundException {
        Activity activityFound = activityMap.get(activityId);
        if (activityFound == null){
			throw new EntityNotFoundException(Activity.class, "id Activity", activityId);
        }
        return activityFound;
    }

    @Override
    public void remove(String activityId) throws EntityNotFoundException {
        if (!activityMap.containsKey(activityId)){
			throw new EntityNotFoundException(Activity.class, "id Activity", activityId);
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
