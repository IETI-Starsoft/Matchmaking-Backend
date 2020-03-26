package edu.escuelaing.ieti.matchmaking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;
import edu.escuelaing.ieti.matchmaking.services.TeamService;
import edu.escuelaing.ieti.matchmaking.services.UserService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    ActivityService activityService;

    @Override
    public void rechargeCredits(String userID, int amount) throws MatchmakingException, EntityNotFoundException {
        User user = userService.getUserById(userID);
        user.addCredits(amount);
        userService.update(user);

    }

    @Override
    public void addCreditsUserToUser(String userID1, String userID2, int amount) throws EntityNotFoundException {
        User user1 = userService.getUserById(userID1);
        User user2 = userService.getUserById(userID2);
        if (user1.getCredits() >= amount) {
            user1.subCredits(amount);
            user2.addCredits(amount);
            userService.update(user1);
            userService.update(user2);
        }

    }

    @Override
    public void addCreditsUserToTeam(String userID, String teamID, int amount) throws EntityNotFoundException {
        User user = userService.getUserById(userID);
        Team team = teamService.getTeamById(teamID);
        if (user.getCredits() >= amount) {
            user.subCredits(amount);
            team.addCredits(amount);
            userService.update(user);
            teamService.update(team);
        }

    }

    @Override
    public void addCreditsTeamToUser(String teamID, String userID, int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        Team team = teamService.getTeamById(teamID);
        User user = userService.getUserById(userID);
        if (team.getCredits() >= amount) {
            team.subCredits(amount);
            user.addCredits(amount);
            teamService.update(team);
            userService.update(user);

        }

    }

    @Override
    public void betUserToActivity(String userID, String activityID, int amount) throws EntityNotFoundException {
        User user = userService.getUserById(userID);
        Activity act = activityService.getActivityById(activityID);
        if (user.getCredits() >= amount) {
            user.subCredits(amount);
            act.subCredits(amount);
            userService.update(user);
            activityService.update(act);

        }

    }

    @Override
    public void betTeamToActivity(String teamID, String activityID, int amount)
            throws InsufficientFundsException, EntityNotFoundException {
        Team team = teamService.getTeamById(teamID);
        Activity act = activityService.getActivityById(activityID);
        if (team.getCredits() >= amount) {
            team.subCredits(amount);
            act.addCredits(amount);
            teamService.update(team);
            activityService.update(act);

        }

    }

    @Override
    public void payActivityToUser(String activityID, String userID, int amount) throws EntityNotFoundException {
        Activity act = activityService.getActivityById(activityID);
        User user = userService.getUserById(userID);
        if (act.getCredits() >= amount) {
            act.subCredits(amount);
            user.addCredits(amount);
            activityService.update(act);
            userService.update(user);

        }

    }

    @Override
    public void payActivityToTeam(String activityID, String teamID, int amount) throws EntityNotFoundException {
        Activity act = activityService.getActivityById(activityID);
        Team team = teamService.getTeamById(teamID);
        if (act.getCredits() >= amount) {
            act.subCredits(amount);
            team.addCredits(amount);
            activityService.update(act);
            teamService.update(team);
        }

    }

}