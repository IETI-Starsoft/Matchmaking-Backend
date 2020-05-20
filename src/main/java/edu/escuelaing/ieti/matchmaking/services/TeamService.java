package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;

public interface TeamService {

	Team create(Team team) throws EntityExistsException, EntityNotFoundException;

	void remove(Team team) throws EntityNotFoundException;

	Team update(Team team) throws EntityNotFoundException;

	Team getTeamById(String teamId) throws EntityNotFoundException;

	List<Team> getAll() throws EntityNotFoundException;

	List<User> getMembersByTeam(String teamId) throws EntityNotFoundException;

	List<Team> getTeamsByCaptainId(String captainId);

	List<Activity> getTeamActivitiesById(String teamId);

	List<Activity> getActivitiesBtActivity(Filter filter);

	List<Activity> getAllActivitiesByTeams(Filter filter);

	List<Activity> getAllActivitiesByRangeCredits(Filter filter);

	List<Activity> getAllActivitiesByNoneFilter(Filter filter);
	
	List<Activity> activitiesPagination(List<Activity> activities,Integer pag);
	
	void setRanking(String teamId, Integer score) throws EntityNotFoundException ;

}
