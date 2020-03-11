package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.exception.UserMatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user) throws UserMatchmakingException;

    User update(User user) throws UserMatchmakingException;

    User getById(String userId) throws UserMatchmakingException;

    void remove(String userId) throws UserMatchmakingException;

    List<User> getAll();

}
