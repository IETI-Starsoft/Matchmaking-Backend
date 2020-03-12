package edu.escuelaing.ieti.matchmaking.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.persistence.TeamRepository;
import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;

@Service
public class InMemoryTeamRepository implements TeamRepository {

	private static Map<String, Team> teamMap = new ConcurrentHashMap<>();

	@Override
	public Team create(Team team) throws EntityExistsException {
		String teamId = team.getTeamId();
		if (!teamMap.containsKey(teamId)) {
			teamMap.put(teamId, team);
			return team;
		}
		throw new EntityExistsException(Team.class, "Team", team.toString());
	}

	@Override
	public void remove(String teamId) throws EntityNotFoundException {
		if (teamMap.containsKey(teamId)) {
			teamMap.remove(teamId);
		} else {
			throw new EntityNotFoundException(Team.class, "id Team", teamId);
		}

	}

	@Override
	public Team update(Team team) throws EntityNotFoundException {
		String equipoId = team.getTeamId();
		if (teamMap.containsKey(equipoId)) {
			teamMap.put(equipoId, team);
			return team;
		}
		throw new EntityNotFoundException(Team.class, "Team", team.toString());
	}

	@Override
	public Team getTeamById(String teamId) throws EntityNotFoundException {
		if (teamMap.get(teamId) == null) {
			throw new EntityNotFoundException(Team.class, "Id team", teamId);
		} else {
			return teamMap.get(teamId);
		}
	}

	@Override
	public List<Team> getAll() throws EntityNotFoundException {
		Set<Map.Entry<String, Team>> entrySet =teamMap.entrySet();
		List<Team> teams = new ArrayList<>();
		for (Map.Entry<String, Team> entry : entrySet) {
			teams.add(entry.getValue());
		}
		return teams;
	}

}
