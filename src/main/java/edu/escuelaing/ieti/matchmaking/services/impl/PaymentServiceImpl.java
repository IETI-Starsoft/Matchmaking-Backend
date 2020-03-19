package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.ActivityRepository;
import edu.escuelaing.ieti.matchmaking.persistence.TeamRepository;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void rechargeCredits(String userID, int amount) throws MatchmakingException, EntityNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public void addCreditsUserToUser(String userID1, String userID2, int amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addCreditsUserToTeam(String userID, String teamID, int amount) throws EntityNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public void addCreditsTeamToUser(String teamID, String userID, int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public void betUserToActivity(String userID, String activityID, int amount) throws EntityNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public void betTeamToActivity(String teamID, String activityID, int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        // TODO Auto-generated method stub

    }

}