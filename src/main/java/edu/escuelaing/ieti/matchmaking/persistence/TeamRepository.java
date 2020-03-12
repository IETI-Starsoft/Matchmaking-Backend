package edu.escuelaing.ieti.matchmaking.persistence;

import java.util.List;

import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;

public interface TeamRepository {

    Team create(Team team) throws EntityExistsException;

    void remove(String teamId) throws EntityNotFoundException;

    Team update(Team team) throws EntityNotFoundException;

    Team getTeamById(String teamid) throws EntityNotFoundException;

    List<Team> getAll() throws EntityNotFoundException;
}
