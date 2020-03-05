package edu.escuelaing.ieti.matchmaking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.PagosService;

public class PagosServiceImpl implements PagosService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void rechargeCredits(User user, int saldo) throws MatchmakingException {
        user.setCredits(saldo);
        userRepository.update(user);
        
    }

    @Override
    public void bet(User user, int saldo) throws MatchmakingException {
        user.setCredits(saldo);
        userRepository.update(user);
    }

}