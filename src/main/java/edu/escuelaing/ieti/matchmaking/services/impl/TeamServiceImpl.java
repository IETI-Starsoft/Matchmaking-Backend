package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.List;
import java.util.Optional;

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
	private TeamRepository teamRepository;

	@Override
	public Team create(Team team) throws EntityExistsException {
		if (teamRepository.existsById(team.getTeamId())){
            throw new EntityExistsException(Team.class, "Team Id", team.getTeamId());
        }
        return teamRepository.save(team);
	}

	@Override
	public void remove(Team team) throws EntityNotFoundException {
		if (teamRepository.existsById(team.getTeamId())){
            throw new EntityNotFoundException(Team.class, "Team Id", team.getTeamId());
        }
		teamRepository.delete(team);
	}

	@Override
	public Team update(Team team) throws EntityNotFoundException {
		if (teamRepository.existsById(team.getTeamId())){
            throw new EntityNotFoundException(Team.class, "Team Id", team.getTeamId());
        }
		return teamRepository.save(team);
	}

	@Override
	public Team getTeamById(String teamId) throws EntityNotFoundException {
		 Optional<Team> optionalTeam = teamRepository.findById(teamId);
	     return optionalTeam.orElseThrow(() -> new EntityNotFoundException(Team.class, "Team Id", teamId));
	}

	@Override
	public List<Team> getAll() throws EntityNotFoundException {
		return  teamRepository.findAll();
	}

}
