package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.persistence.ActivityRepository;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import java.util.Optional;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity create(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void remove(Activity activity) throws EntityNotFoundException {
        if (!activityRepository.existsById(activity.getId())){
            throw new EntityNotFoundException(Activity.class,"Activity id", activity.getId());
        }
        activityRepository.delete(activity);
    }

    @Override
    public Activity update(Activity activity) throws EntityNotFoundException {
        if (!activityRepository.existsById(activity.getId())){
            throw new EntityNotFoundException(Activity.class,"Activity id", activity.getId());
        }
        return activityRepository.save(activity);
    }

    @Override
    public Activity getActivityById(String activityId) throws EntityNotFoundException {
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        return optionalActivity.orElseThrow(() -> new EntityNotFoundException(Activity.class, "Activity id", activityId));
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

  
}
