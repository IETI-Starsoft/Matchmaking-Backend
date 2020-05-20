package edu.escuelaing.ieti.matchmaking.services;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
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

    List<Team> getUserTeamsById(String userId, int page);

    List<String> getUsersNotFriendsWithEmailContaining(String userId, String searchStr, int limitTo) throws EntityNotFoundException;

    User addFriendToUser(String userId, String userFriendEmail) throws EntityNotFoundException;

    List<User> getAll();

    List<Activity> getUserActivityById(String userId);

	List<Activity> getActivitiesBytActivity(Filter filter);

	List<Activity> getAllActivitiesByRangeCredits(Filter filter);

	List<Activity> getAllActivitiesByNoneFilter(Filter filter);
	
	void setRanking(String userId, Double score) throws EntityNotFoundException ;
}
