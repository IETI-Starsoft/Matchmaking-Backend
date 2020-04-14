package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws EntityExistsException;

    void remove(User user) throws EntityNotFoundException;

    User update(User user) throws EntityNotFoundException;

    User getUserById(String userId) throws EntityNotFoundException;

    User getUserByEmail(String email) throws EntityNotFoundException;

    List<User> getUserFriendsById(String userId);

    List<User> getUserFriendsById(String userId, int page);

    List<Team> getUserTeamsById(String userId);

    List<String> getUsersEmailContaining(String searchStr, int limitTo);

    List<User> getAll();

    List<Activity> getUserActivityById(String userId);
}
