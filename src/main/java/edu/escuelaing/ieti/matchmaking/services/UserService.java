package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.exception.UserMatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws UserMatchmakingException;

    void remove(String userId) throws UserMatchmakingException;

    User update(User user) throws UserMatchmakingException;

    User getUserById(String userId) throws UserMatchmakingException;

    List<User> getAll();
}
