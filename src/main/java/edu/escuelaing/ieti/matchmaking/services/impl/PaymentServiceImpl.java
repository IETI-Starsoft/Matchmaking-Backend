package edu.escuelaing.ieti.matchmaking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void rechargeCredits(User user, int amount) throws MatchmakingException {
        user.setCredits(amount);
        userRepository.update(user);

    }

    @Override
    public void bet(User user, int amount) throws MatchmakingException {
        user.setCredits(amount);
        userRepository.update(user);
    }

}