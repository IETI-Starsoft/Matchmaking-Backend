package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.persistence.TeamRepository;
import edu.escuelaing.ieti.matchmaking.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamRepository inMemoryRepository;

	@Override
	public Team create(Team team) throws EntityExistsException {
		return inMemoryRepository.create(team);
	}

	@Override
	public void remove(String teamId) throws EntityNotFoundException {
		inMemoryRepository.remove(teamId);
	}

	@Override
	public Team update(Team team) throws EntityNotFoundException {
		return inMemoryRepository.update(team);
	}

	@Override
	public Team getTeamById(String teamId) throws EntityNotFoundException {
		return inMemoryRepository.getTeamById(teamId);
	}

	@Override
	public List<Team> getAll() throws EntityNotFoundException {
		return inMemoryRepository.getAll();
	}

}
