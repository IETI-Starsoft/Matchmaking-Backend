package edu.escuelaing.ieti.matchmaking.persistence;

import edu.escuelaing.ieti.matchmaking.exception.MatchmakingException;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user) throws MatchmakingException;

    User update(User user) throws MatchmakingException;

    User getById(String userId) throws MatchmakingException;

    void remove(String userId) throws MatchmakingException;

    List<User> getAll();

}
