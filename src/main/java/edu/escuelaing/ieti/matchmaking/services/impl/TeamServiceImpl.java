package edu.escuelaing.ieti.matchmaking.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.matchmaking.exception.EntityExistsException;
import edu.escuelaing.ieti.matchmaking.exception.EntityNotFoundException;
import edu.escuelaing.ieti.matchmaking.model.Activity;
import edu.escuelaing.ieti.matchmaking.model.Filter;
import edu.escuelaing.ieti.matchmaking.model.Team;
import edu.escuelaing.ieti.matchmaking.model.User;
import edu.escuelaing.ieti.matchmaking.persistence.TeamRepository;
import edu.escuelaing.ieti.matchmaking.services.ActivityService;
import edu.escuelaing.ieti.matchmaking.services.TeamService;
import edu.escuelaing.ieti.matchmaking.services.UserService;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	UserService userService;
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private ActivityService activityService;

	@Override
	public Team create(Team team) throws EntityExistsException, EntityNotFoundException {
		Team teamSave = teamRepository.save(team);
		if (team.getMembers().size() > 0) {
			for (String userId : teamSave.getMembers()) {
				User user = userService.getUserById(userId);
				List<String> teamsUser = user.getTeams();
				teamsUser.add(teamSave.getTeamId());
				user.setTeams(teamsUser);
				userService.update(user);
			}
		}
		User captain = userService.getUserById(team.getCaptainId());
		List<String> teamsCaptain = captain.getTeams();
		teamsCaptain.add(teamSave.getTeamId());
		captain.setTeams(teamsCaptain);
		userService.update(captain);
		return teamSave;
	}

	@Override
	public void remove(Team team) throws EntityNotFoundException {
		if (!teamRepository.existsById(team.getTeamId())) {
			throw new EntityNotFoundException(Team.class, "Team Id", team.getTeamId());
		}
		teamRepository.delete(team);
	}

	@Override
	public Team update(Team team) throws EntityNotFoundException {
		if (!teamRepository.existsById(team.getTeamId())) {
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
		return teamRepository.findAll();
	}

	@Override
	public List<Team> getTeamsByCaptainId(String captainId) {
		ArrayList<Team> teams = new ArrayList<Team>();
		Iterable<Team> ite = teamRepository.findAllByCaptainId(captainId);
		Iterator<Team> it = ite.iterator();
		while (it.hasNext()) {
			teams.add(it.next());
		}
		return teams;
	}

	@Override
	public List<User> getMembersByTeam(String teamid) throws EntityNotFoundException {
		Team team = getTeamById(teamid);
		User captain = userService.getUserById(team.getCaptainId());
		List<String> membersIds = team.getMembers();
		List<User> members = new ArrayList<User>();
		for (String memberid : membersIds) {
			members.add(userService.getUserById(memberid));
		}
		members.add(captain);
		return members;
	}

	@Override
	public List<Activity> getTeamActivitiesById(String teamId) {
		Optional<Team> optionalTeam = teamRepository.findById(teamId);
		List<Activity> activities = new ArrayList<>();
		optionalTeam.ifPresent(team -> {
			team.getActivities().forEach(activityId -> {
				try {
					activities.add(activityService.getActivityById(activityId));
				} catch (EntityNotFoundException e) {
					e.printStackTrace();
				}
			});
		});
		return activities;
	}

	@Override
	public List<Activity> getActivitiesBtActivity(Filter filter) {
		List<Activity> rta = new ArrayList<>();
		List<Activity> activities = getAllActivitiesByTeams(filter);
		activities.forEach(activiti -> {
			if (activiti.getState().equals(filter.getStateActiviti())
					&& activiti.getType().contains(filter.getLabels().get(0))) {
				rta.add(activiti);
			}
		});
		return activitiesPagination(rta,filter.getPag());
	}

	@Override
	public List<Activity> getAllActivitiesByTeams(Filter filter) {
		List<Activity> activities = new ArrayList<>();
		if (filter.getTeam().equals("All")) {
			List<Team> teams = userService.getUserTeamsById(filter.getUserConsulting());
			teams.forEach(team -> {
				List<Activity> activitiesTeam = getTeamActivitiesById(team.getTeamId());
				activitiesTeam.forEach(activity -> {
					activities.add(activity);
				});
			});
		} else {
			getTeamActivitiesById(filter.getTeam()).forEach(activiy -> {
				activities.add(activiy);
			});
		}
		return activities;
	}

	@Override
	public List<Activity> getAllActivitiesByRangeCredits(Filter filter) {
		List<Activity> activities = getAllActivitiesByTeams(filter);
		List<Activity> rta = new ArrayList<>();
		activities.forEach(activiti -> {
			if (activiti.getState().equals(filter.getStateActiviti())
					&& (activiti.getBet() >= filter.getRangeCredrits().get(0)
							&& activiti.getBet() <= filter.getRangeCredrits().get(1))) {
				rta.add(activiti);
			}
		});
		return activitiesPagination(rta,filter.getPag());

	}
	@Override
	public List<Activity> getAllActivitiesByNoneFilter(Filter filter) {
		List<Activity> activities = getAllActivitiesByTeams(filter);
		List<Activity> rta = new ArrayList<>();
		activities.forEach(activiti -> {
			if (activiti.getState().equals(filter.getStateActiviti())) {
				rta.add(activiti);
			}
		});
		if(rta.size()>4) {
			return activitiesPagination(rta,filter.getPag());
		}
		else
			return rta;

	}

	@Override
	public List<Activity> activitiesPagination(List<Activity> activities, Integer pag) {
		List<Activity> activitiesP=activities;
		Integer initialIndex=(pag*4);
		List<Activity>rta=new ArrayList<>();
		if(initialIndex>activities.size()) {
			return rta;
		}else {
			int count=0;
			while(count<4 && (activitiesP.size()>(initialIndex+count))) {
				rta.add(activitiesP.get(initialIndex+count));
				count+=1;
			}
		}
		return rta;
	}
}
