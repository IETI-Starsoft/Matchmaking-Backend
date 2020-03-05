package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws MatchmakingException;

    void remove(String userId) throws MatchmakingException;

    User update(User user) throws MatchmakingException;

    User getUserById(String userId) throws MatchmakingException;

    List<User> getAll();
}
