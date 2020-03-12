package edu.escuelaing.ieti.matchmaking.services.impl;

import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.exception.InsufficientFundsException;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.UserRepository;
import edu.escuelaing.ieti.matchmaking.services.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void rechargeCredits(User user, int amount) throws MatchmakingException, EntityNotFoundException {
        user.addCredits(amount);
        userRepository.update(user);

    }

    @Override
    public void bet(User user, int amount)
            throws MatchmakingException, EntityNotFoundException, InsufficientFundsException {
        user.subCredits(amount);
        userRepository.update(user);
    }

}