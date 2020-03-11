package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

public interface PaymentService {

    void rechargeCredits(User user, int amount) throws MatchmakingException;

    void bet(User user, int amount) throws MatchmakingException;

}