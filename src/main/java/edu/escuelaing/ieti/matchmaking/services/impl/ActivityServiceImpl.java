package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.persistence.ActivityRepository;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.GroupActivity;
import edu.escuelaing.ieti.matchmaking.model.IndividualActivity;
import edu.escuelaing.ieti.matchmaking.model.State;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private MongoOperations mongoOperation; 

    @Override
    public Activity create(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void remove(Activity activity) throws EntityNotFoundException {
        if (!activityRepository.existsById(activity.getId())) {
            throw new EntityNotFoundException(Activity.class, "Activity id", activity.getId());
        }
        activityRepository.delete(activity);
    }

    @Override
    public Activity update(Activity activity) throws EntityNotFoundException {
        if (!activityRepository.existsById(activity.getId())) {
            throw new EntityNotFoundException(Activity.class, "Activity id", activity.getId());
        }
        return activityRepository.save(activity);
    }

    @Override
    public Activity getActivityById(String activityId) throws EntityNotFoundException {
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        return optionalActivity
                .orElseThrow(() -> new EntityNotFoundException(Activity.class, "Activity id", activityId));
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> getActivities(String userId,State state) {
        Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(state));
        for (Activity act: mongoOperation.find(query, Activity.class)){
            if(!act.getOwner().equals(userId)){
                rta.add(act);
            } 
        }
        return rta;
    }

	@Override
	public List<Activity> getAvailableActivitiesByTypeActiviti(String userId,Filter filter) {
		Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(filter.getStateActiviti()));
        System.out.println(filter.toString());
        query.addCriteria(Criteria.where("type").is(filter.getLabels().get(0)));
        query.skip(4*filter.getPag()).limit(4);
        for (Activity act: mongoOperation.find(query, Activity.class)){
            if(!act.getOwner().equals(userId)){
                rta.add(act);
            } 
        }
        return rta;
		
	}
	@Override
	public List<Activity> getAvailableActivitiesFilter(String userId,Filter filter) {
		Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(filter.getStateActiviti()));
        query.skip(4*filter.getPag()).limit(4);
        for (Activity act: mongoOperation.find(query, Activity.class)){
            if(!act.getOwner().equals(userId)){
                rta.add(act);
            } 
        }
        return rta;
		
	}
	@Override
	public List<Activity> getAvailableActivitiesByParticipants(String userId, Filter filter) {
		Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(filter.getStateActiviti()));
        query.addCriteria(Criteria.where("_class").is("edu.escuelaing.ieti.matchmaking.model."+filter.getParticipants()));
        query.skip(4*filter.getPag()).limit(4);
        for (Activity act: mongoOperation.find(query, Activity.class)){
            if(!act.getOwner().equals(userId)){
            	System.out.println(act);
                rta.add(act);
            } 
        }
        return rta;
	}

	@Override
	public List<Activity> getAvailableActivitiesByRangeCredrits(String userId, Filter filter) {
		Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(filter.getStateActiviti()));
        System.out.println(filter.getRangeCredrits().get(1));
        System.out.println(filter.getRangeCredrits().get(0));
        System.out.println(filter.getPag());
        query.addCriteria(Criteria.where("bet").lt(filter.getRangeCredrits().get(1)).gt(filter.getRangeCredrits().get(0)));
        query.skip(4*filter.getPag()).limit(4);
        for (Activity act: mongoOperation.find(query, Activity.class)){
            if(!act.getOwner().equals(userId)){
                rta.add(act);
            } 
        }
        return rta;
	}

  
}
