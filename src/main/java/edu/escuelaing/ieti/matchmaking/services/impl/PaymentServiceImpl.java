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
    ActivityRepository activityRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void rechargeCredits(String userID, int amount) throws MatchmakingException, EntityNotFoundException {

        // user

    }

    @Override
    public void addCreditsUserToUser(String userID1, String userID2, int amount) {
        // user

    }

    @Override
    public void addCreditsUserToTeam(String userID, String teamID, int amount) throws EntityNotFoundException {
        // user
        teamRepository.addCredits(teamID, amount);

    }

    @Override
    public void addCreditsTeamToUser(String teamID, String userID, int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        teamRepository.subCredits(teamID, amount);
        // user

    }

    @Override
    public void betUserToActivity(String userID, String activityID, int amount) throws EntityNotFoundException {
        // user
        activityRepository.addCredits(activityID, amount);

    }

    @Override
    public void betTeamToActivity(String teamID, String activityID, int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        teamRepository.subCredits(teamID, amount);
        activityRepository.subCredits(activityID, amount);

    }

}