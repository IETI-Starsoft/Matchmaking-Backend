package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;
import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

public interface PaymentService {

    void rechargeCredits(User user, int amount) throws MatchmakingException, EntityNotFoundException;

    void bet(User user, int amount) throws MatchmakingException, EntityNotFoundException, InsufficientFundsException;

}