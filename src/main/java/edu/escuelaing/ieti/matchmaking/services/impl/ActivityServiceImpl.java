package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.persistence.ActivityRepository;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.escuelaing.ieti.matchmaking.model.Activity;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository inMemoryRepository;

    @Override
    public Activity create(Activity activity) throws MatchmakingException {
        return inMemoryRepository.create(activity);
    }

    @Override
    public void remove(String activityId) throws MatchmakingException {
        inMemoryRepository.remove(activityId);
    }

    @Override
    public Activity update(Activity activity) throws MatchmakingException {
        return inMemoryRepository.update(activity);
    }

    @Override
    public Activity getActivityById(String activityId) throws MatchmakingException {
        return inMemoryRepository.getById(activityId);
    }

    @Override
    public List<Activity> getAll() {
        return inMemoryRepository.getAll();
    }
}
