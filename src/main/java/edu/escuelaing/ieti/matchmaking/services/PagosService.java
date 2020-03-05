package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

public interface PagosService {

    void rechargeCredits(User user, int saldo) throws MatchmakingException;

    void bet(User user, int saldo) throws MatchmakingException;

}