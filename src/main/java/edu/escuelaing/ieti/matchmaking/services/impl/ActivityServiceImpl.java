package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.State;
import edu.escuelaing.ieti.matchmaking.persistence.ActivityRepository;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PaymentService paymentService;

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
    public List<Activity> getActivities(String userId, State state) {
        Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(state));
        for (Activity act : mongoOperation.find(query, Activity.class)) {
            if (!act.getOwner().equals(userId)) {
                rta.add(act);
            }
        }
        return rta;
    }

    @Override
    public List<Activity> getAvailableActivitiesByTypeActiviti(String userId, Filter filter) {
        Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(filter.getStateActiviti()));
        query.addCriteria(Criteria.where("type").is(filter.getLabels().get(0)));
        query.skip(4 * filter.getPag()).limit(4);
        for (Activity act : mongoOperation.find(query, Activity.class)) {
            if (!act.getOwner().equals(userId)) {
                rta.add(act);
            }
        }
        return rta;

    }

    @Override
    public List<Activity> getAvailableActivitiesFilter(String userId, Filter filter) {
        Query query = new Query();
        ArrayList<Activity> rta = new ArrayList<Activity>();
        query.addCriteria(Criteria.where("state").is(filter.getStateActiviti()));
        query.skip(4 * filter.getPag()).limit(4);
        for (Activity act : mongoOperation.find(query, Activity.class)) {
            if (!act.getOwner().equals(userId)) {
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
        query.addCriteria(
                Criteria.where("_class").is("edu.escuelaing.ieti.matchmaking.model." + filter.getParticipants()));
        query.skip(4 * filter.getPag()).limit(4);
        for (Activity act : mongoOperation.find(query, Activity.class)) {
            if (!act.getOwner().equals(userId)) {
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
        query.addCriteria(
                Criteria.where("bet").lt(filter.getRangeCredrits().get(1)).gt(filter.getRangeCredrits().get(0)));
        query.skip(4 * filter.getPag()).limit(4);
        for (Activity act : mongoOperation.find(query, Activity.class)) {
            if (!act.getOwner().equals(userId)) {
                rta.add(act);
            }
        }
        return rta;
    }

    @Override
    public Activity winnerActivity(String activityId, String winner)
            throws EntityNotFoundException, MatchmakingException {
        Activity act = getActivityById(activityId);
        if (act.getWinner() == null) {
            if (act.getLoser() != null) {
                act = finishActivityPay(winner, act);
                act.setState(State.Finished);
            }
            act.setWinner(winner);
        } else {
            act = invalidState(winner, act.getWinner(), act);
            act.setState(State.Finished);
        }
        update(act);
        return act;
    }

    @Override
    public Activity loserActivity(String activityId, String loser)
            throws EntityNotFoundException, MatchmakingException {
        Activity act = getActivityById(activityId);
        if (act.getLoser() == null) {
            if (act.getWinner() != null) {
                act = finishActivityPay(act.getWinner(), act);
                act.setState(State.Finished);
            }
            act.setLoser(loser);
        } else {
            act = invalidState(loser, act.getLoser(), act);
            act.setState(State.Finished);
        }
        update(act);
        return act;
    }

    private Activity invalidState(String userId1, String userId2, Activity act)
            throws MatchmakingException, EntityNotFoundException {
        if (act.getBet() > 0) {
            int credits = act.getCredits();
            int creditsUser = credits / 2;
            creditsUser = (int) Math.round(creditsUser * 0.85);
            if (act.getClass().getSimpleName().equals("IndividualActivity")) {
                paymentService.payActivityToUser(act.getId(), userId1, creditsUser);
                paymentService.payActivityToUser(act.getId(), userId2, creditsUser);
            } else {
                System.out.println(act.getClass().getSimpleName());
                paymentService.payActivityToTeam(act.getId(), userId1, creditsUser);
                paymentService.payActivityToTeam(act.getId(), userId2, creditsUser);
            }
        }
        return getActivityById(act.getId());
    }

    private Activity finishActivityPay(String winner, Activity act) throws EntityNotFoundException {
        if (act.getBet() > 0) {
            int credits = act.getCredits();
            int pay = (int) Math.round(credits * 0.85);
            if (act.getClass().getSimpleName().equals("IndividualActivity")) {
                paymentService.payActivityToUser(act.getId(), winner, pay);
            } else {
                paymentService.payActivityToTeam(act.getId(), winner, pay);
            }
        }
        return getActivityById(act.getId());
    }

}
