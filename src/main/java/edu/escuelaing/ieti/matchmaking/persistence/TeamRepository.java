package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;

public interface TeamRepository extends MongoRepository<Team, String>{

    Team create(Team team) throws EntityExistsException;

    void remove(Team team) throws EntityNotFoundException;

    Team update(Team team) throws EntityNotFoundException;

    Team getTeamById(String teamid) throws EntityNotFoundException;

    List<Team> getAll() throws EntityNotFoundException;
    
    boolean existsById (String teamId);
}
