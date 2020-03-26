package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

public interface PaymentService {
        User rechargeCredits(String userID, int amount) throws MatchmakingException, EntityNotFoundException;

        void addCreditsUserToUser(String userID1, String userID2, int amount) throws EntityNotFoundException;

        void addCreditsUserToTeam(String userID, String teamID, int amount) throws EntityNotFoundException;

        void addCreditsTeamToUser(String teamID, String userID, int amount)
                        throws InsufficientFundsException, EntityNotFoundException;

        void betUserToActivity(String userID, String activityID, int amount) throws EntityNotFoundException;

        void betTeamToActivity(String teamID, String activityID, int amount)
                        throws InsufficientFundsException, EntityNotFoundException;

        void payActivityToUser(String activityID, String userID, int amount) throws EntityNotFoundException;

        void payActivityToTeam(String activityID, String teamID, int amount) throws EntityNotFoundException;

}