package edu.escuelaing.ieti.matchmaking.services;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;
public interface TeamService {
	
	Team create(Team team)  throws EntityExistsException,EntityNotFoundException;

    void remove(Team team)  throws EntityNotFoundException;

    Team update(Team team)  throws EntityNotFoundException;

    Team getTeamById(String teamId)  throws EntityNotFoundException;

    List<Team> getAll()  throws EntityNotFoundException;
    
    List<User> getMembersByTeam(String teamId) throws EntityNotFoundException;

    List<Team> getTeamsByCaptainId(String captainId);
}
